package com.android.keyfortress_hack36;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Objects;

public class PasswordManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_manager);

        Objects.requireNonNull(getSupportActionBar()).hide();

        BottomNavigationView navbar = findViewById(R.id.navbar);

        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

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

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, selectedFragment).addToBackStack(null).commit();
                return true;
            }
        });
    }
}