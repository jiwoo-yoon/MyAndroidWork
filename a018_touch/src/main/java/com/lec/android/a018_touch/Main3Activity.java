package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
// 나는 윤지우 나는 여유를 장착하는중...
public class Main3Activity extends AppCompatActivity {

    TextView tvResult;

    //3개까지의 멀티터치를 다루기 위한 배열
    int id[] = new int[3];
    int x[] = new int[3];
    int y[] = new int[3];
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvResult = findViewById(R.id.tvResult);


    }//end oncreate

    //액티비티에서 발생한 터치 이벤트 처리(set이 아닌 액티비티에서 오버라이드해주기)
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //현재 터치 발생한 포인트 개수를 얻어온다.
        int pointCount = event.getPointerCount();

        if (pointCount > 3) pointCount = 3; // 3개의 포인트 터치를 넘어가도 3개까지만 처리

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //한개 포인트에 대한 DOWN 이 발생했을때
                event.getPointerId(0); //터치한 순간에 부여되는 포인트 고유번호
                x[0] = (int)(event.getX());
                y[0] = (int)(event.getY());
                result = "싱글터치 : \n";
                result += "(" + x[0] + ", " + y[0] + " )"; //x, y좌표 찍어주기
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //두개 이상의 포인트에 대한 DOWN 이 발생했을때(멀티터치)
                result = "멀티터치 : \n";
                for(int i = 0; i < pointCount; i++){
                    id[i] = event.getPointerId(i);
                    x[i] = (int)(event.getX(i));
                    y[i] = (int)(event.getY(i));
                    result += "id[" + id[i] + "] (" + x[i] + ", " + y[i] + ")\n";
                }
                break;
            case MotionEvent.ACTION_MOVE:
                result = "멀티터치 MOVE: \n";
                for(int i = 0; i < pointCount; i++){
                    id[i] = event.getPointerId(i);
                    x[i] = (int)(event.getX(i));
                    y[i] = (int)(event.getY(i));
                    result += "id[" + id[i] + "] (" + x[i] + ", " + y[i] + ")\n";
                }
                break;
            case MotionEvent.ACTION_UP:
                result = "";
                break;

        }

        tvResult.setText(result);

        return super.onTouchEvent(event);
    }
}//end main
