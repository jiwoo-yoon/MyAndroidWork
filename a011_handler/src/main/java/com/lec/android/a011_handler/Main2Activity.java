package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

/** Handler
 *  자바는 자바가상머신 위에서 자체적으로 스레드를 생성하고 운영하긴 하지만,
 *  스레드 부분 만큼은 '운영체제'의 영향을 받는다.
 *  안드로이드에서 돌아가는 자바는 결국 '안드로이드 운영체제'의 영향을 받을수 밖에 없는데, ..
 *  안드로이드 운영체제의 경우 '작업스레드' 가 '메인스레드'의 변수를 참조하거나 변경을 할수 있어도,
 *  '메인스레드' 에서 정의된 UI 를 변경할수는 없게 하고 있습니다.  --> CalledFromWrongThreadException !! (이전 예제 참조)
 *
 *  안드로이드에서 '작업 스레드' 가 '메인스레드의 UI' 에 접근(변경/사용) 하려면 Handler 를 사용해야 합니다
 *  Handler 는 메인스레드와 작업스레드 간에 통신을 할 수 있는 방법입니다ㅣ
 *
 *  사용 방법:
 *      ▫ 'Handler 를 생성'한 스레드만이 다른 작업스레드가 전송하는 'Message' 나 'Runnable객체' 를 수신하는 기능을 할 수 있다.
 *      ▫  Message 전송은 sendMessage()
 *      ▫  Runnable 전송은 postXXX()
 */

public class Main2Activity extends AppCompatActivity {

    int mainValue = 0;
    int backValue1 = 0;
    int backValue2 = 0;
    TextView tvMainValue;
    TextView tvBackValue1, tvBackValue2, tvBackValue3, tvBackValue4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvMainValue = findViewById(R.id.tvMainValue);
        tvBackValue1 = findViewById(R.id.tvBackValue1);
        tvBackValue2 = findViewById(R.id.tvBackValue2);
        tvBackValue3 = findViewById(R.id.tvBackValue3);
        tvBackValue4 = findViewById(R.id.tvBackValue4);

        //방법1, 방법2 의 스레드 생성하고 시작
        BackThread1 thread1 = new BackThread1();
        thread1.setDaemon(true);
        thread1.start();

        //방법3의 스레드 생성, 시작
        BackThread3 thread3 = new BackThread3(handler3); // 메인스레드의 Handler 객체를 외부클래스에 넘겨준것
       thread3.setDaemon(true);
        thread3.start();

        //방법4
        BackThread4 thread4 = new BackThread4(handler4); // 메인스레드의 Handler 객체를 외부클래스에 넘겨준것
        thread4.setDaemon(true);
        thread4.start();


    }//end oncreate

    public void mOnClick(View v){
        mainValue++;
        tvMainValue.setText("MainValue : " + mainValue);
    }//end

    class BackThread1 extends Thread{ // 작업스레드에 핸들러를 연결해서(inner class라서 가능)
        @Override
        public void run() {
            while (true){
                //방법1> 메인에서 생성된 Handler 객체의 sendEmptyMessage 를 통해 Message 전달
                backValue1++;
                handler1.sendEmptyMessage(1);

                //방법2> 메인에서 생성된 Handler 객체의 postXXX()를 통해 Runnable 객체 전달
                backValue2 += 2;
                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        tvBackValue2.setText("BackValue2 : " + backValue2);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }//end

    //---------------------------------------------------------------------------------------------
// 방법1
// '메인스레드' 에서 Handler 객체를 생성한다.
// Handler 객체를 생성한 스레드 만이 다른 스레드가 전송하는 Message나 Runnable 객체를
// 수신할수 있다.
// 아래 생성된 Handler 객체는 handleMessage() 를 오버라이딩 하여
// Message 를 수신합니다.

    Handler handler1 = new Handler(){ // 익명클래스로 핸들메세지를 오버라이드 해주고 , 메인에서 생성
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 1){ //Message id를 알아내는 함수 .what
                tvBackValue1.setText("BackValue1 : " + backValue1); // 메인스레드의 UI변경

            }
        }
    };

    //---------------------------------------------------------------------------------------------
    //방법2
    Handler handler2 = new Handler();

    //방법3>
    Handler handler3 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 0){
                // 메세지를 통해 받은 값을 메인 UI에 출력
                tvBackValue3.setText("BackValue3 : " + msg.arg1);
            }
        }
    };

    //방법4>
    Handler handler4 = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if(msg.what == 0){
                // 메세지를 통해 받은 값을 메인 UI에 출력
                tvBackValue4.setText("BackValue4 : " + msg.arg1);
            }
        }
    };


}//end main

// 방법3>(외부클래스)
// 작업스레드가 메인스레드와 완전히 분리되어 있어서 메인스레드에서 생성한 핸들러를 작업스레드에서
// 직접 참조 할수 없을때, Message 생성자 함수로 메세지를 생성하여 보내주면 됩니다.
// 가령 아래와 같이 메인스레드의 핸들러를 직접 사용할수 없는 분리된 작업 스레드
class BackThread3 extends Thread{

    int backValue = 0; // 자체적으로 값을 가진다.
    Handler handler;

    BackThread3(Handler handler){this.handler = handler;} // 메인(backthread3)으로부터 미리 핸들러를 불러와서 생성자를 만들어준다.

    @Override
    public void run() {
        while(true){
            backValue += 3;

            Message msg = new Message(); // 보낼 메세지 생성
            msg.what = 0; //메세지 id값 0으로 초기화
            msg.arg1 = backValue; //메세지객체에서는 arg1, 2 라는 정수값을 줄수있다

            handler.sendMessage(msg);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}//end 3

// 방법4> 메인스레드의 Handler를 직접 사용할 수 없는 분리된 작업스레드(외부클래스), 그렇게 중요하지는 않음
class BackThread4 extends Thread{
    int backValue = 0;
    Handler handler;
    BackThread4(Handler handler){this.handler = handler;}

    @Override
    public void run() {
        while(true){
            backValue += 4;
            // obtain 메소드로 메세지 생성
            // obtain(Handler h, int what, int arg1, int arg2)
            // Message.obtain(..) ← 다양하게 오버로딩 되어 있슴
            Message msg = Message.obtain(handler, 0, backValue, 0);
            handler.sendMessage(msg);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}//end 4

