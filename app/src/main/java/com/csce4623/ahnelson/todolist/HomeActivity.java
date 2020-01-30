package com.csce4623.ahnelson.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

//Create HomeActivity and implement the OnClick listener
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initializeComponents();
    }

    //Set the OnClick Listener for buttons
    void initializeComponents(){
        findViewById(R.id.btnNewNote).setOnClickListener(this);
        ListView listView = findViewById(R.id.noteList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editNote(id);
            }
        });

        //Create the projection for the query
        String[] projection = {
                ToDoProvider.TODO_TABLE_COL_ID,
                ToDoProvider.TODO_TABLE_COL_TITLE,
                ToDoProvider.TODO_TABLE_COL_CONTENT};
        //Perform a query to get all rows in the DB
        Cursor myCursor = getContentResolver().query(ToDoProvider.CONTENT_URI,projection,null,null,null);
        ListView lvItems = findViewById(R.id.noteList);
        ToDoCursorAdapter todoAdapter = new ToDoCursorAdapter(this, myCursor);
        lvItems.setAdapter(todoAdapter);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            //If new Note, call createNewNote()
            case R.id.btnNewNote:
                createNewNote();
                break;
            //This shouldn't happen
            default:
                break;
        }
    }

    //Create a new note with the title "New Note" and content "Note Content"
    void createNewNote(){
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    void editNote(long id){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("listItemID", id);
        startActivity(intent);
    }
}
