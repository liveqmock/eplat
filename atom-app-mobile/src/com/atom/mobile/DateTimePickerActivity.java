package com.atom.mobile;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class DateTimePickerActivity extends Activity {

    private DatePickerDialog dlgDatePicker;
    private TimePickerDialog dlgTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time_picker);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        final TextView txtDate = (TextView) super.findViewById(R.id.TxtDate);

        this.dlgDatePicker = new DatePickerDialog(this, new OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                txtDate.setText("Ñ¡ÔñÈÕÆÚ£º" + (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        Button btnDatePicker = (Button) super.findViewById(R.id.BtnDatePicker);
        btnDatePicker.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dlgDatePicker.show();
            }
        });

        final AnalogClock anogClock = (AnalogClock) super.findViewById(R.id.AnogClock);
        this.dlgTimePicker = new TimePickerDialog(this, new OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            }
        }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);

        Button btnTimePicker = (Button) super.findViewById(R.id.BtnTimePicker);
        btnTimePicker.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dlgTimePicker.show();
            }
        });
    }

}
