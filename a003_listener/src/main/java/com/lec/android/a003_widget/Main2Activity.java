package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    Button tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv0, tvp, tvs, tvm, tvd, tve, tvclear;
    EditText etres;
    Integer f1, f2;
    boolean bPlus, bSub, bMul, bDiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv0 = (Button)findViewById(R.id.tv0);
        tv1 = (Button)findViewById(R.id.tv1);
        tv2 = (Button)findViewById(R.id.tv2);
        tv3 = (Button)findViewById(R.id.tv3);
        tv4 = (Button)findViewById(R.id.tv4);
        tv5 = (Button)findViewById(R.id.tv5);
        tv6 = (Button)findViewById(R.id.tv6);
        tv7 = (Button)findViewById(R.id.tv7);
        tv8 = (Button)findViewById(R.id.tv8);
        tv9 = (Button)findViewById(R.id.tv9);
        tvp = (Button)findViewById(R.id.tvp);
        tvs = (Button)findViewById(R.id.tvs);
        tvm = (Button)findViewById(R.id.tvm);
        tvd = (Button)findViewById(R.id.tvd);
        tve = (Button)findViewById(R.id.tve);
        tvclear = (Button)findViewById(R.id.tvclear);
        etres = (EditText)findViewById(R.id.etres);

        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"0");
            }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"1");
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"2");
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"3");
            }
        });

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"4");
            }
        });

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"5");
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"6");
            }
        });

        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"7");
            }
        });

        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"8");
            }
        });

        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText(etres.getText()+"9");
            }
        });

        tvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 f1 = Integer.parseInt(etres.getText()+"");
                 bPlus = true;
                 etres.setText("");
            }
        });

        tvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = Integer.parseInt(etres.getText()+"");
                bSub = true;
                etres.setText("");
            }
        });

        tvm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = Integer.parseInt(etres.getText()+"");
                bMul = true;
                etres.setText("");
            }
        });

        tvd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = Integer.parseInt(etres.getText()+"");
                bDiv = true;
                etres.setText("");
            }
        });

        tve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f2 = Integer.parseInt(etres.getText()+"");

                if(bPlus == true){
                    etres.setText(f1 + f2 + "");
                    bPlus = false;
                }
                if(bSub == true){
                    etres.setText(f1 - f2 + "");
                    bSub = false;
                }
                if(bMul==true){
                    etres.setText(f1*f2+"");
                    bMul = false;
                }
                if(bDiv==true){
                    etres.setText(f1/f2+"");
                    bDiv = false;
                }
            }
        });

        tvclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etres.setText("");
            }
        });

    }
}
