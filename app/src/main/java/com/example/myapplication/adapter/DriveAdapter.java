package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.example.myapplication.DriveHolder;
import com.example.myapplication.domain.DriveVO;

import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveVO> {
    Context context;
    int resId;
    ArrayList<DriveVO> data;
    public DriveAdapter(Context context, int resId, ArrayList<DriveVO> data) {
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
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }
        DriveHolder holder = (DriveHolder)convertView.getTag();
        ImageView typeImageView = holder.typeImageView;
        TextView titleView = holder.titleView;
        TextView dateView = holder.dateView;
        ImageView menuImageView = holder.menuImageView;
        final DriveVO vo = data.get(position);
        titleView.setText(vo.title);
        dateView.setText(vo.date);

        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, vo.title+" menu click", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return convertView;
    }
}
