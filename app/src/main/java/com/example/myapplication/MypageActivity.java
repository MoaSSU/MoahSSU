package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.MypageVO;

import java.util.ArrayList;
import java.util.List;

public class MypageActivity extends AppCompatActivity {

    ImageView backButton;
    TextView userName;
    ImageView userProfile;
    RecyclerView recyclerView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        backButton = (ImageView) findViewById(R.id.mypage_back);
        backButton.setOnClickListener(listener);
        userName = (TextView) findViewById(R.id.mypage_userName);
        userProfile = (ImageView) findViewById(R.id.mypage_profile);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from user", null);
        cursor.moveToNext();
        userName.setText(cursor.getString(0)); // 마이페이지 이름 불러오기

        recyclerView1 = findViewById(R.id.main_startcurriculum);

        Cursor cursorr = db.rawQuery("select title, difficulty from curriculum", null);

        ArrayList<MypageVO> list = new ArrayList<>();
        while (cursorr.moveToNext()) {
            MypageVO vo = new MypageVO();
            vo.setTitle(cursorr.getString(0));
            vo.setDifficulty(cursorr.getString(1));
            list.add(vo);
        }
        db.close();

        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView1.setAdapter(new CustomAdapter(list));
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == backButton) finish();
        }
    };

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView difficulty;

        ViewHolder(View view) {
            super(view);

            title =view.findViewById(R.id.text_primary);
            difficulty = view.findViewById(R.id.text_difficulty);
        }

        TextView getTitleView() {return title;}
        TextView getDifficultyView() {return difficulty;}
    }

    private class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<MypageVO> dataSet;

        CustomAdapter(ArrayList<MypageVO> dataSet) {this.dataSet = dataSet;}

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

            viewHolder.getTitleView().setText(vo.getTitle());
            viewHolder.getDifficultyView().setText(vo.getDifficulty());
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }


    // private class CustomItemDecoration extends RecyclerView.ItemDecoration { }
}