package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity{

    CardView moveStudy;
    CardView moveHobby;
    CardView moveLife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moveStudy = (CardView) findViewById(R.id.study_button);
        moveStudy.setOnClickListener(study);
        moveHobby = (CardView) findViewById(R.id.hobby_button);
        moveHobby.setOnClickListener(hobby);
        moveLife = (CardView) findViewById(R.id.life_button);
        moveLife.setOnClickListener(life);
    }
    View.OnClickListener study = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main.this, StudyCurriculumListActivity.class);
            startActivity(intent);

        }
    };
    View.OnClickListener hobby = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main.this, HobbyCurriculumListActivity.class);
            startActivity(intent);

        }
    };
    View.OnClickListener life = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main.this, LifeCurriculumListActivity.class);
            startActivity(intent);

        }
    };

}