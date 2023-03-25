package com.android.keyfortress_hack36;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.keyfortress_hack36.model.Creds;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    }

    @Override
    public int getItemCount() {
        return credList.size();
    }

    public static class CredViewHolder extends RecyclerView.ViewHolder {

        ImageView appImg;
        TextView username;

        public CredViewHolder(@NonNull View itemView) {
            super(itemView);

            appImg = itemView.findViewById(R.id.app_img);
            username = itemView.findViewById(R.id.username);
        }
    }

}
