package com.example.myapplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);
        backButton = (ImageView) findViewById(R.id.mypage_back);
        backButton.setOnClickListener(listener);
        userName = (TextView) findViewById(R.id.mypage_userName);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from user", null);
        cursor.moveToNext();
        userName.setText(cursor.getString(0));

        RecyclerView recyclerView1 = findViewById(R.id.main_startcurriculum);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        Cursor cursorr = db.rawQuery("select title, difficulty from curriculum", null);

        List<MypageVO> list = new ArrayList<>();
        while (cursorr.moveToNext()) {
            MypageVO vo = new MypageVO();
            vo.title = cursorr.getString(0);
            vo.difficulty = cursorr.getString(1);
            list.add(vo);
        }
        db.close();

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(new CustomAdapter(list));
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == backButton) finish();
        }
    };

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private TextView difficulty;

        ViewHolder(View view) {
            super(view);

            title =view.findViewById(R.id.text_primary);
            difficulty = view.findViewById(R.id.text_difficulty);
        }

        TextView getTitleView() {return title;}
        TextView getDifficultyView() {return difficulty;}
    }

    private class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<MypageVO> dataSet;

        CustomAdapter(List<MypageVO> dataSet) {this.dataSet = dataSet;}

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            //Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycler_item, viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.getTitleView().setText((CharSequence) dataSet.get(position));
            viewHolder.getDifficultyView().setText((CharSequence) dataSet.get(position));
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }
    }

    // private class CustomItemDecoration extends RecyclerView.ItemDecoration { }
}