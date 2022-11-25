package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.UUID;

public class NewPrimaryCarriculum extends AppCompatActivity implements View.OnClickListener{
    Button back;
    Button addImage;
    ImageView imageView;
    Button addSub;
    Button cancel;
    String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_primary_carriculum);

        back = (Button) findViewById(R.id.btn_back);
        addImage = (Button) findViewById(R.id.btn_add_image);
        imageView = (ImageView) findViewById(R.id.primary_image);
        addSub = (Button) findViewById(R.id.addSub);
        cancel = (Button) findViewById(R.id.cancel);
        addSub.setOnClickListener(this);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        uuid = UUID.randomUUID().toString();
        db.execSQL("insert into curriculum (curriculumId) values (?)", new String[]{uuid});
        db.close();

    }

    @Override
    public void onClick(View view) {
        if(view == addSub) {
            Intent intent = new Intent(this, NewSubCarriculum.class);
            intent.putExtra("curriculumId", uuid);
            startActivity(intent);
        }
        else if(view == cancel){
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("delete from curriculum where curriculumId = " + uuid);
            db.execSQL("delete from subCurriculum where curriculumId = " +uuid);
            Intent intent = new Intent(this, PrimaryCurriculumListActivity.class);
            startActivity(intent);
        }
        else if(view == back){
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("delete from curriculum where curriculumId = " + uuid);
            db.execSQL("delete from subCurriculum where curriculumId = " +uuid);
            Intent intent = new Intent(this, PrimaryCurriculumListActivity.class);
            startActivity(intent);
        }
    }
}