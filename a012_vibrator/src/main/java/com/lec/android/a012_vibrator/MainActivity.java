package com.lec.android.a012_vibrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
// 진동
// 1. 진동 권한을 획득해야한다. AndroidManifest.xml
// 2. Vibrator 객체를 얻어서 진동시킨다

public class MainActivity extends AppCompatActivity {

    Button btnVib1, btnVib2, btnVib3, btnVib4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVib1 = findViewById(R.id.btnVib1);
        btnVib2 = findViewById(R.id.btnVib2);
        btnVib3 = findViewById(R.id.btnVib3);
        btnVib4 = findViewById(R.id.btnVib4);

        final Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        btnVib1.setText("5초 진동");
        btnVib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(5000); // 지정시간동안진동
            }
        });

        btnVib2.setText("지정한 패턴으로 진동");
        btnVib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long [] pattern = {100, 300, 100, 700, 300, 2000}; //ms기준, 대기(홀수인덱스), 진동(짝수인덱스) 순으로 나열해준다.
                vibrator.vibrate(pattern, -1);// 진동패턴은 배열로 지정, 반복횟수지정(0:무한반복, -1:반복없음, 양의정수:진동패턴배열의 해당 인덱스부터 진동 무한 반복)
            }
        });

        btnVib3.setText("무한반복진동");
        btnVib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(new long[]{100,1000,1000,500}, 0);
            }
        });

        btnVib4.setText("진동취소");
        btnVib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel(); // 진동취소소
            }
        });
    }
}
