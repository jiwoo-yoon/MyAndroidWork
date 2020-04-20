package com.lec.android.a009_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Main2Activity extends AppCompatActivity {

    private ImageView btnPlay;
    private ImageView btnPause;
    private ImageView btnResume;
    private ImageView btnStop;
    SeekBar sb; // 음악 재생위치를 나타내는 시크바

    MediaPlayer mp; // 음악재생을 위한 객체

    int pos; //재생위치
    boolean isTracking = false; //드래그중일때 시크바가 자동으로 못움직이게

    class MyThread extends Thread{ // 시크바가 음악이 연주될때 움직일수 있게 스레드로 동작주기
        @Override
        public void run() {
            while(mp.isPlaying()){ //현재 재생중이면
               pos = mp.getCurrentPosition(); // 현재 재생중인 위치 ms (int)
                if(!isTracking){sb.setProgress(pos);} //시크바 이동(내가드래그안할때,자동으로 시스템이 움직이게해준다)
                                                      // --> onProgressChanged() 호출
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnResume = findViewById(R.id.btnResume);
        btnStop = findViewById(R.id.btnStop);
        sb = findViewById(R.id.sb);

        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //sb 값 변경될때 마다 호출
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //음악이 끝까지 연주완료 되었다면
                if(seekBar.getMax() == progress && !fromUser){ // 곡의 진행도(progress)
                    btnPlay.setVisibility(View.VISIBLE);
                    btnPause.setVisibility(View.INVISIBLE);
                    btnResume.setVisibility(View.INVISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                    if(mp != null) mp.stop();
                }
            }

            //드래그(트랙킹) 하면 호출
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isTracking = true;
            }

            //드래그(트랙킹) 마치면 호출
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                pos = seekBar.getProgress();//시크바의 위치를 찾아야된다.
                if(mp != null){mp.seekTo(pos);} // 사용자가 지정한 위치로
                isTracking = false;
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MediaPlayer 객체 초기화, 재생
                mp = MediaPlayer.create(
                        getApplicationContext(), //현재화면의 제어권자
                        R.raw.chacha // 음악파일 리소스
                );
                mp.setLooping(false); // 반복할지 안할지, true:무한반복

                //재생이 끝나면 호출되는 콜백메소드
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Log.d("myapp ", " 연주종료 " + mp.getCurrentPosition() + " /현재재생되는위치 " + mp.getDuration() + " /곡의길이");
                        btnPlay.setVisibility(View.VISIBLE);
                        btnPause.setVisibility(View.INVISIBLE);
                        btnResume.setVisibility(View.INVISIBLE);
                        btnStop.setVisibility(View.INVISIBLE);
                    }
                });

                mp.start(); //재생시작

                int duration = mp.getDuration();//음악의 재생시간(ms)
                sb.setMax(duration);//시크바의 범위를 음악의 재생시간으로 설정해주고
                new MyThread().start();//시크바 스레드 시작

                btnPlay.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //음악종료
                pos = 0;

                if(mp != null){
                    mp.stop(); //재생멈춤
                    mp.seekTo(0); //음악의 처음으로 이동
                    mp.release(); // 자원해제
                    mp = null;
                }
                sb.setProgress(0); //시크바도 초기위치로 돌리기

                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });

        //일시중지, 포즈하는순간 스레드도 멈춤 그래서 resume할때 다시 시작해줘야된다.
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = mp.getCurrentPosition(); // 현재 재생중이던 위치저장하고
                mp.pause(); // 일시정지
                btnPause.setVisibility(View.INVISIBLE);
                btnResume.setVisibility(View.VISIBLE);
            }
        });

        //일시정지된 시점부터 재시작(resume)
        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(pos); // 일시정지했던 저장된 위치로 이동하고
                mp.start(); // 다시 시작
                new MyThread().start(); // 시크바 쓰레드도 다시 시작
                btnPause.setVisibility(View.VISIBLE);
                btnResume.setVisibility(View.INVISIBLE);
            }
        });

    }//end oncreate

    @Override
    protected void onPause() { //mediaplayer가 끝날때 자원해제를 해줄려고
        super.onPause();
        if(mp != null){
            mp.release(); // 자원해제
        }
        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.INVISIBLE);
        btnResume.setVisibility(View.INVISIBLE);
        btnStop.setVisibility(View.INVISIBLE);
    }
}//end main






















