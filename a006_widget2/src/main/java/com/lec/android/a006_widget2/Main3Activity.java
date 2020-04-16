package com.lec.android.a006_widget2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    TextView tvResult;
    SeekBar seekBar;

    int value = 0;
    int add = 2;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //값의 변화가 생겼을 때 콜백
            //progress : 진행값 0~max 지정가능
            //fromUser : 사용자에 의한 진행값이 변하는 경우 true(동영상볼때 밑에 프로그레스바를 직접 움직이는 것)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvResult.setText("onProgressChanged : " + progress + "(" + fromUser + ")");
            }

            //시작할때 콜백
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트랙킹시작", Toast.LENGTH_SHORT).show();
            }

            //끌날때 콜백
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "트랙킹종료", Toast.LENGTH_SHORT).show();
            }
        });

        //앱 시작시 Thread .. SeekBar 증가 시키기
        new Thread(new Runnable() {
            @Override
            public void run() {
                int max = seekBar.getMax();

                while (true){
                    value = seekBar.getProgress() + add; //seekBar.setProgress(); //현재값
                    if(value > max || value < 0){
                        add = -add;
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(value);
                        }
                    });

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



    }//end oncreate
}//end main
