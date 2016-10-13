package com.example.diy_indicator.fragmentView.fragment_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.diy_indicator.MyDataBase;
import com.example.diy_indicator.bean.Fragment_1_bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/12.
 */

public class Fragment_1_db {
    private MyDataBase dbHelper;
    private SQLiteDatabase db;

    public Fragment_1_db(Context context) {
        this.dbHelper = new MyDataBase(context,"NOTE.db",null,1);
        db = dbHelper.getWritableDatabase();
    }
    public Fragment_1_bean addToDB(Fragment_1_bean bean){
        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put("pos",bean.getPos());
        values.put("grade",bean.getGrade());
        values.put("name",bean.getName());
        values.put("content",bean.getContent());
        int id = (int)db.insert("Note",null,values);
        bean.setId(id);
        db.setTransactionSuccessful();
        db.endTransaction();
        return bean;
    }
    public List<Fragment_1_bean> qureyFromDB(){
        List<Fragment_1_bean> data = new ArrayList<>();
        db.beginTransaction();

        Cursor cursor = db.query("Note",null,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int pos = cursor.getInt(cursor.getColumnIndex("pos"));
            String grade = cursor.getString(cursor.getColumnIndex("grade"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Fragment_1_bean bean = new Fragment_1_bean(grade,name,content,pos);
            bean.setId(id);
            data.add(0,bean);
//            Log.d("db","dbquery"+bean.toString());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();

        return data;
    }
    public boolean updataPos(Fragment_1_bean bean){
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("pos",bean.getPos());
//        db.update("Note",values,"grade=? and name=? and content=? and id=?",
//                new String[]{bean.getGrade(),bean.getName(),bean.getContent(),String.valueOf(bean.getId() ) });
                db.update("Note",values,"id=?",
              new String[]{String.valueOf(bean.getId() ) });

        db.setTransactionSuccessful();
        db.endTransaction();

        return true;
    }
    public boolean removeFromDB(Fragment_1_bean bean){
        db.beginTransaction();
//        db.delete("Note","grade=? and name=? and content=? and id=?",
//                new String[]{bean.getGrade(),bean.getName(),bean.getContent(),String.valueOf(bean.getId() )});
        db.delete("Note","id=?",
                new String[]{String.valueOf(bean.getId() )});
        db.setTransactionSuccessful();
        db.endTransaction();
        return true;
    }

}
