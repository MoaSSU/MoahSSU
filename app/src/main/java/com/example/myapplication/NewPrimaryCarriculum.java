package com.example.myapplication;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewPrimaryCarriculum extends AppCompatActivity {
    RadioButton high;
    RadioButton middle;
    RadioButton low;
    RadioGroup radioGroup;
    EditText newCurriculumTitle;
    EditText newCurriculumDes;  //설명
    Button saveButtun;
    private int best = 0;
    private int difficulty=0;
    private String tempname = "temp";
    private String name;
    private String uid;
    private DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_primary_carriculum);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getDisplayName();
        uid = user.getUid();

        high = (RadioButton) findViewById(R.id.high);
        middle = (RadioButton) findViewById(R.id.middle);
        low = (RadioButton) findViewById(R.id.low);
        newCurriculumDes = (EditText) findViewById(R.id.newCurriculumdes);
        newCurriculumTitle = (EditText) findViewById(R.id.newCurriculumTitle);
        saveButtun = (Button) findViewById(R.id.save);
        high.setOnClickListener(radioButtonClickListener);
        middle.setOnClickListener(radioButtonClickListener);
        low.setOnClickListener(radioButtonClickListener);
        saveButtun.setOnClickListener(save);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }
    View.OnClickListener save = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("insert into curriculum (title, name, description , uuid , best , difficulty) values"
                    +"("
                    +"'" + newCurriculumTitle.getText().toString() + "',"
                    +"'" + tempname + "',"
                    +"'" + newCurriculumDes.getText().toString() + "',"
                    +"'" + uid + "',"
                    + best +","
                    + difficulty
                    + ")");
            db.close();
            finish();
        }
    };

    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener(){
        @Override
        public void onClick(View view) {
            Toast.makeText(NewPrimaryCarriculum.this, "상 : "+high.isChecked() + "중 : " +middle.isChecked() +"하 : " +low.isChecked() , Toast.LENGTH_SHORT).show();
        }
    };

    //라디오 그룹 클릭 리스너
    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i==R.id.high){
                difficulty=3;
                Toast.makeText(NewPrimaryCarriculum.this, "난이도 상", Toast.LENGTH_SHORT).show();
            }
            else if(i==R.id.middle){
                difficulty=2;
                Toast.makeText(NewPrimaryCarriculum.this, "난이도 중", Toast.LENGTH_SHORT).show();
            }else{
                difficulty=1;
                Toast.makeText(NewPrimaryCarriculum.this, "난이도 하", Toast.LENGTH_SHORT).show();
            }
        }
    };


}