package com.example.ptu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;

public class MainTodoList extends MainActivity {
    Intent TakeActiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_todo_list);
        File internalStorageDir = getFilesDir();
    }

    public void TakeTodoList(View v) {
        TakeActiv = new Intent(this, TodoList.class);
        switch (v.getId()){
            case R.id.todoListWork:
                TakeActiv.putExtra("base", "workBase");
                TakeActiv.putExtra("title", "Дела по работе");
                break;
            case R.id.todoListHome:
                TakeActiv.putExtra("base", "homeBase");
                TakeActiv.putExtra("title", "Домашние дела");
                break;
        }
        startActivity(TakeActiv);
    }
}
