package com.example.adrianm.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class AppoinmentActivity extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    public int day,month,year,hour,min;
    EditText txtFecha;
    EditText txtHour;
    Button btnHour;
    SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        Button btnDate = (Button) findViewById(R.id.btnDate);
        txtHour = (EditText) findViewById(R.id.txtHour);
        btnHour = (Button) findViewById(R.id.btnHour);
        txtFecha = (EditText) findViewById(R.id.txtFecha);

        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.min = calendar.get(Calendar.MINUTE);
        txtFecha.setText(new StringBuilder().append(preferences.getString("date","")));
        txtHour.setText(new StringBuilder().append(preferences.getString("hour","")));

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btnHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showDatePicker(){
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        StringBuilder date = new StringBuilder().append(dayOfMonth).append("/")
                                .append(monthOfYear).append("/").append(year);
                        txtFecha.setText(date);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("date",date.toString());
                        editor.commit();
                    }
                }, 1, 2, 3);
        dpd.show();
    }

    public void showTimePicker() {
        TimePickerDialog tpd = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                StringBuilder date = new StringBuilder().append(selectedHour).append(":")
                        .append(selectedMinute);
                txtHour.setText(date);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("hour",date.toString());
                editor.commit();
            }
        },1,2,true);
        tpd.show();
    }


}
