package com.lec.android.a007_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_two);

        Button btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 액티비티 종료
            }
        });

        //넘겨받은 Intent 객체를 받는다.
        Intent intent = getIntent();

        //"num"이라는 name으로 넘어온 값
        //만약 "num"이라는 name이 인텐트에 없었으면
        //디폴트값(두번째 매개변수)를 리턴
        int a = intent.getIntExtra("num", 0);
        int b = intent.getIntExtra("num2", 0);
        int c = intent.getIntExtra("num3", 999); // main2에 "num3"라는 이름은 없었다, 디폴트값을 지정
        long l = intent.getLongExtra("long", 0);
        String msg = intent.getStringExtra("msg"); //리턴값이 Object인 경우, 디폴트값 설정 없다. name이 없으면 null리턴

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("인텐트로 받은값 : " + a + ":" + b + ":" + c + ":" + l + ":" + msg);

        //Person데이터 받기
        Person p = (Person)intent.getSerializableExtra("Person"); //오브젝트이기에 형변환해줘야댐

        TextView tv2 = findViewById(R.id.tv2);
        tv2.setText("Person 받은값 :" + p.getName() + " : " + p.getAge());


        //첫번째 액티비티로 인텐트를 날리면
        Button btnToMain = findViewById(R.id.btnToMain);
        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
        });



    }//end oncreate
}//end main
