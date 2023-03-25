package com.android.keyfortress_hack36;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.keyfortress_hack36.model.Creds;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class CredAdapter extends RecyclerView.Adapter<CredAdapter.CredViewHolder> {

    ArrayList<Creds> credList;
    Context context;

    public CredAdapter(ArrayList<Creds> credList, Context context) {
        this.credList = credList;
        this.context = context;
    }

    @NonNull
    @Override
    public CredViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cred_item, parent, false);
        return new CredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CredViewHolder holder, int position) {
        Creds currentCred = credList.get(position);
        Picasso.get().load(currentCred.getAppImg()).into(holder.appImg);
        holder.username.setText(currentCred.getUsername());

        String appName = currentCred.getAppName();
        appName = appName.toLowerCase();
//        String pkg = "com." + appName +".android";

//        if (appName.equals("instagram"))
//            pkg = "com.instagram.android";

//        Toast.makeText(context, "biometric...", Toast.LENGTH_SHORT).show();

        String finalAppName = appName;
        holder.credcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, finalAppName, Toast.LENGTH_SHORT).show();

                for (int i = 0; i < credList.size(); i++) {
                    Log.i("appname", "onClick: " + credList.get(i).getAppName());
                }

                biometric(finalAppName);
            }
        });
    }

    public void biometric(String finalAppName) {
        BiometricPrompt biometricPrompt;
        BiometricPrompt.PromptInfo promptInfo;

        BiometricManager biometricManager = BiometricManager.from(context);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(context, "No FingerPrint Sensor found!", Toast.LENGTH_SHORT).show();
                break;


            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(context, "Not working!", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(context, "No FingerPrint Assigned!", Toast.LENGTH_SHORT).show();
                break;


        }

        Executor executor = ContextCompat.getMainExecutor(context);

        biometricPrompt = new BiometricPrompt((FragmentActivity) context, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
//                Toast.makeText(context, "Redirecting... Password Copied. use it within 1 minute", Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, finalAppName, Toast.LENGTH_SHORT).show();

                String pkg = "com."+finalAppName+".android";
                PackageManager pm = context.getPackageManager();
                Intent i = pm.getLaunchIntentForPackage(pkg);

                if (i != null)
                    context.startActivity(i);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("KeyFortress")
                .setDescription("Verify your fingerprint to continue logging in to selected app")
                .setDeviceCredentialAllowed(true).build();



        biometricPrompt.authenticate(promptInfo);
    }

    @Override
    public int getItemCount() {
        return credList.size();
    }

    public static class CredViewHolder extends RecyclerView.ViewHolder {

        ImageView appImg;
        TextView username;
        CardView credcard;

        public CredViewHolder(@NonNull View itemView) {
            super(itemView);

            appImg = itemView.findViewById(R.id.app_img);
            username = itemView.findViewById(R.id.username);
            credcard = itemView.findViewById(R.id.credcard);

        }
    }

}
