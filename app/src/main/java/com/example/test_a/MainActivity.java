package com.example.test_a;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

public class MainActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pendingIntent;

    Button btn_timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.context = this;

        // 알람매니저 설정하기
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //타임피커 설정하기
        alarm_timepicker = findViewById(R.id.tp_timepicker);

        // Calendar 객체 생성하기
        final Calendar calendar = Calendar.getInstance();

        // 알람리시버 intent 생성하기
        final Intent my_intent = new Intent(this.context, Alarm_Reciver.class);

        // 알람 시작 버튼
        Button alarm_on = findViewById(R.id.btn_start);
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // calender에 시간 셋팅하기
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                // 시간 가져오기
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();
                Toast.makeText(MainActivity.this, "Alarm 예정" + hour + "시" + minute + "분", Toast.LENGTH_SHORT).show();

                // 알람 셋팅하기
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pendingIntent);
            }
        });

        //알람 정지 버튼
        Button alarm_off = findViewById(R.id.btn_finish);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Alarm 종료", Toast.LENGTH_SHORT).show();

                // 알람매니저 취소하기
                alarmManager.cancel(pendingIntent);

                my_intent.putExtra("state", "alarm off");

                // 알람 취소하기
                sendBroadcast(my_intent);



            }
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
