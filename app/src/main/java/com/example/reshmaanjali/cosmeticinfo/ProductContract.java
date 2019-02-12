package com.example.reshmaanjali.cosmeticinfo;

import android.net.Uri;
import android.provider.BaseColumns;

public class ProductContract implements BaseColumns {

    public static final String AUTHORITY = "com.example.reshmaanjali.cosmeticinfo";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_myFavourites = "myFavourites";
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_myFavourites).build();
    public static String TABLE_NAME="myFavourites";
    public static String PROD_ID_COL="prodID";
    public static String PROD_NAME_COL="prodName";
    public static String PROD_IMAGE_URL_COL="prodImageUrl";
    public static String PROD_DESC_COL="prodDesc";
}
