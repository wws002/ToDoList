package com.csce4623.ahnelson.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_note);
    }

    public void saveNote(View v){
        EditText textTitle = findViewById(R.id.tvNoteTitle);
        String title = textTitle.getText().toString();
        EditText textContent = findViewById(R.id.etNoteContent);
        String content = textContent.getText().toString();

        //Create a ContentValues object
        ContentValues myCV = new ContentValues();
        //Put key_value pairs based on the column names, and the values
        myCV.put(ToDoProvider.TODO_TABLE_COL_TITLE, title);
        myCV.put(ToDoProvider.TODO_TABLE_COL_CONTENT, content);
        //Perform the insert function using the ContentProvider
        getContentResolver().insert(ToDoProvider.CONTENT_URI,myCV);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void deleteNote(View v){
        Intent oldIntent = getIntent();
        Long id = oldIntent.getLongExtra("listItemID", 0);

        int didWork = getContentResolver().delete(Uri.parse(ToDoProvider.CONTENT_URI + "/" + id), null, null);
        if(didWork == 1){
            Toast.makeText(getApplicationContext(), "Deleted Note", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
