package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CurriculumHolder {
    public ImageView ImageView;
    public TextView titleView;
    public TextView nameView;
    public TextView likeView;
    public CurriculumHolder(View root) {
        ImageView = root.findViewById(R.id.curiculumimage);
        titleView = root.findViewById(R.id.curiculumtitle);
        nameView = root.findViewById(R.id.curiculumnName);
        likeView = root.findViewById(R.id.curiculumlike);
    }
}
