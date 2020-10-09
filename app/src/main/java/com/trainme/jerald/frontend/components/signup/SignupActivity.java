package com.trainme.jerald.frontend.components.signup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.informasi.InformasiUmumActivity;
import com.trainme.jerald.frontend.components.informasi.KebijakanPrivasiActivity;
import com.trainme.jerald.frontend.components.signin.SigninActivity;
import com.trainme.jerald.frontend.dependencies.models.SignupModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements SignupContract.View {
    @Inject
    SignupController mUserController;

    @BindView(R.id.idLinear)
    LinearLayout linearLayout;

    @BindView(R.id.toggle)
    RadioGroup toggle;
    RadioButton roleChoosen;

    @BindView(R.id.fullName)
    TextInputEditText fullName;

    @BindView(R.id.retPhoneNumber)
    TextInputEditText retPhoneNumber;

    @BindView(R.id.retEmail)
    TextInputEditText retEmail;

    @BindView(R.id.retAddress)
    TextInputEditText retAddress;

    @BindView(R.id.coachConfig)
    LinearLayout coachConfig;

    @BindView(R.id.studentsConfig)
    LinearLayout studentsConfig;

    @BindView(R.id.retExperience)
    TextInputEditText retExperience;

    @BindView(R.id.retPricePerHour)
    TextInputEditText retPricePerHour;

    @BindView(R.id.retPassword)
    TextInputEditText retPassword;

    @BindView(R.id.reTypePassword)
    TextInputEditText reTypePassword;

    @BindView(R.id.btnSignup)
    Button btnSignup;

    @BindView(R.id.birthDate)
    TextInputEditText birthDate;

    @BindView(R.id.gender)
    Spinner gender;

    @BindView(R.id.levelSkill)
    Spinner levelSkill;

    @BindView(R.id.utr)
    TextInputEditText utr;

    @BindView(R.id.kebijakanPrivasi)
    TextView kebijakanPrivasi;

    @OnClick(R.id.kebijakanPrivasi)
    void clickKebijakanPrivasi() {
        startActivity(KebijakanPrivasiActivity.createIntent(getApplicationContext()));
    }

    @BindView(R.id.informasiUmum)
    TextView informasiUmum;

    @OnClick(R.id.informasiUmum)
    void clickInformasiUmum() {
        startActivity(InformasiUmumActivity.createIntent(getApplicationContext()));
    }

    @OnClick(R.id.btnSignup)
    void registerClicked() {
        String[] validateEmail = retEmail.getText().toString().split(" ");
        boolean validates = true;
        if(validateEmail.length > 1){
            validates = false;
        }

        if (TextUtils.isEmpty(retEmail.getText().toString())) {
            retEmail.setError(getString(R.string.error_email_empty));
        } else if (TextUtils.isEmpty(retPassword.getText().toString())) {
            retPassword.setError(getString(R.string.error_password_empty));
        } else if (TextUtils.isEmpty(retPhoneNumber.getText().toString())) {
            retPhoneNumber.setError("Phone Number can not empty");
        } else if (TextUtils.isEmpty(fullName.getText().toString())) {
            fullName.setError("Full Name can not empty");
        } else if (retPassword.getText().toString().compareTo(reTypePassword.getText().toString()) != 0) {
            reTypePassword.setError(getString(R.string.error_password_not_match));
        } else if(!validates) {
            retEmail.setError("Username can not have space");
        } else {
            if (isNetworkConnected()) {
                showLoading();
                int selectedId = toggle.getCheckedRadioButtonId();
                roleChoosen = findViewById(selectedId);
                int roleId = 1;
                String price = "";
                String experience = "";
                String utrs = "";
                String level = "";
                if (roleChoosen.getText().toString().compareTo("Coach") == 0) {
                    roleId = 2;
                    price = retPricePerHour.getText().toString();
                    experience = retExperience.getText().toString();
                } else {
                    utrs = utr.getText().toString();
                    level = levelSkill.getSelectedItem().toString();
                }

                SignupModel reg = new SignupModel(retEmail.getText().toString(),
                        fullName.getText().toString(),
                        retPhoneNumber.getText().toString(), retPassword.getText().toString(),
                        roleId, retAddress.getText().toString(), price, experience, birthDate.getText().toString(),
                        gender.getSelectedItem().toString(), level, utrs);
                mUserController.setView(this, mContext);
                mUserController.registerUser(reg);
            } else {
                onError("No Internet Connection");
            }
        }
    }

    Context mContext;

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    public static Intent createIntent(Context context) {
        return new Intent(context, SignupActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        mContext = this;
        myCalendar = Calendar.getInstance();
        setDate();
        birthDate.setOnClickListener(view -> {
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        birthDate.setFocusable(false);
        birthDate.setClickable(true);
        toggle.setOnCheckedChangeListener((group, checkedId) -> {
            roleChoosen = findViewById(checkedId);
            if (roleChoosen.getText().toString().compareTo("Coach") == 0) {
                coachConfig.setVisibility(View.VISIBLE);
                studentsConfig.setVisibility(View.GONE);
            } else {
                coachConfig.setVisibility(View.GONE);
                studentsConfig.setVisibility(View.VISIBLE);
            }
        });
        setDataAdapter();
    }

    private void setDataAdapter(){
        String[] genderUser=getResources().getStringArray(R.array.array_gender);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_item,R.id.text, genderUser);
        gender.setAdapter(adapter);

        String[] skillLevels=getResources().getStringArray(R.array.array_level);
        ArrayAdapter<String> adapterOpsi=new ArrayAdapter<String>(this,R.layout.spinner_item,R.id.text, skillLevels);
        levelSkill.setAdapter(adapterOpsi);
    }

    private void setDate() {
        date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelBirthdate();
        };
    }

    private void updateLabelBirthdate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void registerSuccess(String message) {
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        startActivity(SigninActivity.createIntent(getApplicationContext()));
    }

    @Override
    public void registerFailed(String message) {
        hideLoading();
        onError(message);
    }
}
