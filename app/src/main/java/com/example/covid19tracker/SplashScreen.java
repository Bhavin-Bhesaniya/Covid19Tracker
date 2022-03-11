package com.example.covid19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    View first, second, third, fourth, fifth, sixth;
    TextView devBy;

    Animation topAnim, midAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        midAnim = AnimationUtils.loadAnimation(this, R.anim.middle_animation);

        first = findViewById(R.id.firstLine);
        second = findViewById(R.id.secondLine);
        third = findViewById(R.id.thirdLine);
        fourth = findViewById(R.id.fourthLine);
        fifth = findViewById(R.id.fifthLine);
        sixth = findViewById(R.id.sixthLine);
        devBy = findViewById(R.id.nameSp);

        first.setAnimation(topAnim);
        second.setAnimation(topAnim);
        third.setAnimation(topAnim);
        fourth.setAnimation(topAnim);
        fifth.setAnimation(topAnim);
        sixth.setAnimation(topAnim);

        devBy.setAnimation(midAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}