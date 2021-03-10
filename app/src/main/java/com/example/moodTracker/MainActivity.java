package com.example.moodTracker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    RecyclerView rcview ;
    rvadapter adapter;
    private List<model> quesList;
    DatabaseReference dbreff,query1;
    TextView tv_signin, nextIntent;
    String nextValue;
    int next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lazyLoader = findViewById(R.id.three_dot);
        rcview = (RecyclerView)findViewById(R.id.rv);
        //tv_signin = findViewById(R.id.tv_SignIn);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setStackFromEnd(true);
        rcview.setLayoutManager(linearLayoutManager);
        quesList = new ArrayList<>();
        adapter = new rvadapter(this,quesList);
        rcview.setAdapter(adapter);
       /* final int value = getIntent().getIntExtra("value",0);
        nextIntent.setText(value);
        nextIntent.setVisibility(View.VISIBLE);*/


        /*tv_signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.moodTracker.LoginAuthActivity.class);
                startActivity(intent);
            }
        });*/


        //query1 = FirebaseDatabase.getInstance().getReference("QuesMt");

      /*  Query q1 = FirebaseDatabase.getInstance().getReference("QuesMt").orderByChild("id").equalTo("i1");
        q1.addListenerForSingleValueEvent(valueEventListener);*/



        /*Query q1 = FirebaseDatabase.getInstance()
                .getReference("QuesMt")
                .orderByChild("id")
                .equalTo("i1");*/
        //q1.addListenerForSingleValueEvent(valueEventListener);
        query1 = FirebaseDatabase.getInstance().getReference("QuesMt");
        query1.addValueEventListener(valueEventListener);
        //query1 = FirebaseDatabase.getInstance().getReference("QuesMt");
        /*next = 0;
        int i=2;
        while(true){
            if(next == 1){
                rcview.setAdapter(adapter);
                Query q2 = FirebaseDatabase.getInstance().getReference("QuesMt").
                        orderByChild("id").equalTo("i"+Integer.toString(i));
                q2.addListenerForSingleValueEvent(valueEventListener);
                i++;
            }
            next = adapter.getNext();

        }*/

        /*Query q2 = FirebaseDatabase.getInstance().getReference("QuesMt").
                orderByChild("id").equalTo("i2");
        q2.addListenerForSingleValueEvent(valueEventListener);*/









    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            quesList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    model model = snapshot.getValue(model.class);
                    quesList.add(model);
                    //Toast.makeText(MainActivity.this, ""+model.id, Toast.LENGTH_SHORT).show();
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(MainActivity.this, "Error getting data", Toast.LENGTH_SHORT).show();
        }
    };
}