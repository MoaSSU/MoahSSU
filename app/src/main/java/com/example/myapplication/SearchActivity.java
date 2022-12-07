package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import com.example.myapplication.adapter.CurriculumAdapter;
import com.example.myapplication.domain.CurriculumVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public SearchView searchView;
    ArrayList<HashMap<String, String>> resultList;
    ListView listView;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        backButton = (ImageView) findViewById(R.id.search_back);
        backButton.setOnClickListener(back);
        searchView = findViewById(R.id.search_bar);
        listView = findViewById(R.id.search_list);
        listView.setOnItemClickListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // 새로운 쿼리의 결과 뿌리기
                getCurriculumList(s);
                return true;
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, PrimaryCurriculumActivity.class);
        intent.putExtra("curriculumId",l);
        this.startActivity(intent);
    }

    public void getCurriculumList(String search) {

        String ss = search;

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        resultList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from curriculum where title like \'%" + ss + "%\'", null);
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", cursor.getString(1));
            map.put("name", cursor.getString(2));
            resultList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter((Context) this,
                resultList,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "name"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
    }

    View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}