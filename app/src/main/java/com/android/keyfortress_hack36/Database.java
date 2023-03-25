package com.android.keyfortress_hack36;

import com.android.keyfortress_hack36.model.Creds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private DatabaseReference dbRef;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private String currentUserId;

    public Database(){
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) {
            currentUserId = currentUser.getUid();
            dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        }
    }

    public void addCreds(String appName, String userId, String password){
        Map<String, Object> credMap = new HashMap<>();
        credMap.put("App_Name", appName);
        credMap.put("User_Id", userId);
        credMap.put("Password", password);

        dbRef.child("Credentials").child(appName + "_" + userId).updateChildren(credMap);
    }

    public void readCreds(ArrayList<Creds> credList, CredAdapter credAdapter) {
    }
}
