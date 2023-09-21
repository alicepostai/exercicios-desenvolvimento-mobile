package com.example.simple_paint;

import static android.provider.Settings.System.getString;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {

    Path currentPath;
    Paint currentPaint;
    List<Path> strokeList;
    List<Paint> paintList;
    ColorDrawable currentColor;
    String currentLineStyle = "LINE";
    float initX;
    float initY;
    float lx;
    float ly;

    public SimplePaint(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        paintList = new ArrayList<Paint>();
        strokeList = new ArrayList<Path>();

        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);
        initLayerDraw();
    }

    public void initLayerDraw() {
        currentPaint = new Paint();
        currentPath = new Path();

        currentPaint.setStrokeWidth(20);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setColor(currentColor.getColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < paintList.size(); i++) {
            switch(currentLineStyle) {
                case "LINE":
                    canvas.drawPath(strokeList.get(i), paintList.get(i));
                    canvas.drawPath(currentPath, currentPaint);
                    break;
                case "CIRCLE":
                    canvas.drawCircle(initX, initY,(float) Math.sqrt(Math.pow(lx - initX, 2) + Math.pow(ly - initY, 2))/2, paintList.get(i));
                    break;
                case "SQUARE":
                    canvas.drawRect(new Rect((int)initX,(int)ly,(int)lx,(int)initY), paintList.get(i));
                    break;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lx = event.getX();
        ly = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initX = lx;
                initY = ly;
                currentPath.moveTo(lx, ly);
                currentPath.lineTo(lx, ly);
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(lx, ly);
                break;
            case MotionEvent.ACTION_UP:
                currentPath.lineTo(lx, ly);
                paintList.add(currentPaint);
                strokeList.add(currentPath);
                initLayerDraw();
                break;
        }
        invalidate();
        return true;
    }

    public void openColorPicker(Context context, ImageView colorPickerView) {
        new ColorPickerDialog.Builder(context)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton("confirm",
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setColor(envelope, colorPickerView);
                            }
                        })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true)  // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show();
    }

    private void setColor(ColorEnvelope envelope, ImageView colorPickerView) {
        Color color = Color.valueOf(envelope.getColor());

        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
        colorPickerView.setColorFilter(color.toArgb());
    }

    public void setLineStyle(String lineStyle) {
        currentLineStyle = lineStyle;
    }

    public void undo(){
        if (strokeList.size() > 0){
            strokeList.remove(strokeList.size() - 1);
            paintList.remove(paintList.size() - 1);
        }
        invalidate();
    }

    public void clearScreen(){
        strokeList.clear();
        paintList.clear();
        invalidate();
    }
}
