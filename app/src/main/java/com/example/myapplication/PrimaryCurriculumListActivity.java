package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.adapter.CurriculumAdapter;
import com.example.myapplication.domain.CurriculumVO;

import java.util.ArrayList;

public class PrimaryCurriculumListActivity extends AppCompatActivity {

    Button addCurriculum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycurriculum_list);
        ListView listView = findViewById(R.id.curriculumlist);
        addCurriculum = findViewById(R.id.addCurriculum);
        addCurriculum.setOnClickListener(add);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title,name,best from curriculum", null);
        ArrayList<CurriculumVO> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            CurriculumVO vo = new CurriculumVO();
            vo.title = cursor.getString(1);
            vo.name = cursor.getString(2);
            //vo.best = cursor.getInt(3);
            data.add(vo);
        }
        db.close();
        CurriculumAdapter adapter = new CurriculumAdapter(this, R.layout.curriculum_item, data);
        listView.setAdapter(adapter);
    }
    View.OnClickListener add = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PrimaryCurriculumListActivity.this,NewPrimaryCarriculum.class);
            startActivity(intent);
        }
    };

}