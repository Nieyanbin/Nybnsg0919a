package com.example.dell.nybnsg.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dell on 2017/9/9.
 */
public class MySql extends SQLiteOpenHelper {
    public MySql(Context context) {
        super(context, "nyba.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table nyba(id integer primary key autoincrement,img varchar ,name varchar(20) ,money float,num integer )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
