package com.android.keyfortress_hack36.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.keyfortress_hack36.R;

public class LearnFragment extends Fragment {

    VideoView videoView1, videoView2;
    CardView cdPhishing, cdMalAttack, cdDDoSAttack, cdBnkFraud;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        videoView1 = view.findViewById(R.id.videoView1);
        videoView2 = view.findViewById(R.id.videoView2);

        cdPhishing = view.findViewById(R.id.cardView1);
        cdMalAttack = view.findViewById(R.id.cardView2);
        cdDDoSAttack = view.findViewById(R.id.cardView3);
        cdBnkFraud = view.findViewById(R.id.cardView4);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.cardView1:
                        Toast.makeText(getActivity(), "Phishing", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.cardView2:
                        Toast.makeText(getActivity(), "MalAttack", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.cardView3:
                        Toast.makeText(getActivity(), "DDos", Toast.LENGTH_SHORT).show();
                        break;


                    case R.id.cardView4:
                        Toast.makeText(getActivity(), "Bank fraud", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        cdPhishing.setOnClickListener(onClickListener);
        cdBnkFraud.setOnClickListener(onClickListener);
        cdDDoSAttack.setOnClickListener(onClickListener);
        cdMalAttack.setOnClickListener(onClickListener);

        videoView1.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.vid1));
        if(!videoView2.isPlaying()) videoView1.start();

        videoView2.setVideoURI(Uri.parse("android.resource://" + getContext().getPackageName() + "/" + R.raw.vid2));
        if(!videoView1.isPlaying()) videoView2.start();

        return view;
    }

}