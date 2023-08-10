package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView weightInput = findViewById(R.id.weightTextBox);
        TextView heightInput = findViewById(R.id.heightTextBox);
        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showResultScreen = new Intent(MainActivity.this, ResultScreen.class);
                Double weight = Double.parseDouble(weightInput.getText().toString());
                Double height = Double.parseDouble(heightInput.getText().toString());
                showResultScreen.putExtra("weight", weight);
                showResultScreen.putExtra("height", height);
                startActivity(showResultScreen);
            }
        });
    }
}