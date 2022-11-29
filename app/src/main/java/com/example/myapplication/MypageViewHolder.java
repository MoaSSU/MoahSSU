package com.example.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MypageViewHolder extends RecyclerView.ViewHolder{
    public TextView textView1; //커리큘럼제목
    public TextView textView2; // "소요 시간 :"
    public TextView textView3; //소요 시간
    public ImageView imageView; //이미지

    public MypageViewHolder(@NonNull View itemView) {
        super(itemView);

        textView1 = itemView.findViewById(R.id.imgView_item);
        textView2 = itemView.findViewById(R.id.text_soyosigan);
        textView3 = itemView.findViewById(R.id.text_time);
        imageView = itemView.findViewById(R.id.imgView_item);
    }
}
