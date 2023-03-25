package com.android.keyfortress_hack36;

import androidx.annotation.NonNull;

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
        dbRef.child("Credentials").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()){
                    String key = snap.getKey();
                    if(key != null) {
                        dbRef.child(key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Creds cred = snapshot.getValue(Creds.class);
                                credList.add(0, cred);
                                credAdapter.notifyItemInserted(0);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
