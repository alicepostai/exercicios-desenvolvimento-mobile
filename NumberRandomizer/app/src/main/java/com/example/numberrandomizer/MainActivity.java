package com.example.numberrandomizer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button drawButton = findViewById(R.id.drawButton);
        TextView randomizedNumberField = findViewById(R.id.randomizedNumber);
        EditText firstNumberField = findViewById(R.id.firstNumber);
        EditText lastNumberField = findViewById(R.id.lastNumber);

        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer firstNumber = Integer.parseInt(firstNumberField.getText().toString());
                    Integer lastNumber = Integer.parseInt(lastNumberField.getText().toString());

                    String randomizedNumber = Integer.toString(randomizeNumber(firstNumber, lastNumber));

                    randomizedNumberField.setText(randomizedNumber);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Há um problema com os números inseridos, favor verificar.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Integer randomizeNumber(Integer firstNumber, Integer lastNumber) {
        return ThreadLocalRandom.current().nextInt(firstNumber, lastNumber);
    }
}