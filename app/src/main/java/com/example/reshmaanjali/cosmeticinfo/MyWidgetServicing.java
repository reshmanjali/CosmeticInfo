package com.example.reshmaanjali.cosmeticinfo;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;

public class MyWidgetServicing extends IntentService {

    static final String KEY = "xxx";

    public MyWidgetServicing() {
        super("My");
    }

    static void selfCall(Context c) {
        Intent i = new Intent(c, MyWidgetServicing.class);
        i.setAction(KEY);
        c.startService(i);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String obtainedString = intent.getAction();
        if (obtainedString.equals(KEY)) {
            String widgetData = null;
            Cursor c = getContentResolver().query(ProductContract.CONTENT_URI, null, null, null, null);
            if (c != null) {
                if (c.moveToFirst()) {
                    widgetData = "";
                    do {
                        widgetData += " * " + c.getString(c.getColumnIndex(ProductContract.PROD_NAME_COL)) + "\n";
                    } while (c.moveToNext());
                }
                c.close();
            }
            if (widgetData == null || widgetData.equals("")) {
                widgetData = "No Favourites added";
            }
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] wiggetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, MyWidget.class));
            MyWidget.method(this, appWidgetManager, wiggetIds, widgetData);
        }
    }
}
