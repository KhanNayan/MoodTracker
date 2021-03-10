package com.example.moodTracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class FunctionalActivity extends AppCompatActivity {
    Button btn_profile, btn_queries,stress_btn;
    String name, email, contact, provider;
    Uri photoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functional);

        /*name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra("contact");
        provider = getIntent().getStringExtra("provider");
        photoUrl = getIntent().getData();*/

        btn_profile = findViewById(R.id.btn_profile);
        btn_queries = findViewById(R.id.btn_queries);
        stress_btn = findViewById(R.id.stress_btn);

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(FunctionalActivity.this,UserProfileActivity.class);
                /*intent.putExtra("name", user.getDisplayName().toString());
                intent.putExtra("email", user.getEmail().toString());
                intent.putExtra("provider", user.getIdToken(false).getResult().getSignInProvider().toString());
                intent.putExtra("contact", user.getPhoneNumber().toString());
                intent.setData(user.getPhotoUrl());*/
                startActivity(intent);
            }
        });
        btn_queries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FunctionalActivity.this, personality_check_Activity.class));
            }
        });
        stress_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FunctionalActivity.this,Stress_activity.class);
                startActivity(intent);
            }
        });
    }
}