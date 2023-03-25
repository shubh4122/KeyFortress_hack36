package com.android.keyfortress_hack36;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.keyfortress_hack36.model.Creds;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;

public class SecureFragment extends Fragment {


    private ArrayList<Creds> credList;
    private RecyclerView recyclerView;
    private CredAdapter credAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Database db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        credList = new ArrayList<Creds>();

        //dummy data
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://www.freeiconspng.com/thumbs/logo-twitter-png/logo-twitter-icon-symbol-0.png", "Twitter", "__sp04", "hello"));
        credList.add(new Creds("https://cdn-icons-png.flaticon.com/512/174/174857.png", "LinkedIn", "__sp04", "hello"));
        credList.add(new Creds("https://seeklogo.com/images/S/snapchat-logo-F20CDB1199-seeklogo.com.png", "Snapchat", "__sp04", "hello"));


        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));

        View view = inflater.inflate(R.layout.fragment_secure, container, false);
        setUpRecyclerView(view);
//        db.readCreds(credList, credAdapter); //use when connected to DB
        return view;
    }

    private void setUpRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.credRecyclerview);
        layoutManager = new LinearLayoutManager(getActivity());
        credAdapter = new CredAdapter(credList, getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(credAdapter);
    }

}