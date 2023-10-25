package com.example.speak_for_me;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    SQLiteDatabase database;
    private TextToSpeech tts;
    private EditText textToSpeak;
    private Button buttonText;
    private ListView speechListView;
    private boolean loadTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = openOrCreateDatabase("SpeechList", getBaseContext().MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS speech_list (_id INTEGER PRIMARY KEY AUTOINCREMENT, speech TEXT )");

        textToSpeak = findViewById(R.id.textToSpeak);
        buttonText = findViewById(R.id.buttonSpeak);
        speechListView = findViewById(R.id.speechListView);

        tts = new TextToSpeech(this, this);
        loadTts = false;

        buttonText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loadTts) {
                    String text = textToSpeak.getText().toString();

                    tts.speak(text, TextToSpeech.QUEUE_ADD, null, null);
                    insertSpeech(text);
                    showSpeeches();
                }
            }
        });

        speechListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedText = adapterView.getItemAtPosition(i).toString();
                textToSpeak.setText(selectedText);
            }
        });

        showSpeeches();
    }

    @Override
    public void onInit(int status) {
        if (TextToSpeech.SUCCESS == status) {
            loadTts = true;
        } else {
            Log.e("TTS", "Failed to initialize TextToSpeak");
        }
    }

    private void insertSpeech(String text) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("speech", text);

        database.insert("speech_list", null, contentValues);

    }

    private void showSpeeches() {
        Cursor cursor = database.rawQuery("SELECT speech FROM speech_list order by _id desc", null);
        cursor.moveToFirst();

        ArrayList<String> speechList = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            String speech = cursor.getString(cursor.getColumnIndex("speech"));
            speechList.add(speech);
            cursor.moveToNext();
        }

        speechListView = findViewById(R.id.speechListView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                speechList);
        speechListView.setAdapter(adapter);
    }

}
