package com.example.vlatkopopovic.checkandbeefree.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;

import java.util.ArrayList;
import java.util.List;


public class SQLite
{
    private static final String DATABASE_NAME = "data";

    private MyDBHelper mDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    public SQLite(Context context)
    {
        //Context mContext = context;
        int DATABASE_VERSION = 1;
        mDbHelper = new MyDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open()
    {
        mSqLiteDatabase = mDbHelper.getWritableDatabase();
    }




    public void addItem(String title, String question, int image, int switchButton, String date)
    {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("question", question);
        cv.put("image", image);
        cv.put("switchButton", switchButton);
        cv.put("date", date);
        mSqLiteDatabase.insert("items", null, cv);

    }
    public void updateSwitch(int switchButton, String title)
    {
        mSqLiteDatabase.execSQL("update items set switchButton='" + switchButton + "'where title='" + title + "'");
        //staviti sve u jedan query

    }
    public void updateDate(String date, String title)
    {
        mSqLiteDatabase.execSQL("update items set date='" + date + "'where title='" + title + "'");


    }

    public void deleteItem (String title)
    {
        mSqLiteDatabase.delete("items","title='"+title+"'",null);

    }



    public Cursor list_all_list(){
        SQLiteDatabase db = mSqLiteDatabase;
        return db.rawQuery("SELECT * FROM ITEMS",null);
    }


    public List<RecyclerListItem> selectAllItems()
    {

        List<RecyclerListItem> allItems = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query("items", null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            do
            {
                RecyclerListItem listItem1 = new RecyclerListItem(cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getInt(4),cursor.getString(5));
                allItems.add(listItem1);

            }
            while (cursor.moveToNext());

        }
          cursor.close();
        return allItems;

    }




    private class MyDBHelper extends SQLiteOpenHelper
    {
        MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String query = "CREATE TABLE items(id integer primary key autoincrement, title text, question text, image integer, switchButton integer, date text);";
            db.execSQL(query);
        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            String query = "DROP TABLE IF EXISTS items;";
            db.execSQL(query);
            onCreate(db);
        }
    }
}
