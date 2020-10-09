package com.trainme.jerald.frontend.components.addcoaching;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trainme.jerald.frontend.MainApplication;
import com.trainme.jerald.frontend.R;
import com.trainme.jerald.frontend.components.base.BaseActivity;
import com.trainme.jerald.frontend.components.historycoaching.HistoryCoachingActivity;
import com.trainme.jerald.frontend.components.stucoaching.StudentCoachingActivity;
import com.trainme.jerald.frontend.dependencies.models.CoachingCreateModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCoachingActivity extends BaseActivity implements AddCoachingContract.View {

    @Inject
    AddCoachingController mController;
    @BindView(R.id.etCoachingName)
    EditText etCoachingName;
    @BindView(R.id.etCoachingDate)
    EditText etCoachingDate;
    @BindView(R.id.etCoachingEndDate)
    EditText etCoachingEndDate;
    @BindView(R.id.etCoachingStartTime)
    EditText etCoachingStartTime;
    @BindView(R.id.etCoachingEndTime)
    EditText etCoachingEndTime;
    @BindView(R.id.etLocation)
    EditText etLocation;
    @BindView(R.id.etDescription)
    EditText etDescription;
    @BindView(R.id.add)
    Button add;

    @OnClick(R.id.add)
    void clickAdd() {
        if (TextUtils.isEmpty(etCoachingName.getText().toString())) {
            etCoachingName.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etCoachingDate.getText().toString())) {
            etCoachingDate.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etCoachingEndDate.getText().toString())) {
            etCoachingEndDate.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etCoachingStartTime.getText().toString())) {
            etCoachingStartTime.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etCoachingEndTime.getText().toString())) {
            etCoachingEndTime.setError(getString(R.string.error_empty));
        } else if (TextUtils.isEmpty(etLocation.getText().toString())) {
            etLocation.setError(getString(R.string.error_empty));
        } else {
            if (isNetworkConnected()) {
                showLoading();
                mController.setView(this);
                String startDT = etCoachingDate.getText().toString() + " " + convertTime(etCoachingStartTime.getText().toString())+ ":00";
                String endDT = etCoachingEndDate.getText().toString() + " " + convertTime(etCoachingEndTime.getText().toString())+ ":00";

                CoachingCreateModel data = new CoachingCreateModel(mController.getIdUser(), etCoachingName.getText().toString(),
                        startDT, endDT,
                        etCoachingStartTime.getText().toString(), etDescription.getText().toString(), etLocation.getText().toString());
                mController.saveData(data);
            } else {
                onError("No Internet Connection");
            }
        }
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, AddCoachingActivity.class);
    }

    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener dateEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coaching);
        ((MainApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        myCalendar = Calendar.getInstance();
        setDate();
        setDateEnd();
        etCoachingDate.setOnClickListener(view -> {
            new DatePickerDialog(this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        etCoachingEndDate.setOnClickListener(view -> {
            new DatePickerDialog(this, dateEnd, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
        etCoachingStartTime.setFocusable(false);
        etCoachingStartTime.setClickable(true);
        etCoachingDate.setFocusable(false);
        etCoachingDate.setClickable(true);
        etCoachingEndDate.setFocusable(false);
        etCoachingEndDate.setClickable(true);
        etCoachingEndTime.setFocusable(false);
        etCoachingEndTime.setClickable(true);

        etCoachingStartTime.setOnClickListener(view -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> etCoachingStartTime.setText(selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Set Time");
            mTimePicker.show();
        });

        etCoachingEndTime.setOnClickListener(view -> {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this,
                    (timePicker, selectedHour, selectedMinute) ->
                            etCoachingEndTime.setText(selectedHour + ":" + selectedMinute),
                    hour, minute, true);
            mTimePicker.setTitle("Set Time");
            mTimePicker.show();
        });
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
        etCoachingDate.setText(sdf.format(myCalendar.getTime()));
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
        etCoachingEndDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void saveDataSuccess() {
        hideLoading();
        finish();
        startActivity(HistoryCoachingActivity.createIntent(getApplicationContext()));
    }

    @Override
    public void saveDataFailed(String message) {
        hideLoading();
        onError(message);
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
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
