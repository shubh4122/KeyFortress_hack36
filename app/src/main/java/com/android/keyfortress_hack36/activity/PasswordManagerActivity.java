package com.android.keyfortress_hack36.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.keyfortress_hack36.R;
import com.android.keyfortress_hack36.auth.GoogleSignIn;
import com.android.keyfortress_hack36.fragment.LearnFragment;
import com.android.keyfortress_hack36.fragment.ProtectFragment;
import com.android.keyfortress_hack36.fragment.SecureFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class PasswordManagerActivity extends AppCompatActivity {

    Fragment selectedFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);

        Objects.requireNonNull(getSupportActionBar()).hide();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new SecureFragment()).commit();
            selectedFragment = new SecureFragment();
        }

        BottomNavigationView navbar = findViewById(R.id.navbar);

        navbar.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.secure_pwm:
                            selectedFragment = new SecureFragment();
                            break;

                        case R.id.protect_frag:
                            selectedFragment = new ProtectFragment();
                            break;

                        case R.id.learn_frag:
                            selectedFragment = new LearnFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(PasswordManagerActivity.this, GoogleSignIn.class);
            startActivity(intent);
        }
    }

}