package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    // 다이얼로그의 ID를 보기 좋은 상수로 선언해서 사용한다
    final int DIALOG_TEXT = 1;
    final int DIALOG_LIST = 2; // 리스트 형식의 다이얼로그 ID
    final int DIALOG_RADIO= 3; // 하나만 선택할 수 있는 다이얼로그 ID
    final int DIALOG_MULTICHOICE= 4;

    TextView tvResult;

    int choice = -1; //라디오 버튼을 선택했을때 저장하는 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // AlertDialog : Dialog 를 상속받은 자식클래스로
        //다이얼로그를 쉽게 만들수 있도록 지원해주고,
        //Activity 라이프 사이클과 다이얼로그의 라이프사이클을 맞춰줌

        tvResult = findViewById(R.id.tvResult);
        Button btnText = findViewById(R.id.btnText);
        Button btnList = findViewById(R.id.btnList);
        Button btnRadio = findViewById(R.id.btnRadio);
        Button btnMultiChoice = findViewById(R.id.btnMultiChoice);

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_TEXT);
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_LIST);
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_RADIO);
            }
        });

        btnMultiChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert(DIALOG_MULTICHOICE);
            }
        });


    }//end oncreate

    protected AlertDialog.Builder showAlert(int id){ //빌더생성, 세팅까지

        switch (id){
            case DIALOG_TEXT:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

                builder1.setTitle("다이얼로그 제목임")
                        .setMessage("안녕 모두들")
                        .setPositiveButton("긍정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "긍정", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("부정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "부정", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("중립", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "중립", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder1.show();
                return builder1;

            case DIALOG_LIST:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);

                final String [] str = {"사과", "딸기", "수박", "배"};

                builder2.setTitle("리스트 형식 다이얼로그 제목")
                        .setNegativeButton("취소", null)
                        .setItems(str, // 리스트목록에 사용할 배열
                                new DialogInterface.OnClickListener() {
                            //리스트 아이템이 선택되었을때 호출되는 콜백
                            // which : 몇번째 선택되었는지에 대한 값
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "선택은 ? " + str[which], Toast.LENGTH_SHORT).show();
                            }
                        });
                builder2.show();
                return builder2;

            case DIALOG_RADIO:
                //커스텀 스타일 적용, styles.xml 편집해서 불러오기
                AlertDialog.Builder builder3 = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));

                final String [] str2 = {"빨강", "녹색", "파랑"};

                builder3.setTitle("색을고르세여")
                        .setPositiveButton("선택완료", //선택했을때 호출되는 콜백, which : 몇번째인지 선택
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), str2[choice]+"을 선택했네만", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setSingleChoiceItems(
                                str2, // 리스트 배열목록
                                -1, //기본설정값(-1 은 아무것도 선택안한것)
                                new DialogInterface.OnClickListener() {
                                    //라디오버튼에서 선택했을때 호출되는 곳
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        choice = which; //라디오버튼을 눌렀을때 (which) choice에 담아주고 선택완료메소드에 choice값을 넘겨줘서 보이게 해준다.
                                    }
                                }
                        );
                builder3.show();
                return builder3;

            case DIALOG_MULTICHOICE :
                AlertDialog.Builder builder4 = new AlertDialog.Builder(this);

                final String [] data = {"한국", "북한", "소련", "영국"};
                final boolean [] checked = {true, false, true, false};

                builder4.setTitle("멀티초이스 다이얼로그 제목")
                        .setPositiveButton("선택완료",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String str = "선택된 값은 : ";

                                        for(int i = 0; i < checked.length; i++){
                                            if(checked[i]){
                                                str = str + data[i] + " ,";
                                            }
                                        }

                                        tvResult.setText(str);

                                    }
                                })
                        .setNegativeButton("취소", null)
                        .setMultiChoiceItems(
                                data, //체크박스 리스트 항목
                                checked, //초기값(선택여부)
                                //체크박스 선택했을때 호출되는콜백
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                        checked[which] = isChecked;
                                    }
                                }
                        );
                builder4.show();
                return builder4;
        }//end switch
     return null;
    }


}//end main