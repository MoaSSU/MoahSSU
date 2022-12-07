package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SearchActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_bar);

    }
}