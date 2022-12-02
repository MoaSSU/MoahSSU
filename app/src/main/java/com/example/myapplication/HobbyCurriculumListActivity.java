package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.CurriculumAdapter;
import com.example.myapplication.domain.CurriculumVO;

import java.util.ArrayList;

public class HobbyCurriculumListActivity extends AppCompatActivity {
    Button addCurriculum;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobbycurriculum_list);
        ListView listView = findViewById(R.id.curriculumlist);
        back = findViewById(R.id.hobbyBack);
        back.setOnClickListener(moveBack);
        addCurriculum = findViewById(R.id.addCurriculum);
        addCurriculum.setOnClickListener(add);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        //hobby의 카테고리명은 1
        Cursor cursor = db.rawQuery("select id,title,name,best from curriculum where category = 1", null);
        ArrayList<CurriculumVO> data = new ArrayList<>();
        while (cursor.moveToNext()) {
            CurriculumVO vo = new CurriculumVO();
            vo.id = cursor.getInt(0);
            vo.title = cursor.getString(1);
            vo.name = cursor.getString(2);
            vo.image = cursor.getString(3);
            vo.best = cursor.getInt(4);
            data.add(vo);
        }
        db.close();
        CurriculumAdapter adapter = new CurriculumAdapter(this, R.layout.curriculum_item, data);
        listView.setAdapter(adapter);
    }
    View.OnClickListener add = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HobbyCurriculumListActivity.this,NewPrimaryCarriculum.class);
            intent.putExtra("category",1); //0은 스터디
            startActivity(intent);
        }
    };
    View.OnClickListener moveBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
