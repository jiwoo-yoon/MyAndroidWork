package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoDetail extends AppCompatActivity {

    TextView tvName, tvAge, tvAddr;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvAddr = findViewById(R.id.tvAddr);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Info info = (Info)intent.getSerializableExtra("info");

        tvName.setText(info.getName());
        tvAge.setText(info.getAge());
        tvAddr.setText(info.getAddr());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
