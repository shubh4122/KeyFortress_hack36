package com.android.keyfortress_hack36;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewCred extends AppCompatActivity {

    EditText appName, userId;
    Button btnSaveCred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_cred);

        appName = findViewById(R.id.etAppName);
        userId = findViewById(R.id.etUserId);
        btnSaveCred = findViewById(R.id.btnSaveCred);

        String app_name = appName.getText().toString();
        String user_id = userId.getText().toString();

        //TODO: To generate Password through Algorithms

        btnSaveCred.setOnClickListener(view -> {
            Database db = new Database();
            db.addCreds(app_name, user_id, "dsfjh2321434bgfdfdgbf");
        });
    }
}