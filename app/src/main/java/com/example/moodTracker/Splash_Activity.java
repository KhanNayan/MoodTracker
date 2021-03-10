package com.example.moodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Activity extends AppCompatActivity {
    TextView tv1;
    ImageView iv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
        //Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transition);
        tv1 = findViewById(R.id.tv1);
        iv1 = findViewById(R.id.iv1);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transition);

        tv1.startAnimation(myanim);
        iv1.startAnimation(myanim);
        final Intent i = new Intent(Splash_Activity.this,com.example.moodTracker.MainActivity.class);

        Thread timer = new Thread(){
            public void run (){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}