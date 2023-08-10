package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        DecimalFormat formatter = new DecimalFormat("0.0");
        TextView weightResultTextBox = findViewById(R.id.weightResultTextBox);
        TextView heightResultTextBox = findViewById(R.id.heightResultTextBox);
        TextView BMIResult = findViewById(R.id.BMIResult);
        TextView BMIResultDescription = findViewById(R.id.BMIResultDescription);

        Bundle bundle = getIntent().getExtras();
        Double weightInput = bundle.getDouble("weight");
        Double heightInput = bundle.getDouble("height");

        Double bmi = calculateBMI(weightInput, heightInput);
        String bmiDescription = getBMIDescription(bmi);

        weightResultTextBox.setText(weightInput.toString());
        heightResultTextBox.setText(heightInput.toString());
        BMIResult.setText(formatter.format(bmi).toString());
        BMIResultDescription.setText(bmiDescription);
    }

    private Double calculateBMI(Double weight, Double height) {
        return weight / (height * height);
    }

    private String getBMIDescription(Double bmi) {
        if (bmi <= 18.5) {
            return "Abaixo do peso";
        } else if (bmi < 25) {
            return "Peso ideal";
        } else if (bmi < 30) {
            return "Levemente acima do peso";
        } else if (bmi < 35) {
            return "Obesidade grau 1";
        } else if (bmi < 40) {
            return "Obesidade grau 2 (severa)";
        } else if (bmi >= 40) {
            return "Obesidade grau 3 (morbida)";
        }
        return "";
    }
}