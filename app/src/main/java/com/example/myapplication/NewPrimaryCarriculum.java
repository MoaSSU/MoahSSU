package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewPrimaryCarriculum extends AppCompatActivity {
    CheckBox high;
    CheckBox middle;
    CheckBox low;
    EditText newCurriculumName;
    EditText newCurriculumDes;  //설명
    int difficulty=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_primary_carriculum);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null){
            String name = user.getDisplayName();
            String uid = user.getUid();
        }
        high = findViewById(R.id.high);
        middle = findViewById(R.id.middle);
        low = findViewById(R.id.low);
        newCurriculumDes = findViewById(R.id.newCurriculumdes);
        newCurriculumName = findViewById(R.id.newCurriculumName);
        high.setOnClickListener(checkbox);
        middle.setOnClickListener(checkbox);
        low.setOnClickListener(checkbox);



    }
    View.OnClickListener checkbox = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox)view).isChecked();
            switch (view.getId()){
                case R.id.high:
                    if(checked){
                        difficulty =3;
                    }
                    break;
                case R.id.middle:
                    if(checked) {
                        difficulty = 2;
                    }
                    break;
                case R.id.low:
                    if(checked){
                        difficulty = 1;
                    }
                    break;
            }
        }
    };
}