package com.mtmt_timing.timing;

import android.content.Intent;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class TimerActivity extends AppCompatActivity {

    LinearLayout timeCountSettingLV;
    EditText hourET, minuteET, secondET;
    Button startBtn, btn_alarm, stopBtn;
    int hour = 0, minute = 0, second = 0;
    MediaPlayer mediaPlayer;
    Boolean useTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        btn_alarm = findViewById(R.id.btn_alarm);
        timeCountSettingLV = (LinearLayout) findViewById(R.id.timeCountSettingLV);
        hourET = (EditText) findViewById(R.id.hourET);
        minuteET = (EditText) findViewById(R.id.minuteET);
        secondET = (EditText) findViewById(R.id.secondET);
        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);

        changeActivity();
        timerStart();
        timerStop();

    }


    void timerStart() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    hour = Integer.parseInt(hourET.getText().toString());
                }catch (Exception e){}
                try {
                    minute = Integer.parseInt(minuteET.getText().toString());
                }catch (Exception e){}
                try {
                    second = Integer.parseInt(secondET.getText().toString());
                }catch (Exception e){}

                handler.sendEmptyMessage(0);
            }

        });
    }

    void timerStop() {
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeMessages(0);
                if(hour == 0) {
                    hourET.setText("");
                } else if (minute == 0) {
                    minuteET.setText("");
                } else if (second == 0) {
                    secondET.setText("");
                }
                mediaPlayer.stop();
                mediaPlayer.release();
                useTimer = false;
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(second != 0) {
                //1초씩 감소
                second--;

                // 0분 이상이면
            } else if(minute != 0) {
                // 1분 = 60초
                second = 60;
                second--;
                minute--;

                // 0시간 이상이면
            } else if(hour != 0) {
                // 1시간 = 60분
                second = 60;
                minute = 60;
                second--;
                minute--;
                hour--;
            }

            //시, 분, 초가 10이하(한자리수) 라면
            // 숫자 앞에 0을 붙인다 ( 8 -> 08 )
            if(second <= 9){
                secondET.setText("0" + second);
            } else {
                secondET.setText(Integer.toString(second));
            }

            if(minute <= 9){
                minuteET.setText("0" + minute);
            } else {
                minuteET.setText(Integer.toString(minute));
            }

            if(hour <= 9){
                hourET.setText("0" + hour);
            } else {
                hourET.setText(Integer.toString(hour));
            }

//             시분초가 다 0이라면 toast를 띄우고 타이머를 종료한다.
            if(hour == 0 && minute == 0 && second == 0 && useTimer == false) {
                hourET.setText("");
                minuteET.setText("");
                secondET.setText("");
                mediaPlayer = MediaPlayer.create(TimerActivity.this, R.raw.alarm_sound);
                mediaPlayer.start();
                Vibrator vibrator = (Vibrator) getSystemService(TimerActivity.this.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
                useTimer = true;
            }
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            handler.removeMessages(0);
        }catch (Exception e){}
    }

    void changeActivity() {
        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
