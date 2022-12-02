package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.CurriculumHolder;
import com.example.myapplication.domain.CurriculumVO;

import java.util.ArrayList;

public class CurriculumAdapter extends ArrayAdapter<CurriculumVO> {
    Context context;
    int resId;
    ArrayList<CurriculumVO> data;
    public CurriculumAdapter(Context context, int resId, ArrayList<CurriculumVO> data) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            CurriculumHolder holder = new CurriculumHolder(convertView);
            convertView.setTag(holder);
        }
        CurriculumHolder holder = (CurriculumHolder)convertView.getTag();
        //ImageView ImageView = holder.ImageView;
        TextView idView = holder.idView;
        TextView titleView = holder.titleView;
        TextView nameView = holder.nameView;
        TextView bestView = holder.bestView;
        // ImageView likeImageView = holder.likeImageView;
        final CurriculumVO vo = data.get(position);
        String id = String.valueOf(vo.id);
        idView.setText(id);
        titleView.setText(vo.title);
        nameView.setText(vo.name);
        String best = String.valueOf(vo.best);
        bestView.setText(best);

        return convertView;
    }
}
