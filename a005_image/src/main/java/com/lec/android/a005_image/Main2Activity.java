package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);
        ImageView iv3 = findViewById(R.id.iv3);

        // 방법1. 프로젝트 내의 res/drawable 폴더에 있는 파일을 보여주는 방법
        iv1.setImageResource(R.drawable.a2);

        // 방법2. Drawable 객체를 이용해서 보여주는 방법
        //Drawable drawable = getResources().getDrawable(R.drawable.a3);
        //버전23 이상 부터 getDrawble은 사라질 수 있는 메소드(deprecated)라 밑에 있는 코드로 작성한다
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.a3);
        //현재시작하고있는 객체를 Context, 현재는 액티비티(getApplication 해당부분)
        iv2.setImageDrawable(drawable);

        // 방법3 bitmap 객체를 이용해서 보여주는 방법
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.a4);
        iv3.setImageBitmap(bm);

    }//end oncreate
}//end main
