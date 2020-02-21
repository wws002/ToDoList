package com.csce4623.ahnelson.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent oldIntent = getIntent();
        Long id = oldIntent.getLongExtra("listItemID",0);

        String[] projection = {
                ToDoProvider.TODO_TABLE_COL_ID,
                ToDoProvider.TODO_TABLE_COL_TITLE,
                ToDoProvider.TODO_TABLE_COL_CONTENT};

        Cursor myCursor = getContentResolver().query(ToDoProvider.CONTENT_URI,projection, "_id = "+id.toString(),null,null);

        myCursor.moveToFirst();
        int index = myCursor.getColumnIndexOrThrow("TITLE");
        String title = myCursor.getString(index);
        index = myCursor.getColumnIndexOrThrow("CONTENT");
        String content = myCursor.getString(index);

        EditText textTitle = findViewById(R.id.eNoteTitle);
        EditText textContent = findViewById(R.id.eNoteContent);

        textTitle.setText(title);
        textContent.setText(content);
    }

    public void saveEditedNote(View v){
        //get note info
        Intent oldIntent = getIntent();
        Long id = oldIntent.getLongExtra("listItemID", 0);
        EditText textTitle = findViewById(R.id.eNoteTitle);
        EditText textContent = findViewById(R.id.eNoteContent);
        String title = textTitle.getText().toString();
        String content = textContent.getText().toString();

        //create content values
        ContentValues myCV = new ContentValues();
        myCV.put(ToDoProvider.TODO_TABLE_COL_TITLE, title);
        myCV.put(ToDoProvider.TODO_TABLE_COL_CONTENT, content);

        //update the note
        getContentResolver().update(ToDoProvider.CONTENT_URI, myCV, "_id = "+id.toString(), null);

        //start home activity
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void deleteEditedNote(View v){
        Intent oldIntent = getIntent();
        Long id = oldIntent.getLongExtra("listItemID",0);

        //Delete the note
        int didWork = getContentResolver().delete(Uri.parse(ToDoProvider.CONTENT_URI + "/" + id), null, null);
        //If deleted, didWork returns the number of rows deleted (should be 1)
        if (didWork == 1) {
            //If it didWork, then create a Toast Message saying that the note was deleted
            Toast.makeText(getApplicationContext(), "Deleted Note", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
