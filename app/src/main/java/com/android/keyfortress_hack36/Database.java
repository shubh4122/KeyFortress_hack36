package com.android.keyfortress_hack36;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.keyfortress_hack36.model.Creds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private DatabaseReference dbRef;
    private DatabaseReference credRef;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String currentUserId;

    public Database(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            currentUserId = currentUser.getUid();
            dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
            credRef = dbRef.child("Credentials");
        }
    }

    public void addCreds(String appName, String userId, String password){
        Creds credObj = new Creds(appName, userId, password);
        credRef.push().setValue(credObj);
    }

    public void readCreds(ArrayList<Creds> credList, CredAdapter credAdapter) {
        credRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()) {
                    Log.i("snap", (String) snapshot.child("appName").getValue());
                    Creds cred = snapshot.getValue(Creds.class);
                    credList.add(0, cred);
                    credAdapter.notifyItemInserted(0);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
