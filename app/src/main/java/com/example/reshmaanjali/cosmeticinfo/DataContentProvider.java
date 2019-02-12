package com.example.reshmaanjali.cosmeticinfo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataContentProvider extends ContentProvider {
    private static final int myFavourites = 200;
    public static final int myFavourites_with_ID = 201;
    public static final UriMatcher sUriMatcher = buildUriMatcher();
    FavDBHelper mFavDBHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ProductContract.AUTHORITY, ProductContract.PATH_myFavourites, myFavourites);
        uriMatcher.addURI(ProductContract.AUTHORITY, ProductContract.PATH_myFavourites + "/*", myFavourites_with_ID);
        return uriMatcher;
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        mFavDBHelper = new FavDBHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        final SQLiteDatabase db = mFavDBHelper.getReadableDatabase();
        Cursor c = null;
        switch (sUriMatcher.match(uri)) {
            case myFavourites:
                c = db.query(ProductContract.TABLE_NAME,
                        strings,
                        s,
                        strings1,
                        null,
                        null,
                        s1);

                break;
            case myFavourites_with_ID:
                c = db.query(ProductContract.TABLE_NAME,
                        strings, ProductContract.PROD_ID_COL + " = '" + s + "'", null, null, null, null);

                break;
            default:
                throw new UnsupportedOperationException("Sorry, Unknown URI : " + uri);

        }
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mFavDBHelper.getWritableDatabase();
        Uri returningUri = null;
        switch (sUriMatcher.match(uri)) {
            case myFavourites:
                long id = db.insert(ProductContract.TABLE_NAME, null, contentValues);
                if (id > 0)
                    returningUri = ContentUris.withAppendedId(ProductContract.CONTENT_URI, id);
                else
                    throw new SQLException("Failed to insert row");
                break;
            default:
                throw new UnsupportedOperationException("Sorry Unknown Uri : " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return returningUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        final SQLiteDatabase db = mFavDBHelper.getWritableDatabase();
        int deletedProductsCount = 0;
        switch (sUriMatcher.match(uri)) {
            case myFavourites_with_ID:
                deletedProductsCount = db.delete(ProductContract.TABLE_NAME, s, null);
                break;
            default:
                throw new UnsupportedOperationException("Sorry, Unknown uri: " + uri);
        }
        if (deletedProductsCount > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deletedProductsCount;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
