package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.domain.MypageVO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {

    ImageView backButton;
    TextView userName;
    TextView userCurriculum;
    ImageView userProfile;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    Context context;
    ImageView setting;
    TextView registerNUm;
    TextView startNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        backButton = (ImageView) findViewById(R.id.mypage_back);
        backButton.setOnClickListener(listener);
        userName = (TextView) findViewById(R.id.mypage_userName);
        userProfile = (ImageView) findViewById(R.id.mypage_profile);
        userCurriculum = (TextView) findViewById(R.id.curriculm_number);
        setting = (ImageView) findViewById(R.id.setting);
        setting.setOnClickListener(settingView);
        registerNUm = (TextView) findViewById(R.id.curriculm_number);
        startNum = (TextView) findViewById(R.id.startCurriculmNum);
        context = this;

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from user where uuid = " + "\"" + uid + "\"", null);
        while (cursor.moveToNext()) {
            userName.setText(cursor.getString(0)); // ??????????????? ?????? ????????????
        }
        Cursor cursor1 = db.rawQuery("select count(*) from curriculum where uuid = " + "\"" + uid + "\"", null);
        while (cursor1.moveToNext()) {
            registerNUm.setText(cursor1.getString(0)); // ????????? ???????????? ???
        }
        Cursor cursor2 = db.rawQuery("select count(*) from usercurriculum where uuid = " + "\"" + uid + "\"", null);
        while (cursor2.moveToNext()) {
            startNum.setText(cursor2.getString(0)); // ????????? ???????????? ???
        }
        recyclerView1 = findViewById(R.id.main_regcurriculum);
        recyclerView2 = findViewById(R.id.main_startcurriculum);

        Cursor cursorr = db.rawQuery("select id,title, difficulty, photoUri from curriculum where uuid = " + "\"" + uid + "\"", null);

        ArrayList<MypageVO> list = new ArrayList<>();
        while (cursorr.moveToNext()) {
            MypageVO vo = new MypageVO();
            vo.setId(cursorr.getInt(0));
            vo.setTitle(cursorr.getString(1));
            vo.setDifficulty(cursorr.getString(2));
            vo.setImageUrl(cursorr.getString(3));
            list.add(vo);
        }

        Cursor cursorrr = db.rawQuery("select curriculum.id,curriculum.title, curriculum.difficulty, curriculum.photoUri " +
                "from curriculum join usercurriculum on usercurriculum.curriculumId = curriculum.id " +
                "where usercurriculum.uuid = " + "\"" + uid + "\"", null);

        ArrayList<MypageVO> list1 = new ArrayList<>();
        while (cursorrr.moveToNext()) {
            MypageVO vo = new MypageVO();
            vo.setId(cursorrr.getInt(0));
            vo.setTitle(cursorrr.getString(1));
            vo.setDifficulty(cursorrr.getString(2));
            vo.setImageUrl(cursorrr.getString(3));
            list1.add(vo);
        }
        db.close();
        //????????? ???????????? ?????????
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView1.setAdapter(new CustomAdapter(list,this) );
        //????????? ???????????? ?????????
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(new CustomAdapter(list1,this) );
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == backButton) finish();
        }
    };

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView all;
        private final TextView id;
        private final TextView title;
        private final TextView difficulty;
        private final ImageView imageUrl;

        ViewHolder(View view) {
            super(view);
            all = view.findViewById(R.id.mypage_card);
            id = view.findViewById(R.id.cardId);
            title =view.findViewById(R.id.text_primary);
            difficulty = view.findViewById(R.id.text_difficulty);
            imageUrl = view.findViewById(R.id.imgView_item);
        }

        TextView getTitleView() {return title;}
        TextView getDifficultyView() {return difficulty;}
        ImageView getImageView() {return imageUrl;}
        TextView getIdView() {return id;}
    }

    private class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<MypageVO> dataSet;
        private Context context;

        CustomAdapter(ArrayList<MypageVO> dataSet,Context context) {
            this.dataSet = dataSet;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            //Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_item, viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
            final MypageVO vo = dataSet.get(position);
            viewHolder.all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PrimaryCurriculumActivity.class);
                    intent.putExtra("curriculumId",vo.getId());
                    context.startActivity(intent);
                }
            });

            viewHolder.getTitleView().setText(vo.getTitle());
            if(vo.getDifficulty().equals("1")){
                viewHolder.getDifficultyView().setText("???");
            }else if(vo.getDifficulty().equals("2")){
                viewHolder.getDifficultyView().setText("???");
            }else{
                viewHolder.getDifficultyView().setText("???");
            }


            FirebaseStorage storage = FirebaseStorage.getInstance();
            if(vo.getImageUrl() !=null) {
                StorageReference storageRef = storage.getReferenceFromUrl("gs://test-aa1c1.appspot.com").child("images/" + vo.getImageUrl());
                if (storageRef != null) {
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(context).load(uri).into(viewHolder.getImageView());
                        }
                    });
                }
            }
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }

    View.OnClickListener settingView = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MypageActivity.this,SettingActivity.class);
            startActivity(intent);
        }
    };

}