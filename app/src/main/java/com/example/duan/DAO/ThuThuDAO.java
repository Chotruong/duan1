package com.example.duan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.database.Dbhelper;

public class ThuThuDAO {
    Dbhelper dbhelper;
    public ThuThuDAO(Context context){
        dbhelper = new Dbhelper(context);
    }

    //login
    public boolean checkLogin(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0){

            return true;
        }else {
            return false;
        }
    }

    public int capNhapMatKhau(String username, String OldPass, String NewPass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{username, OldPass});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", NewPass);
            long check = sqLiteDatabase.update("THUTHU", contentValues,"matt = ?", new String[]{username});
            if (check == -1)
                return -1;
            return 1;
        }
        return 0;
    }
}
