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
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        BiometricManager biometricManager = BiometricManager.from(requireActivity());
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(requireActivity(), "No FingerPrint Sensor found!", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(requireActivity(), "Not working!", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(requireActivity(), "No FingerPrint Assigned!", Toast.LENGTH_SHORT).show();
                break;


        }

        Executor executor = ContextCompat.getMainExecutor(requireActivity());

        biometricPrompt = new BiometricPrompt(requireActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(requireActivity(), "Put Intent to the given app", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("KeyFortress")
                .setDescription("Verify your fingerprint to continue logging in to selected app")
                .setDeviceCredentialAllowed(true).build();

        credList = new ArrayList<Creds>();

        //dummy data
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
        credList.add(new Creds("https://cdn4.iconfinder.com/data/icons/social-messaging-ui-color-shapes-2-free/128/social-instagram-new-square2-512.png", "Instagram", "__sp04", "hello"));
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