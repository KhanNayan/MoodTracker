package com.example.moodTracker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.Calendar;

public class UserProfileActivity extends AppCompatActivity {
    String name = "", email = "", contact = "", provider = "";
    Uri photoUrl;
    TextView full_name, loggedInWith;
    com.google.android.material.textfield.TextInputLayout et_name, et_email, et_contact, et_dob;
    com.google.android.material.textfield.TextInputEditText dob_click;
    ImageView profile_image;
    Button update, signOut;
    private int datePickerDial = 999;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        /*name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        contact = getIntent().getStringExtra("contact");
        provider = getIntent().getStringExtra("provider");
        photoUrl = getIntent().getData();*/
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        name = user.getDisplayName().toString();
        email = user.getEmail().toString();
        contact = user.getPhoneNumber().toString();
        provider = user.getIdToken(false).getResult().getSignInProvider().toString();
        photoUrl = user.getPhotoUrl();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /*if(snapshot.exists()) {
                    User userInfo = snapshot.getValue(User.class);
                    assert userInfo != null;
                    if(et_contact.getEditText().getText().toString().isEmpty()) {
                        et_contact.getEditText().setText(userInfo.Phone.toString());
                    }
                    if(et_dob.getEditText().getText().toString().isEmpty()) {
                        et_dob.getEditText().setText(userInfo.Dob.toString());
                    }
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Binding
        full_name = findViewById(R.id.fullName_field);
        loggedInWith = findViewById(R.id.loggedInWith);
        et_name = findViewById(R.id.full_name_profile);
        et_email = findViewById(R.id.email_profile);
        et_contact = findViewById(R.id.contact_number_profile);
        et_dob = findViewById(R.id.dob_profile);
        dob_click = findViewById(R.id.dob_click);
        profile_image = findViewById(R.id.profile_image);
        update = findViewById(R.id.btn_update);
        signOut = findViewById(R.id.btn_sign_out);

        // updating values
        if(name != null) {
            full_name.setText(name);
            et_name.getEditText().setText(name);
        }else{
            name = "";
        }
        if(email != null) {
            et_email.getEditText().setText(email);
        }else{
            email = "";
        }
        if(contact != null) {
            et_contact.getEditText().setText(contact);
        }else{
            contact = "";
        }
        //profile_image.setImageURI(photoUrl);
        if(photoUrl != null) {
            Glide.with(getBaseContext())
                    .load(photoUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profile_image);
        }

        // Date of birth current time show
        if(et_dob.getEditText().getText().toString().isEmpty()){
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);

            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);
            showDate(year, month+1, day);
        }

        // Viewing with which provider user signed in
        signInProviders();

        // Alternate info from realtime database firebase

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance()
                        .signOut(UserProfileActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                FirebaseAuth.getInstance().signOut();
                                Toast.makeText(UserProfileActivity.this, "Signed out successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(UserProfileActivity.this, LoginAuthActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Users Full name change
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(et_name.getEditText().getText().toString())
                        .build();

                assert user != null;
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                String DOB, phone;
                DOB = et_dob.getEditText().getText().toString();
                if(et_contact.getEditText().getText().toString().isEmpty()) {
                    phone = et_contact.getEditText().getText().toString();
                }
                else {
                    phone = "00-00";
                }
                //phone = et_contact.getEditText().getText().toString();
                User addUser = new User(
                        name, 
                        email, 
                        DOB, 
                        phone,
                        photoUrl.toString(),
                        provider);
                
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(user.getUid())
                        .setValue(addUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(UserProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        dob_click.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                setDate();
            }
        });
    }
    @SuppressWarnings("deprecation")
    public void setDate() {
        showDialog(datePickerDial);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == datePickerDial) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int i, int day) {
        et_dob.getEditText().setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void signInProviders() {
        if(provider.contains("google")){
            loggedInWith.setText("By Google");
        }
        else if(provider.contains("facebook")){
            loggedInWith.setText("By Facebook");
        }
        else if(provider.contains("phone")){
            loggedInWith.setText("By Phone Number");
            full_name.setText("Unknown");
            profile_image.setImageResource(R.drawable.profile_sample);
        }
        else if(provider.contains("twitter")){
            loggedInWith.setText("By Twitter");
        }
        else if(provider.contains("password")){
            loggedInWith.setText("By E-mail");
            if(photoUrl == null){
                profile_image.setImageResource(R.drawable.profile_sample);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}