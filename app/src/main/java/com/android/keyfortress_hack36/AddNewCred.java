package com.android.keyfortress_hack36;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

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

        //TODO: To generate Password through Algorithms

        btnSaveCred.setOnClickListener(view -> {
            String app_name = appName.getText().toString();
            String user_id = userId.getText().toString();
            String pw = PwdGenerator.generatePassword(20);

            AES_Encryption e;
            String encryptedPW = null;
            try {
                e = new AES_Encryption();
                encryptedPW = e.encrypt(pw);
            } catch (Exception ee) {
                ee.printStackTrace();
            }

//            Toast.makeText(this, app_name + user_id, Toast.LENGTH_SHORT).show();
            if(!TextUtils.isEmpty(app_name) && !TextUtils.isEmpty(user_id)){
                Database db = new Database();
                db.addCreds(app_name, user_id, encryptedPW);
            }
            else{
                Toast.makeText(this, "please enter the info", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}