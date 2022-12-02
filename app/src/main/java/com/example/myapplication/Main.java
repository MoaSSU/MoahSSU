package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

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
        moveStudy.setOnClickListener(study);
        moveHobby = (CardView) findViewById(R.id.hobby_button);
        moveHobby.setOnClickListener(hobby);
        moveLife = (CardView) findViewById(R.id.life_button);
        moveLife.setOnClickListener(life);
        moveMypage = (ImageView) findViewById(R.id.move_mypage);
        moveMypage.setOnClickListener(mypage);

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
    View.OnClickListener mypage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main.this, MypageActivity.class);
            startActivity(intent);
        }
    };

}