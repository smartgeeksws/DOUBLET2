package com.example.lionsoft.doublet;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplahScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);

        CountDownTimer countDownTimer = new CountDownTimer(2000,2000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplahScreen.this, PantallaLogin.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
