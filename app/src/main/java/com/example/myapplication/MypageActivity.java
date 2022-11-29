package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        RecyclerView recyclerView1 = findViewById(R.id.main_startcurriculum);

        List<String> list = new ArrayList<>();
        for(int i=0; i<20;i++) { list.add("Item =" +i); }

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        /*recyclerView1.setAdapter(new CustomAdapter(list));
        recyclerView1.addItemDecoration(new CustomItemDecoration());*/
    }
}