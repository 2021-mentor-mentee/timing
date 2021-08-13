package com.example.test_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Button btn_timer;

    TextView TextView;
    TimePicker TimePicker;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimePicker = (TimePicker) findViewById(R.id.tp_timepicker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }

        btn_timer = (Button)findViewById(R.id.btn_timer);

        changeActivity();
    }

    void changeActivity() {
        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
    }
}
