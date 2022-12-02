package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.adapter.BestCurriculumAdapter;
import com.example.myapplication.domain.BestCurriculumVO;

import java.util.ArrayList;

public class Main extends AppCompatActivity{

    CardView moveStudy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveStudy = (CardView) findViewById(R.id.study_button);
        moveStudy.setOnClickListener(study);


        ListView listView = findViewById(R.id.main_listview);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,title,name from curriculum order by best desc", null);

        ArrayList<BestCurriculumVO> data = new ArrayList<>();
        while(cursor.moveToNext()){
            BestCurriculumVO vo = new BestCurriculumVO();
            vo.id = cursor.getString(0);
            vo.title = cursor.getString(1);
            vo.name = cursor.getString(2);
            data.add(vo);//arraylist에 id, title, name 담은것 추가
        }

        db.close();
        BestCurriculumAdapter adapter = new BestCurriculumAdapter(this, R.layout.best_item, data);
        listView.setAdapter(adapter);
    }

    View.OnClickListener study = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Main.this, PrimaryCurriculumListActivity.class);
            startActivity(intent);

        }
    };

}