package com.example.reshmaanjali.cosmeticinfo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavActivity extends AppCompatActivity implements FavAdapter.FavInterface, LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.id_rec_view_fav)
    RecyclerView fav_rec_view;
    private String[] names;
    private String[] imagesLinks;
    private FavAdapter favAdapter;
    private String[] desc;
    private String[] ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        ButterKnife.bind(this);
        getData();
    }

    void getData() {
        fav_rec_view.setLayoutManager(new LinearLayoutManager(FavActivity.this));
        favAdapter = new FavAdapter(FavActivity.this, FavActivity.this);
        fav_rec_view.setAdapter(favAdapter);
        getSupportLoaderManager().restartLoader(23, null, this);
    }


    @Override
    public void puttingData(int s) {
        Intent intent = new Intent(FavActivity.this, ProductInDetail.class);
        intent.putExtra("names_key", names[s]);
        intent.putExtra("images_key", imagesLinks[s]);
        intent.putExtra("desc_key", desc[s]);
        intent.putExtra("prod_id_key", ids[s]);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public Loader onCreateLoader(int id, @Nullable Bundle args) {

        return new AsyncTaskLoader<Cursor>(getApplicationContext()) {
            Cursor c = null;

            @Nullable
            @Override
            public Cursor loadInBackground() {
                Uri uri = ProductContract.CONTENT_URI;
                c = getContentResolver().query(uri, null, null, null, null);
                return c;
            }

            @Override
            protected void onStartLoading() {
                if (c == null)
                    forceLoad();
                else
                    deliverResult(c);
            }

            @Override
            public void deliverResult(@Nullable Cursor data) {
                c = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Cursor data) {
        Cursor c = (Cursor) data;
        if (c != null) {
            if (c.moveToFirst()) {
                names = new String[c.getCount()];
                imagesLinks = new String[c.getCount()];
                desc = new String[c.getCount()];
                ids = new String[c.getCount()];
                int i = 0;
                do {
                    names[i] = c.getString(c.getColumnIndex(ProductContract.PROD_NAME_COL));
                    imagesLinks[i] = c.getString(c.getColumnIndex(ProductContract.PROD_IMAGE_URL_COL));
                    desc[i] = c.getString(c.getColumnIndex(ProductContract.PROD_DESC_COL));
                    ids[i] = c.getString(c.getColumnIndex(ProductContract.PROD_ID_COL));
                    i++;

                } while (c.moveToNext());
                favAdapter.setResults(names);
            } else {
                Toast.makeText(this, R.string.no, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
}
