package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Main extends AppCompatActivity implements AdapterView.OnItemClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View 세팅
        EditText searchCuriculum = findViewById(R.id.search_bar);
        ListView mainListView = findViewById(R.id.main_listview);
        mainListView.setOnItemClickListener(this);

        //Cursor
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_data", null);

        //ListView adapter
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.custom_item,
                cursor,
                new String[]{/*column*/},
                new int[]{/**/},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mainListView.setAdapter(cursorAdapter);

        //block touch
        searchCuriculum.setFocusable(false);

        //검색 버튼 listener
        searchCuriculum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색 화면으로 이동
                startActivity( new Intent(Main.this, SearchActivity.class));
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity( new Intent(Main.this, PrimaryCurriculumListActivity.class));
    }
}