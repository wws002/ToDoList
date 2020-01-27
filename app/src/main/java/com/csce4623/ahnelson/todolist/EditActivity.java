package com.csce4623.ahnelson.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

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
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
