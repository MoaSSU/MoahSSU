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
import com.example.myapplication.DBHelper;
import com.example.myapplication.SubCurriculumHolder;
import com.example.myapplication.domain.CurriculumVO;
import com.example.myapplication.domain.SubCurriculumVO;

import java.util.ArrayList;

public class SubCurriculumAdapter extends ArrayAdapter<SubCurriculumVO> {
    Context context;
    int resId;
    ArrayList<SubCurriculumVO> data;
    public SubCurriculumAdapter(Context context, int resId, ArrayList<SubCurriculumVO> data) {
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
            SubCurriculumHolder holder = new SubCurriculumHolder(convertView);
            convertView.setTag(holder);
        }
        SubCurriculumHolder holder = (SubCurriculumHolder)convertView.getTag();

        TextView titleView = holder.title;
        final SubCurriculumVO subCurriculumVO = data.get(position);
        titleView.setText(subCurriculumVO.text);

        return convertView;
    }
}
