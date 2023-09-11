package com.example.fragments_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentA extends Fragment {

    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewFragA = inflater.inflate(R.layout.fragment_a, container, false);
        Button buttonSend = viewFragA.findViewById(R.id.sendButton);
        EditText editTextMessage = viewFragA.findViewById(R.id.messageTextBox);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editTextMessage.getText().toString();
                if (!message.isEmpty()) {
                    ((MainActivity) requireActivity()).openFragmentB(message);
                }
            }
        });

        return viewFragA;
    }
}
