package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //대화상자 객체 생성
    Dialog dlg1;
    Dialog dlg2;
    ImageView ivDlgBanner;
    Button btnDlgEvent;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        //Dialog 클래스로 다이얼로그 객체 생성 및 세팅
        dlg1 = new Dialog(this); // 다이얼로그 객체 생성 1-1
        dlg1.setContentView(R.layout.dialog_layout11); // 다이얼로그화면 등록 1-2

        //Dialog 안의 View객체들 얻어오기 1-3
        ivDlgBanner = dlg1.findViewById(R.id.ivDlgBanner);
        btnDlgEvent = dlg1.findViewById(R.id.btnDlgEvent);


        btnDlgEvent.setOnClickListener(new View.OnClickListener() { //1-4
            @Override
            public void onClick(View v) {
                ivDlgBanner.setImageResource(R.drawable.face04);
                Toast.makeText(getApplicationContext(), "버튼을 눌렀니?", Toast.LENGTH_SHORT).show();
            }
        });

        //Activity에 Dialog등록하기 1-5
        dlg1.setOwnerActivity(MainActivity.this);
        dlg1.setCanceledOnTouchOutside(true); //다이얼로그 바깥영역클릭시(혹은back버튼클릭시) hide()상태가 됨.
                                                //종료할 것인지 여부, true : 종료됨, false : 종료안됨.

        //2-1
        dlg2 = new Dialog(this);
        dlg2.setContentView(R.layout.dialog_layout12);
        dlg2.setOwnerActivity(MainActivity.this);
        dlg2.setCanceledOnTouchOutside(false);

        final EditText etName = dlg2.findViewById(R.id.etName);
        Button btnOk = dlg2.findViewById(R.id.btnOk);
        Button btnCancel = dlg2.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etName.getText().toString();
                tvResult.setText(str);
                dlg2.dismiss(); //다이얼로그 객체 제거
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg2.dismiss();
            }
        });

        //다이얼로그가 등장할때 호출되는 메소드
        dlg2.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                etName.setText("");
            }
        });


    }//end oncreate

    public void showDialog1(View v){
        dlg1.show(); //다이얼로그 띄우기 1-6
    }

    public void showDialog2(View v){
        dlg2.show();
    }
}//end main
