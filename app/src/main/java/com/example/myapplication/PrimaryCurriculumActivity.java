package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.domain.Curriculum;
import com.example.myapplication.domain.CurriculumVO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class PrimaryCurriculumActivity extends AppCompatActivity {

    TextView titleView;
    TextView bestView;
    TextView categoryView;
    TextView descriptionView;
    int curriculumId;
    ImageView back;
    Button start;
    String name;
    String uid;

    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary_curriculum);
        Intent intent = getIntent();
        curriculumId = intent.getIntExtra("curriculumId",0);
        start = (Button) findViewById(R.id.btn_start);
        back = (ImageView) findViewById(R.id.back);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getDisplayName();
        uid = user.getUid();

        SQLiteDatabase db = helper.getWritableDatabase();
        //study의 카테고리명은 0
        Cursor cursor = db.rawQuery("select title,name,photoUri,best,category,description from curriculum where id = " +curriculumId, null);
        Curriculum curriculum = new Curriculum();
        while (cursor.moveToNext()) {
            curriculum.setTitle(cursor.getString(0));
            curriculum.setName(cursor.getString(1));
            curriculum.setPhotoUri(cursor.getString(2));
            curriculum.setBest(cursor.getInt(3));
            curriculum.setCategory(cursor.getInt(4));
            curriculum.setDescription(cursor.getString(5));
        }
        titleView = findViewById(R.id.title);
        bestView = findViewById(R.id.best);
        categoryView = findViewById(R.id.category);
        descriptionView = findViewById(R.id.description);

        titleView.setText(curriculum.getTitle());
        bestView.setText(String.valueOf(curriculum.getBest()));
        if(curriculum.getCategory()==0){
            categoryView.setText("공부");
        }else if(curriculum.getCategory()==1){
            categoryView.setText("취미");
        }else {
            categoryView.setText("생활");
        }
        descriptionView.setText(curriculum.getDescription());


        //커리큘럼을 시작한지 안 시작했는지 체크
        boolean check = false; // 이 커리큘럼을 시작했는지 체크
        Cursor cursor1 = db.rawQuery("select uuid from usercurriculum where curriculumId = " + curriculumId, null);
        while (cursor1.moveToNext()) {
            String tmp = cursor1.getString(0);
            if(tmp.equals(uid)) check = true;
        }


        back.setOnClickListener(moveBack);
        if(check){
            start.setText("중지");
            start.setOnClickListener(stopCurriculum);
        }else{
            start.setVisibility(View.VISIBLE);
            start.setText("시작");
            start.setOnClickListener(startCurriculum);
        }
        db.close();
    }

    View.OnClickListener startCurriculum = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into usercurriculum (uuid , curriculumId) values"
                    +"("
                    +"'" + uid + "',"
                    + curriculumId
                    + ")");
            db.close();
            finish();
        }
    };
    View.OnClickListener stopCurriculum = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("delete from usercurriculum where curriculumId = " +curriculumId);
            db.close();
            finish();
        }
    };

    View.OnClickListener moveBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}