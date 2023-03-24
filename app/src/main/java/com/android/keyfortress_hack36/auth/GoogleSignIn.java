package com.android.keyfortress_hack36.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.keyfortress_hack36.MainActivity;
import com.android.keyfortress_hack36.R;
import com.android.keyfortress_hack36.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class GoogleSignIn extends AppCompatActivity {

    public static final int RC_SIGN_IN = 100;
    GoogleSignInClient gsc;
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    String name, email;

    Button btnSignIn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);

        Objects.requireNonNull(getSupportActionBar()).hide();

        mAuth = FirebaseAuth.getInstance();
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(view -> {

            progressDialog = new ProgressDialog(GoogleSignIn.this);
            progressDialog.setMessage("Google Sign In..");
            progressDialog.show();

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("1089741073383-q8lpllspjfvr1s7g2mig4v88d2rbtu6n.apps.googleusercontent.com")
                    .requestEmail()
                    .build();

            gsc = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(GoogleSignIn.this, gso);
            gsc.revokeAccess();

            Intent signInIntent = gsc.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    email = account.getEmail();
                    if (validateEmail(email)) {
                        firebaseAuthWithGoogle(account.getIdToken());
                    } else {
                        progressDialog.dismiss();
                        gsc.signOut();
                        Toast.makeText(GoogleSignIn.this, "Please use correct mail-id", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (ApiException e) {
                progressDialog.dismiss();
                Toast.makeText(this, "Select Email-Id to continue", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        updateUserInfo();

                        Intent intent = new Intent(GoogleSignIn.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(GoogleSignIn.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void updateUserInfo() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String currentUserId;

        if(currentUser!=null){
            currentUserId = currentUser.getUid();
            mRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

            GoogleSignInAccount account = com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this);
            if(account!=null){
                name = account.getGivenName();
                email = account.getEmail();
                User user = new User(name, email);
                mRef.setValue(user);
            }
        }
    }

    public boolean validateEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}