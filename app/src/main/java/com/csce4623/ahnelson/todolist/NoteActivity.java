package com.csce4623.ahnelson.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
