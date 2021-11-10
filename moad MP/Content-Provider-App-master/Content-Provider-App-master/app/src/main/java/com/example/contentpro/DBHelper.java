package com.example.contentpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "StudentInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table StudentInfo(pid TEXT primary key,name TEXT,contact TEXT,dob TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists StudentInfo");

    }

    public boolean insertData(String pid,String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("pid",pid);
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("dob",dob);
        long result=DB.insert("StudentInfo",null,cv);
        if(result==1){
            return false;
        }else{
            return true;
        }
    }

    public boolean updateData(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("contact",contact);
        cv.put("dob",dob);
        Cursor cursor=DB.rawQuery("Select * from StudentInfo where name =?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = DB.update("StudentInfo", cv, "name=?", new String[]{name});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }

    public boolean deleteData(String name){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from StudentInfo where name =?",new String[]{name});
        if(cursor.getCount()>0) {
            long result = DB.delete("StudentInfo", "name=?", new String[]{name});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }
    public Cursor getData(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from StudentInfo",null);
        return cursor;

    }
}
