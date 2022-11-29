package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MypageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private ArrayList<RecyclerViewItem> dataSet = null;

    public MypageAdapter(ArrayList<RecyclerViewItem> dataSet) {
        this.dataSet = dataSet;
    }

    //아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_item, viewGroup, false);
        return new MypageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RecyclerViewItem item = dataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
