package com.example.reshmaanjali.cosmeticinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ForFavouritesDB";
    private static final int DATABASE_VERSION = 3;

    public FavDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_query = "CREATE TABLE " + ProductContract.TABLE_NAME + "( "
                + ProductContract.PROD_ID_COL + " TEXT PRIMARY KEY,"
                + ProductContract.PROD_NAME_COL + " TEXT NOT NULL,"
                + ProductContract.PROD_IMAGE_URL_COL + " TEXT,"
                + ProductContract.PROD_DESC_COL + " TEXT);";
        sqLiteDatabase.execSQL(create_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if exists " + ProductContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
