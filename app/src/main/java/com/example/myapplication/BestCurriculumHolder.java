package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BestCurriculumHolder {
    public ImageView ImageView;
    public TextView titleView;
    public TextView nameView;
    public TextView idView;

    public BestCurriculumHolder(View root){
        ImageView = root.findViewById(R.id.best_item_image);
        titleView = root.findViewById(R.id.best_item_title);
        nameView = root.findViewById(R.id.best_item_nickname);
        idView = root.findViewById(R.id.mainListId);
    }

}
