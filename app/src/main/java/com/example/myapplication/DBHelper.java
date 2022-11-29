package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, "moah", null , DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "create table user (" +
                "id integer primary key autoincrement, " +
                "name VARCHAR(10)," +
                "email VARCHAR(20)," +
                "photo VARCHAR(20)," +
                "uuid VARCHAR(45)" + ")";
        db.execSQL(memoSQL);

        String curriculumSQL = "create table curriculum (" +
                "id integer primary key autoincrement, " +
                "title VARCHAR(45)," +
                "name VARCHAR(10)," +
                "description VARCHAR(45)," +
                "photoUri VARCHAR(45)," +
                "uuid VARCHAR(45)," +
                "best integer," +
                "difficulty integer," +
                "category integer" +")";
        db.execSQL(curriculumSQL);

        String userCurriculumSQL = "create table usercurriculum (" +
                "id integer primary key autoincrement, " +
                "uuid VARCHAR(45)," +
                "curriculumId integer" + ")";
        db.execSQL(userCurriculumSQL);
        /*
        String subCurriculumSQL = "create table subcurriculum (" +
                "id integer primary key autoincrement, " +
                "name VARCHAR(10)," +
                "time VARCHAR(10)," +
                "bookLink VARCHAR(45)," +
                "videoLink VARCHAR(45)," +
                "description VARCHAR(45)," +
                "curriculunId integer" + ")";
        db.execSQL(subCurriculumSQL);

         */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i1 == DATABASE_VERSION) {
            db.execSQL("drop table tb_memo");
            onCreate(db);
        }
    }
}
