package com.example.duan.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan.database.Dbhelper;
import com.example.duan.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    Dbhelper dbhelper;
    public SachDAO(Context context) {
        dbhelper = new Dbhelper(context);
    }

    //test
    public ArrayList<Sach> getDSSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, sc.giathue, sc.maloai, lo.tenloai FROM SACH sc, LOAISACH lo WHERE sc.maloai = lo.maloai",null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themSachmoi(String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",tensach);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhapThongTinSach(int masach, int giathue, int maloai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("masach",masach);
        contentValues.put("giathue",giathue);
        contentValues.put("maloai",maloai);
        long check = sqLiteDatabase.update("SACH", contentValues,"masach = ?", new String[]{String.valueOf(masach)});
        if (check ==-1)
            return false;
        return true;
    }

    public int xoaSach (int masach){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUMUON WHERE masach = ?", new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("SACH", "masach = ?",new String[]{String.valueOf(masach)});
        if (check == -1)
            return 0;
        return 1;
    }
}
