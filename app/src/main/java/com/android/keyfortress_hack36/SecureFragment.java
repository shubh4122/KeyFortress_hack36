package com.android.keyfortress_hack36;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.android.keyfortress_hack36.model.Creds;

import java.util.ArrayList;

public class SecureFragment extends Fragment {


    private ArrayList<Creds> credList;
    private RecyclerView recyclerView;
    private CredAdapter credAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Database db;
    private ProgressBar postLoaderBar;
    private ImageButton addNewCred;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        credList = new ArrayList<Creds>();

        //dummy data
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));

        View view = inflater.inflate(R.layout.fragment_secure, container, false);
        setUpRecyclerView(view);
//        db.readCreds(credList, credAdapter);

        addNewCred = view.findViewById(R.id.btnAddNewCred);
        // Redirects to other activity where new cred can be added.
        addNewCred.setOnClickListener(v -> {
            Intent intent = new Intent(this.getActivity(), AddNewCred.class);
            startActivity(intent);
        });

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