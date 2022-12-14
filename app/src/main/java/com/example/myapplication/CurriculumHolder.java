package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CurriculumHolder {
    public ImageView ImageView;
    public TextView idView;
    public TextView titleView;
    public TextView nameView;
    public TextView bestView;
    public ImageView likeImageView;
    public CurriculumHolder(View root) {
        idView = root.findViewById(R.id.curriculumId);
        ImageView = root.findViewById(R.id.curriculumimage);
        titleView = root.findViewById(R.id.curriculumtitle);
        nameView = root.findViewById(R.id.curriculumName);
        //bestView = root.findViewById(R.id.curiculumlike);
        likeImageView = root.findViewById(R.id.imageId);
    }
}
