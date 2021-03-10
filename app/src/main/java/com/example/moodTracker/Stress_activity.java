package com.example.moodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class Stress_activity extends AppCompatActivity {
    TextView breathTxt, breathTxtHint;
    Button btnBreath;
    private CountDownTimer countDownTimer;
    private long timer = 10000;
    private boolean running;
    Chronometer chronometer;
    GifImageView gifImg;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_activity);

        btnBreath = findViewById(R.id.btnBreath);
        breathTxt = findViewById(R.id.breathTxt);
        chronometer = findViewById(R.id.chronometer);
        breathTxtHint = findViewById(R.id.breathTxtHint);
        gifImg = findViewById(R.id.gifImg);
        img = findViewById(R.id.img);

        btnBreath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!running)
                {
//                    chronometer.setBase(SystemClock.elapsedRealtime());
//                    chronometer.start();
                    gifImg.setVisibility(View.VISIBLE);
                    img.setVisibility(View.GONE);
                    running = true;
                    btnBreath.setText("Pause");
                    breathTxt.setText("Exercise it!");
                }
                else
                {
//                    chronometer.setBase(SystemClock.elapsedRealtime());
//                    chronometer.start();
                    gifImg.setVisibility(View.GONE);
                    img.setVisibility(View.VISIBLE);
                    running = false;
                    btnBreath.setText("Start");
                    breathTxt.setText("Exercise it!");
                }


            }
        });

    }

}