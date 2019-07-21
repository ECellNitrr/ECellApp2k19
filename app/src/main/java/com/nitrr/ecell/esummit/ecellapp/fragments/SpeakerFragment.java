package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.ArrayList;
import java.util.Objects;

import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;

public class SpeakerFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView company;
    private TextView year;
    private TextView email;
    private TextView contact;
    private TextView socialMedia;
    private BroadcastReceiver receiver;

    public SpeakerFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker_detail, container, false);
        ArrayList<String> data = this.getArguments() != null ? this.getArguments().getStringArrayList("data") : null;
        if (data != null) {
            initialize(view);
            setData(data.get(0), data.get(1), data.get(2), this.getArguments().getInt("year"),
                    data.get(3), data.get(4), data.get(5));
        }
        return view;
    }


    private void initialize(View v) {
        name = v.findViewById(R.id.detail_speaker_name);
        image = v.findViewById(R.id.detail_speaker_image);
        company = v.findViewById(R.id.speaker_company);
        email = v.findViewById(R.id.speaker_email);
        year = v.findViewById(R.id.detail_speaker_year);
        socialMedia = v.findViewById(R.id.speaker_social_media);
        contact = v.findViewById(R.id.speaker_contact);
    }

    private void setData(String image, String name, String company, int year, String email, String contact, String socialMedia) {
        try{
            if(image != null){
                Glide.with(Objects.requireNonNull(getContext())).load(image).apply(RequestOptions.circleCropTransform()).into(this.image);}
        }
        catch(Exception e){
            setData(image, name, company, year, email, contact, socialMedia);
        }
        this.name.setText(name);
        this.company.setText(company);
        this.year.setText((Integer.toString(year)));
        this.email.setText(email);
        this.contact.setText(contact);
        this.socialMedia.setText(socialMedia);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        Objects.requireNonNull(getContext()).registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void onDestroy() {
        if(receiver !=null){
            Objects.requireNonNull(getContext()).unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }
}