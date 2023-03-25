package com.android.keyfortress_hack36;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.keyfortress_hack36.auth.GoogleSignIn;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar()).hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, PasswordManagerActivity.class));
                finish();
            }
        }, 1000);
    }
}