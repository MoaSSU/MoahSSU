package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

public class SubCurriculumHolder {
    public TextView title;
    public SubCurriculumHolder(View root){
        title = root.findViewById(R.id.subtilte);
    }
}
