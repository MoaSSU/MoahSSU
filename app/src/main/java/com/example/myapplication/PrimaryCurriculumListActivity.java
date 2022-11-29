package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrimaryCurriculumListActivity extends AppCompatActivity {
    Button addCurriculum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primarycurriculum_list);
        addCurriculum = (Button) findViewById(R.id.addCurriculum);
        addCurriculum.setOnClickListener(addCur);
    }

    View.OnClickListener addCur = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PrimaryCurriculumListActivity.this, NewPrimaryCarriculum.class);
            startActivity(intent);
        }
    };
}