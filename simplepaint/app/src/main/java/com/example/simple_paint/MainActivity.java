package com.example.simple_paint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    SimplePaint simplePaint;
    ImageView ivColorPicker;
    ImageView lineTypeLine;
    ImageView lineTypeCircle;
    ImageView lineTypeSquare;
    ImageView undo;
    ImageView clearScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initColorPicker();
        initSimplePaint();
        initLineTypeLine();
        initLineTypeCircle();
        initLineTypeSquare();
        initUndo();
        initClearScreen();
    }

    private void initColorPicker() {
        ivColorPicker = findViewById(R.id.ivColorPicker);
        ivColorPicker.setOnClickListener(view -> simplePaint.openColorPicker(MainActivity.this, ivColorPicker));
        ivColorPicker.setColorFilter(Color.BLACK);
    }

    private void initSimplePaint() {
        simplePaint = findViewById(R.id.simplePaint);
    }

    private void initLineTypeLine() {
        lineTypeLine = findViewById(R.id.lineTypeLine);
        lineTypeLine.setOnClickListener(view -> simplePaint.setLineStyle("LINE"));
    }

    private void initLineTypeCircle() {
        lineTypeCircle = findViewById(R.id.lineTypeCircle);
        lineTypeCircle.setOnClickListener(view -> simplePaint.setLineStyle("CIRCLE"));
    }

    private void initLineTypeSquare() {
        lineTypeSquare = findViewById(R.id.lineTypeSquare);
        lineTypeSquare.setOnClickListener(view -> simplePaint.setLineStyle("SQUARE"));
    }

    private void initUndo() {
        undo = findViewById(R.id.undo);
        undo.setOnClickListener(view -> simplePaint.undo());
    }

    private void initClearScreen() {
        clearScreen = findViewById(R.id.clearScreen);
        clearScreen.setOnClickListener(view -> simplePaint.clearScreen());
    }

}