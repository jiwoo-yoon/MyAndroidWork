package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.function.LongFunction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //액티비티(화면객체, 메인액티비티)가 생성될때 호출되는 메소드
        //액티비티 초기화 하는 코드 작성.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        tvResult = findViewById(R.id.tvResult);
        LinearLayout ll = findViewById(R.id.ll);

        //방법2 : 익명 클래스(anonymous class) 사용
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // 클릭되었을때 호출되는 메소드(콜백메소드)
                Log.d("myapp", "버튼2가 클릭 되었습니다.");
                tvResult.setText("버튼2가 클릭됨");
                tvResult.setBackgroundColor(Color.YELLOW);
            }
        });

        //다양한 이벤트, 다양한 리스너 등록 가능
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) { //롱클릭 발생시 수행하는 콜백메스도
                Log.d("myapp", "버튼2가 롱클릭 되었습니다.");
                tvResult.setText("버튼2가 롱클릭 되었습니다.");
                tvResult.setBackgroundColor(Color.CYAN);
                //return false; //false를 리턴하면 이벤트가 click까지 간다.
                return true; //true를 리턴하면 이벤트가 Longclick으로 소멸(consume)된다.
            }
        });


        //방법3 : lambda - expression 사용하기
        //AndroidStudio의 세팅 필요(ppt참조)
        btn3.setOnClickListener((v) -> {
            Log.d("myapp", "버튼3가 클릭");
            tvResult.setText("버튼3 클릭");
            et = findViewById(R.id.et);
            ll.setBackgroundColor(Color.rgb(182,255,90));
        });

        //방법4 : implements 한 클래스 사용 (여러개의 동작들이 똑같은 동작을 했을때 편하게 쓸 수 있다.)

        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        class MyListener implements View.OnClickListener{

            String name;

            public MyListener(String name) {this.name = name;}

            @Override
            public void onClick(View v) {
                String tag = (String)v.getTag();
                String text = (String)((Button)v).getText(); //getText() 는 CharSequence 객체리턴
                String msg = String.format("%s 버튼 %s 이 클릭[%s]", name, text, tag);
                Log.d("myapp",msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));
            }
        }

        btnA.setOnClickListener(new MyListener("안녕1"));
        btnB.setOnClickListener(new MyListener("안녕2"));
        btnC.setOnClickListener(new MyListener("안녕3"));
        btnD.setOnClickListener(new MyListener("안녕4"));
        btnE.setOnClickListener(new MyListener("안녕5"));
        btnF.setOnClickListener(new MyListener("안녕6"));

        //방법5-1
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //연습
        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size = tvResult.getTextSize();
                Log.d("myapp", "글꼴사이즈 : " + size);
                tvResult.setTextSize(0, size +5);
            }
        });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float size = tvResult.getTextSize();
                Log.d("myapp", "글꼴사이즈 : " + size);
                tvResult.setTextSize(0, size -5);
            }
        });

    }//end oncreate

    //방법1 : 레이아웃 xmL 의 on*** 속성으로 지정
    public void changeText(View v){
        //Log.d(tag, message)
        //Log 창의 Debug 메서지로 출력
        Log.d("myapp", "버튼 1이 클릭되었습니다.");
        tvResult.setText("버튼1이 클릭되었습니다.");
    }

    //방법5 : 엑티비티가 implement, 메인메소드에 implements를 해주고 alt+insert 해준다.
    @Override
    public void onClick(View v) {
        Log.d("myapp", "Clear 버튼이 클릭");
        tvResult.setText("Clear 버튼이 클릭");
        et.setText("");
    }
//객체 뭔가를 만들면 R(resource)로 정수로 저장된다(id), 지역변수명은 웬만하면 일치시켜주는게 편핟.

}
