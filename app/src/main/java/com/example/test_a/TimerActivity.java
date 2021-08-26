package com.example.test_a;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {

    LinearLayout timeCountSettingLV, timeCountLV;
    EditText hourET, minuteET, secondET;
    TextView hourTV, minuteTV, secondTV, finishTV;
    Button startBtn;
    int hour, minute, second;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeCountSettingLV = (LinearLayout) findViewById(R.id.timeCountSettingLV);
        timeCountLV = (LinearLayout) findViewById(R.id.timeCountLV);

        hourET = (EditText) findViewById(R.id.hourET);
        minuteET = (EditText) findViewById(R.id.minuteET);
        secondET = (EditText) findViewById(R.id.secondET);

        hourTV = (TextView) findViewById(R.id.hourTV);
        minuteTV = (TextView) findViewById(R.id.minuteTV);
        secondTV = (TextView) findViewById(R.id.secondTV);
        finishTV = (TextView) findViewById(R.id.finishTV);

        startBtn = (Button) findViewById(R.id.startBtn);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeCountSettingLV.setVisibility(View.GONE);
                timeCountLV.setVisibility(View.VISIBLE);

                hourTV.setText(hourET.getText().toString());
                minuteTV.setText(minuteET.getText().toString());
                secondTV.setText(secondET.getText().toString());

                hour = Integer.parseInt(hourET.getText().toString());
                minute = Integer.parseInt(minuteET.getText().toString());
                second = Integer.parseInt(secondET.getText().toString());

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {


                        if (second != 0) {

                            second--;


                        } else if (minute != 0) {

                            second = 60;
                            second--;
                            minute--;


                        } else if (hour != 0) {

                            second = 60;
                            minute = 60;
                            second--;
                            minute--;
                            hour--;
                        }


                        if (second <= 9) {
                            secondTV.setText("0" + second);
                        } else {
                            secondTV.setText(Integer.toString(second));
                        }

                        if (minute <= 9) {
                            minuteTV.setText("0" + minute);
                        } else {
                            minuteTV.setText(Integer.toString(minute));
                        }

                        if (hour <= 9) {
                            hourTV.setText("0" + hour);
                        } else {
                            hourTV.setText(Integer.toString(hour));
                        }


                        if (hour == 0 && minute == 0 && second == 0) {
                            timer.cancel();
                            finishTV.setText("타이머 종료.");
                        }
                    }
                };


                timer.schedule(timerTask, 0, 1000);
            }
        });
    }
}

