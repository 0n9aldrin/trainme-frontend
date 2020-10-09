package com.trainme.jerald.frontend.components.addsparring;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.sparring.SparringActivity;
import com.trainme.jerald.frontend.dependencies.models.SparringCreateModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddSparringActivity extends BaseActivity implements AddSparringContract.View {

    @Inject
    AddSparringController mController;
    @BindView(R.id.etSparringName)
    EditText etSparringName;
    @BindView(R.id.etSparringDate)
    EditText etSparringDate;
    @BindView(R.id.etSparringEndDate)
    EditText etSparringEndDate;
    @BindView(R.id.etSparringEndTime)
    EditText etSparringEndTime;
    @BindView(R.id.etSparringStartTime)
    EditText etSparringStartTime;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.levelSkill)
    Spinner levelSkill;

    @OnClick(R.id.add)
    void clickAdd() {
        if (TextUtils.isEmpty(etSparringName.getText().toString())) {
            etSparringName.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etSparringDate.getText().toString())) {
            etSparringDate.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etSparringEndDate.getText().toString())) {
            etSparringEndDate.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etSparringStartTime.getText().toString())) {
            etSparringStartTime.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etSparringEndTime.getText().toString())) {
            etSparringStartTime.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etLocation.getText().toString())) {
            etLocation.setError(getString(R.string.error_empty));
        } else {
            if (isNetworkConnected()) {
                showLoading();
                mController.setView(this);
                String startDT = etSparringDate.getText().toString() + " " + convertTime(etSparringStartTime.getText().toString())+ ":00";
                String endDT = etSparringEndDate.getText().toString() + " " + convertTime(etSparringEndTime.getText().toString())+ ":00";
                SparringCreateModel data = new SparringCreateModel(mController.getIdUser(), etSparringName.getText().toString(),
                        startDT, endDT, convertTime(etSparringStartTime.getText().toString()), etDescription.getText().toString(),
                        etLocation.getText().toString(), 1, levelSkill.getSelectedItem().toString());
                mController.saveData(data);
            } else {
                onError("No Internet Connection");
            }
        }
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, AddSparringActivity.class);
    }

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener dateEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sparring);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDelegate().setSupportActionBar(toolbar);
        myCalendar = Calendar.getInstance();
        setDate();
        setDateEnd();
        etSparringDate.setOnClickListener(view -> {
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        etSparringEndDate.setOnClickListener(view -> {
            new DatePickerDialog(this, dateEnd, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        etSparringStartTime.setOnClickListener(view -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this,
                    (timePicker, selectedHour, selectedMinute) ->
                            etSparringStartTime.setText(selectedHour + ":" + selectedMinute),
                    hour, minute, true);
            mTimePicker.setTitle("Set Time");
            mTimePicker.show();
        });

        etSparringEndTime.setOnClickListener(view -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this,
                    (timePicker, selectedHour, selectedMinute) ->
                            etSparringEndTime.setText(selectedHour + ":" + selectedMinute),
                    hour, minute, true);
            mTimePicker.setTitle("Set Time");
            mTimePicker.show();
        });
        etSparringStartTime.setFocusable(false);
        etSparringStartTime.setClickable(true);
        etSparringDate.setFocusable(false);
        etSparringDate.setClickable(true);
        etSparringEndDate.setFocusable(false);
        etSparringEndDate.setClickable(true);
        etSparringEndTime.setFocusable(false);
        etSparringEndTime.setClickable(true);
        setDataAdapter();
    }

    private void setDataAdapter(){
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
            updateLabelStart();
        };
    }

    private void updateLabelStart() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etSparringDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void setDateEnd() {
        dateEnd = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabeEnd();
        };
    }

    private void updateLabeEnd() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etSparringEndDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void saveDataSuccess() {
        hideLoading();
        finish();
        startActivity(SparringActivity.createIntent(getApplicationContext()));
    }

    @Override
    public void saveDataFailed(String message) {
        hideLoading();
        onError(message);
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    private String convertTime(String dataTime) {
        String res = "";
        String[] tm = dataTime.split(":");
        if (Integer.valueOf(tm[0]) < 10) {
            String min = tm[1];
            if(Integer.valueOf(min) < 10) {
                min = "0" + tm[1];
            }
            res = "0" + tm[0] + ":" + min;
        } else {
            String min = tm[1];
            if(Integer.valueOf(min) < 10) {
                min = "0" + tm[1];
                res = tm[0] + ":" + min;
            }else{
                res = dataTime;
            }
        }
        return res;
    }
}
