package com.example.myapplication;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class NewPrimaryCarriculum extends AppCompatActivity {
    RadioButton high;
    RadioButton middle;
    RadioButton low;
    RadioGroup radioGroup;
    Button addPhoto;
    Button back;
    EditText newCurriculumTitle;
    EditText newCurriculumDes;  //설명
    Button saveButtun;
    private ImageView ivPreview;
    private Uri filePath;
    private int best = 0;
    private int difficulty=0;
    private int category=0;
    private String filename;
    private String tempname = "temp";
    private String name;
    private String uid;
    private DBHelper dbHelper = new DBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_primary_carriculum);
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(moveBack);

        //로그인한 유저 정보
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        name = user.getDisplayName();
        uid = user.getUid();

        //인텐트로 카테고리 변수 받기
        Intent intent = getIntent();
        category = intent.getIntExtra("category",0);

        //라디오 버튼
        high = (RadioButton) findViewById(R.id.high);
        middle = (RadioButton) findViewById(R.id.middle);
        low = (RadioButton) findViewById(R.id.low);
        //이미지 등록
        ivPreview = (ImageView) findViewById(R.id.primary_image);
        addPhoto = (Button) findViewById(R.id.btn_add_image);
        addPhoto.setOnClickListener(addImage);

        //정보들 입력
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
            uploadFile();
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            db.execSQL("insert into curriculum (title, name, description ,photoUri, uuid , best , difficulty, category) values"
                    +"("
                    +"'" + newCurriculumTitle.getText().toString() + "',"
                    +"'" + tempname + "',"
                    +"'" + newCurriculumDes.getText().toString() + "',"
                    +"'" + filename + "',"
                    +"'" + uid + "',"
                    + best +","
                    + difficulty + ","
                    + category
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

    View.OnClickListener addImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if(requestCode == 0 && resultCode == RESULT_OK){
            filePath = data.getData();
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                addPhoto.setVisibility(View.GONE);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {


            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            String fileId = UUID.randomUUID().toString();
            filename = fileId + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://test-aa1c1.appspot.com").child("images/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }
    View.OnClickListener moveBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };


}