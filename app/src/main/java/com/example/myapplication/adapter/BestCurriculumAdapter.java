package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.BestCurriculumHolder;
import com.example.myapplication.domain.BestCurriculumVO;

import java.util.ArrayList;

public class BestCurriculumAdapter extends ArrayAdapter<BestCurriculumVO> {
    Context context;
    int resId;
    ArrayList<BestCurriculumVO> data;

    public BestCurriculumAdapter(Context context, int resId, ArrayList<BestCurriculumVO> data){ //resId : layout file name, data : 요소
        super(context,resId);
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @Override
    public int getCount() { // 전체 항목의 개수를 판단하기 위해 호출됨.
        return super.getCount();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){//첫 번째 리스트뷰
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);//(int resource, viewGroup)
            BestCurriculumHolder holder = new BestCurriculumHolder(convertView);
            convertView.setTag(holder);
        }
        BestCurriculumHolder holder = (BestCurriculumHolder) convertView.getTag();

        ImageView imageView = holder.ImageView;
        TextView titleView = holder.titleView;
        TextView nameView = holder.nameView;

        final BestCurriculumVO vo = data.get(position);

        titleView.setText(vo.title);
        nameView.setText(vo.name);
        //ImageView는 구현 이후에 추가할 예정

        return convertView;
    }
}
