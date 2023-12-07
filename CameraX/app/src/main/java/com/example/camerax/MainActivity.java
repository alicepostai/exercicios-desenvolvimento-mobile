package com.example.camerax;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Uri> takePictureLauncher;
    String [] permissions= new String [] {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
    };

    private Uri uriImage;

    public void checkPermissoes() {
        for (String permission: permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,PackageManager.PERMISSION_GRANTED);
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissoes();

        takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
            if (result) {
                ImageView photo = findViewById(R.id.iconPhoto);
                photo.setImageURI(uriImage);
            } else {

            }
        });

        findViewById(R.id.takePictureBtn).setOnClickListener(view -> {
            takePicture();
        });
    }

    public void takePicture() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File fileName = File.createTempFile(imageFileName, ".jpg", storageDir);
            uriImage = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", fileName);
            takePictureLauncher.launch(uriImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}