package com.example.list_view_task;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button okButton = findViewById(R.id.okButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        ListView taskList = findViewById(R.id.taskList);
        TextView textInput = findViewById(R.id.textInput);
        ArrayList<String> tasks = new ArrayList<>();

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_selectable_list_item,
                android.R.id.text1,
                tasks
        );

        taskList.setOnItemClickListener(this);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.add(textInput.getText().toString());
                taskList.setAdapter(adapter);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("Delete");
                ab.setMessage("Excluir item " + (position+1) + "?");
                ab.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tasks.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                ab.setNegativeButton("Não", null);
                ab.show();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
    }

    private void deleteItem() {

    }
}