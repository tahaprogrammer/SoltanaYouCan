package com.example.soltanayoucan.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.soltanayoucan.Utils.DataPostModel;
import com.example.soltanayoucan.Utils.Variables;

import java.util.ArrayList;

public class PostsDBHelper extends SQLiteOpenHelper {

    //Database name
    private static final String DATABASE_NAME = "PostsDatabase.db";
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database instance
    private SQLiteDatabase db;

    public PostsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table for the Posts
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                PostsTable.InnerTable.TABLE_NAME + " ( " +
                PostsTable.InnerTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PostsTable.InnerTable.ID_POST + " TEXT, " +
                PostsTable.InnerTable.DATE_GMT + " TEXT, " +
                PostsTable.InnerTable.TITLE + " TEXT, " +
                PostsTable.InnerTable.CONTENT + " TEXT " +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PostsTable.InnerTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertPostsToDB() {
        //this code insert Data to SQLite
        db = getWritableDatabase();
        for (int i = 0; i < Variables.dataPostModels.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(PostsTable.InnerTable.ID_POST, Variables.dataPostModels.get(i).getId_post());
            contentValues.put(PostsTable.InnerTable.DATE_GMT, Variables.dataPostModels.get(i).getDate_gmt());
            contentValues.put(PostsTable.InnerTable.TITLE, Variables.dataPostModels.get(i).getTitle());
            contentValues.put(PostsTable.InnerTable.CONTENT, Variables.dataPostModels.get(i).getContent());
            db.insert(PostsTable.InnerTable.TABLE_NAME, null, contentValues);
        }
    }

    public ArrayList<DataPostModel> getAllPosts() {
        //this code for get All Data From SQLite and put it into ArrayList
        ArrayList<DataPostModel> dataPostModels = new ArrayList<>();
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + PostsTable.InnerTable.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {

                DataPostModel single_post = new DataPostModel(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(PostsTable.InnerTable._ID))),
                        cursor.getString(cursor.getColumnIndex(PostsTable.InnerTable.ID_POST)),
                        cursor.getString(cursor.getColumnIndex(PostsTable.InnerTable.DATE_GMT)),
                        cursor.getString(cursor.getColumnIndex(PostsTable.InnerTable.TITLE)),
                        cursor.getString(cursor.getColumnIndex(PostsTable.InnerTable.CONTENT))
                );

                dataPostModels.add(single_post);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dataPostModels;
    }


    public void removeAllPosts() {
        //Remove All data from SQLite
        db = this.getWritableDatabase();
        db.execSQL("delete from " + PostsTable.InnerTable.TABLE_NAME);
    }
}