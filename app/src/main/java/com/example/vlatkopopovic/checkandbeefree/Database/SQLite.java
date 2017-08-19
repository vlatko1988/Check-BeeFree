package com.example.vlatkopopovic.checkandbeefree.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vlatkopopovic.checkandbeefree.RecyclerViewAdapter.RecyclerListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlatko Popovic on 19-Aug-17.
 */

public class SQLite
{
    private static final String DATABASE_NAME = "data";

    private Context mContext;
    private MyDBHelper mDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private int DATABASE_VERSION = 1;

    public SQLite(Context context)
    {
        this.mContext = context;
        mDbHelper = new MyDBHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open()
    {
        mSqLiteDatabase = mDbHelper.getWritableDatabase();
    }




    public void addItem(String title, String question,String image, int switchButton)
    {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("question", question);
        cv.put("image", image);
        cv.put("switchButton", switchButton);
        mSqLiteDatabase.insert("items", null, cv);
    }

    public List<RecyclerListItem> selectAllItems()
    {



        List<RecyclerListItem> allItems = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query("items", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                RecyclerListItem listItem1 = new RecyclerListItem(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                allItems.add(listItem1);

            }
            while (cursor.moveToNext());
        }
        return allItems;
    }

    public void deleteAll()
    {

        mSqLiteDatabase.execSQL("delete from "+ "items");

    }

    public class MyDBHelper extends SQLiteOpenHelper
    {
        public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            String query = "CREATE TABLE items(id integer primary key autoincrement, title text, question text, image text, switchButton integer);";
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
