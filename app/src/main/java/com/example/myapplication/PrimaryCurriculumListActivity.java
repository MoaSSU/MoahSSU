package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.CurriculumAdapter;
import com.example.myapplication.domain.CurriculumVO;

import java.util.ArrayList;

public class PrimaryCurriculumListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycurriculum_list);

        ListView listView = findViewById(R.id.curiculumlist);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //사진경로는 아직 첨부 안함
        Cursor cursor = db.rawQuery("select title,name,best from curriculum", null);
        ArrayList<CurriculumVO> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            CurriculumVO vo = new CurriculumVO();
            vo.title = cursor.getString(1);
            vo.name = cursor.getString(2);
            vo.like = cursor.getInt(3);
            data.add(vo);
        }
        db.close();
        CurriculumAdapter adapter = new CurriculumAdapter(this, R.layout.curriculum_item, data);
        listView.setAdapter(adapter);

    }
}