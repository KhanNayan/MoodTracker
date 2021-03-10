package com.example.moodTracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class personality_check_Activity extends AppCompatActivity {
    TextView tv_question;
    Button ans_btn1,ans_btn2,ans_btn3,ans_btn4,ques_change_btn;
    LinearLayout btn_layout;
    int count = 0;
    List<personality_model> list;
    int position = 0;
    DatabaseReference query1;
    int score = 0;
    int i;
    int ques_count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_check_);
        ques_change_btn = findViewById(R.id.qsn_change_btn);
        ans_btn1 = findViewById(R.id.ans_btn1);
        ans_btn2 = findViewById(R.id.ans_btn2);
        ans_btn3 = findViewById(R.id.ans_btn3);
        ans_btn4 = findViewById(R.id.ans_btn4);
        tv_question = findViewById(R.id.tv_question);
        btn_layout = findViewById(R.id.btn_layout);

        query1 = FirebaseDatabase.getInstance().getReference("Personality");
        query1.addListenerForSingleValueEvent(valueEventListener);
        list = new ArrayList<>();

/*        for ( i = 0;i<4;i++){
            btn_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    btn_layout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                }
            });
        }*/
        ans_btn1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ans_btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                ques_change_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA7470")));
                ques_change_btn.setEnabled(true);
                ans_btn2.setEnabled(false);
                ans_btn3.setEnabled(false);
                ans_btn4.setEnabled(false);
                score+=0;
            }
        });
        ans_btn2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ans_btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                ques_change_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA7470")));
                ques_change_btn.setEnabled(true);
                ans_btn1.setEnabled(false);
                ans_btn3.setEnabled(false);
                ans_btn4.setEnabled(false);
                score+=1;
            }
        });
        ans_btn3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ans_btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                ques_change_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA7470")));
                //ques_change_btn.setBackgroundColor(Color.parseColor("#FA7470"));
                ques_change_btn.setEnabled(true);
                ans_btn2.setEnabled(false);
                ans_btn1.setEnabled(false);
                ans_btn4.setEnabled(false);
                score+=2;
            }
        });
        ans_btn4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ans_btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
                ques_change_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FA7470")));
                ques_change_btn.setEnabled(true);
                ans_btn2.setEnabled(false);
                ans_btn3.setEnabled(false);
                ans_btn1.setEnabled(false);
                score+=3;
            }
        });

        /*list.add(new personality_model("ques1","a","b","c","d"));
        list.add(new personality_model("ques2","1","b","c","d"));
        list.add(new personality_model("ques3","a-","b","c","d"));
        list.add(new personality_model("ques4","10","b","c","d"));
        list.add(new personality_model("ques5","k1","b","c","d"));*/
        //play_animation(tv_question,0,list.get(position).getQuestion());
        ques_change_btn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                if(list.size()-1>ques_count){
                    count = 0;
                    play_animation(tv_question,0,list.get(position).getQuestion());
                    //play_animation(btn_layout,0);

                    position++;

                    ans_btn1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA07A")));
                    ans_btn2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA07A")));
                    ans_btn3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA07A")));
                    ans_btn4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFA07A")));
                    ans_btn1.setEnabled(true);
                    ans_btn2.setEnabled(true);
                    ans_btn3.setEnabled(true);
                    ans_btn4.setEnabled(true);
                    ques_change_btn.setEnabled(false);
                    Toast.makeText(personality_check_Activity.this, "Your Score: "+ques_count, Toast.LENGTH_SHORT).show();
                    ques_change_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FABFBE")));
                    ques_count++;
                }
                else{
                    Intent intent = new Intent(personality_check_Activity.this,com.example.moodTracker.pieActivity.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    //finish();
                }
            }
        });
    }

    private void play_animation(View view, int value, String data){
        view.animate().alpha(value).scaleX(value).scaleY(value)
                .setDuration(200).setStartDelay(80)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if (value == 0 && count<4){
                            String option = "";
                            if(count==0){
                                option = list.get(position).getOptionA();
                            }
                            else if(count==1){
                                option = list.get(position).getOptionB();
                            }
                            else if(count==2){
                                option = list.get(position).getOptionC();

                            }
                            else if(count==3){
                                option = list.get(position).getOptionD();

                            }
                            play_animation(btn_layout.getChildAt(count),0,option);
                            count++;
                            //position++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {

                        //Data setting
                        ((TextView)view).setText(data);
                        if(value==0){
                            play_animation(view,1,data);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //list.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    personality_model personality_model = snapshot.getValue(personality_model.class);
                    list.add(personality_model);
                    //Toast.makeText(MainActivity.this, ""+model.id, Toast.LENGTH_SHORT).show();
                }
                //adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}