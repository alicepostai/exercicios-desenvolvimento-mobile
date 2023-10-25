package com.example.fragments_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFragB = inflater.inflate(R.layout.fragment_b, container, false);
        TextView textViewMessage = viewFragB.findViewById(R.id.messageSentBox);
        Bundle bundle = getArguments();

        if (bundle != null && bundle.containsKey("message")) {
            String message = bundle.getString("message");
            textViewMessage.setText(message);
        }

        return viewFragB;
    }
}