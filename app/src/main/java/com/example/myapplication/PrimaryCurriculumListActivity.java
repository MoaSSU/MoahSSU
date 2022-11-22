package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.DriveAdapter;
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
        Cursor cursor = db.rawQuery("select * from curriculum", null);
        ArrayList<CurriculumVO> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            CurriculumVO vo = new CurriculumVO();
            vo.title = cursor.getString(3);
            vo.name = cursor.getString(1);
            vo.like = cursor.getString(2);
            data.add(vo);
        }
        db.close();
        DriveAdapter adapter = new DriveAdapter(this, R.layout.curriculum_item, data);
        listView.setAdapter(adapter);

    }
}