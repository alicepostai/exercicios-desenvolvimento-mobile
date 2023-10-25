package com.example.fragments_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private FragmentA fragmentA = new FragmentA();
    private FragmentB fragmentB = new FragmentB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragmentA)
                .commit();
    }

    public void openFragmentB(String message) {
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        fragmentB.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragmentB)
                .commit();
    }
}
