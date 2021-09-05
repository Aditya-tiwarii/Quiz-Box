package com.example.android.quizbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class splashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite FoldingCube = new ThreeBounce();
        progressBar.setIndeterminateDrawable(FoldingCube);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreenActivity.this,loginActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}