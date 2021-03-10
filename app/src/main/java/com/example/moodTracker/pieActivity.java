package com.example.moodTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class pieActivity extends AppCompatActivity {

    TextView tvPie;
    int actual_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        tvPie = findViewById(R.id.tvPie);
        int i = getIntent().getIntExtra("score",0);
        /*String messages = intent.getStringExtra("score");*/
        /*float i=Float.parseFloat(messages);*/
        actual_score = 15 - i;
        int set_score = 20 - actual_score;
        tvPie.setText("Your expected score is "+String.valueOf(actual_score));

        if (actual_score > 16)
        {
            Toast.makeText(this, "You are depressed!", Toast.LENGTH_SHORT).show();
        }

        setupPaiChart(actual_score, set_score);
    }

    private void setupPaiChart(int actual_score, int set_score) {
        int rainfall[] = {actual_score, set_score};
        String mood[] = {"Actual Depression", "And the rest"};
        //populating a list of pieEntries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0 ; i<rainfall.length ; i++)
        {
            pieEntries.add(new PieEntry(rainfall[i], mood[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Rainfall for Vancuver");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        //get the chart
        PieChart chart = findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(1000);
        chart.invalidate();
    }
}