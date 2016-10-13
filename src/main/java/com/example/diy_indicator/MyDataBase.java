package com.example.diy_indicator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyDataBase extends SQLiteOpenHelper {

    public static final String CREATE_NOTE = "create table Note ("
            +"id integer primary key autoincrement,"
            +"pos integer,"
            +"grade text,"
            +"name text,"
            +"content text)";
    private Context context;

    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE);
        Toast.makeText(context,"DataBase success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
