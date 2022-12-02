package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Main extends AppCompatActivity{

    CardView moveStudy;
    ImageView moveMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveStudy = (CardView) findViewById(R.id.study_button);
        moveStudy.setOnClickListener(study);
        moveMypage = (ImageView) findViewById(R.id.go_to_mypage);
        moveMypage.setOnClickListener(study);
    }
    View.OnClickListener study = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == moveStudy) {
                Intent intent = new Intent(Main.this, PrimaryCurriculumListActivity.class);
                startActivity(intent);
            }
            else if (view ==moveMypage) {
                Intent intent = new Intent(Main.this, MypageActivity.class);
                startActivity(intent);
            }

        }
    };

}