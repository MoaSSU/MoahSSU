package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DriveHolder {
    public ImageView ImageView;
    public TextView titleView;
    public TextView nameView;
    public ImageView menuImageView;
    public DriveHolder(View root) {
        ImageView = root.findViewById(R.id.curiculumimage);
        titleView = root.findViewById(R.id.curiculumtitle);
        nameView = root.findViewById(R.id.curiculumnName);
        menuImageView = root.findViewById(R.id.curiculumlike);
    }
}
