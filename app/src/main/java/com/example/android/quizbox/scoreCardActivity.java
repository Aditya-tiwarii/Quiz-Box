package com.example.android.quizbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class scoreCardActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView result,message;
    int correct,wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        result = findViewById(R.id.result);
        message = findViewById(R.id.message);

        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);

        circularProgressBar.setProgress(correct);
        result.setText(correct*2 + "/20");

        if(correct>=9){
            message.setText("CONGO!! EXCELLENT PERFORMANCE");
        }
        else if(correct>=4 && correct<9){
            message.setText("NICELY DONE!!");
        }else {
            message.setText("BETTER LUCK NEXT TIME");
        }

    }

    public void exit(View view) {
        finish();
    }

    public void mainmenu(View view) {
        Intent intent = new Intent(scoreCardActivity.this,dashboardActivity.class);
        startActivity(intent);

    }
}