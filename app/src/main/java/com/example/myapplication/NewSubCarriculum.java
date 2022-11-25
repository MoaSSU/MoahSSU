package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewSubCarriculum extends AppCompatActivity implements View.OnClickListener{
    EditText name;
    EditText time;
    EditText description;
    Button save;
    Button cancel;
    String curriculumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sub_carriculum);
        Intent intent = getIntent();
        curriculumId = intent.getStringExtra("curriculumId");

        name = (EditText) findViewById(R.id.subName);
        time = (EditText) findViewById(R.id.subTime);
        description = (EditText) findViewById(R.id.subDescription);
        save = (Button) findViewById(R.id.subSave);
        cancel = (Button) findViewById(R.id.subcancel);

    }

    @Override
    public void onClick(View view) {
        if(view == save){
            String subname = name.getText().toString();
            String subtime = time.getText().toString();
            String subdescription = description.getText().toString();

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into subcurriculum (name,time,description,curriculumId) values (?,?,?,?)",new String[]{subname,subtime,subdescription,curriculumId});
            db.close();

        }

    }
}