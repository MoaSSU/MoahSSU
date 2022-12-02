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

import com.google.firebase.internal.InternalTokenProvider;

public class Main extends AppCompatActivity{

    CardView moveStudy;
    CardView moveHobby;
    CardView moveLife;
    ImageView moveMypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveStudy = (CardView) findViewById(R.id.study_button);
        moveStudy.setOnClickListener(listener);
        moveHobby = (CardView) findViewById(R.id.hobby_button);
        moveHobby.setOnClickListener(listener);
        moveLife = (CardView) findViewById(R.id.life_button);
        moveLife.setOnClickListener(listener);
        moveMypage = (ImageView) findViewById(R.id.go_to_mypage);
        moveMypage.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == moveStudy) {
                Intent intent = new Intent(Main.this, PrimaryCurriculumListActivity.class);
                intent.putExtra("category","공부");
                startActivity(intent);
            }
            else if(view == moveHobby) {
                Intent intent = new Intent(Main.this, PrimaryCurriculumListActivity.class);
                intent.putExtra("category","취미");
                startActivity(intent);
            }
            else if(view == moveLife) {
                Intent intent = new Intent(Main.this, PrimaryCurriculumListActivity.class);
                intent.putExtra("category","생활");
                startActivity(intent);
            }
            else if(view == moveMypage) {
                Intent intent = new Intent(Main.this, MypageActivity.class);
                startActivity(intent);
            }
        }
    };

}