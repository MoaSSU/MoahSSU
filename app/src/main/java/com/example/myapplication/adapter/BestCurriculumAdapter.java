package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.myapplication.BestCurriculumHolder;
import com.example.myapplication.Main;
import com.example.myapplication.PrimaryCurriculumActivity;
import com.example.myapplication.R;
import com.example.myapplication.domain.BestCurriculumVO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        return data.size();
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
        TextView textView = holder.idView;

        final BestCurriculumVO vo = data.get(position);

        titleView.setText(vo.title);
        nameView.setText(vo.name);
        textView.setText(vo.id);

        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PrimaryCurriculumActivity.class);
                intent.putExtra("curriculumId",vo.id);
                context.startActivity(intent);
            }
        });


        FirebaseStorage storage = FirebaseStorage.getInstance();
        if(vo.image !=null) {
            StorageReference storageRef = storage.getReferenceFromUrl("gs://test-aa1c1.appspot.com").child("images/" + vo.image);
            if (storageRef != null) {
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(context).load(uri).into(imageView);
                    }
                });
            }
        }


        return convertView;

    }

}