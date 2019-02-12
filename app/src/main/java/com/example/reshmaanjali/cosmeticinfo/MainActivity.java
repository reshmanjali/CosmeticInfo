package com.example.reshmaanjali.cosmeticinfo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.id_mabelline_tv)
    TextView tv1;
    @BindView(R.id.id_revlon_tv)
    TextView tv2;
    @BindView(R.id.id_covergirl_tv)
    TextView tv3;
    @BindView(R.id.id_milani_tv)
    TextView tv4;

    @BindView(R.id.id_banner)
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        isNetworkAvailable();

        MobileAds.initialize(this,"ca-app-pub-2964509875117240~3707771378");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("69C8BD8BEA2715C5ADEC8BA06AF59AB1")
                .build();
        mAdView.loadAd(adRequest);
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager cm
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = cm.getActiveNetworkInfo();
        Boolean b = (activeInfo != null && activeInfo.isConnected());
        if ((!b)) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle(R.string.oo);
            alertDialog.setMessage(R.string.off);
            alertDialog.setCancelable(true);
            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        }
        return b;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fmenu, menu);
        return true;
    }

    public void brandSpecific(View view) {
        String s = "";
        Intent i;
        if (!isNetworkAvailable()) return;
        switch (view.getId()) {
            case R.id.id_mabelline_tv:
                String s1 = "eXJmSAQpjYU";
                s = "maybelline";
                i = new Intent(this, BrandProducts.class);
                i.putExtra("key_brand", s);

                i.putExtra("video_key", s1);
                startActivity(i);
                break;
            case R.id.id_revlon_tv:
                s = "revlon";
                i = new Intent(this, BrandProducts.class);
                i.putExtra("key_brand", s);
                i.putExtra("video_key", "Uh14o7QCxKE");
                startActivity(i);
                break;
            case R.id.id_covergirl_tv:
                s = "covergirl";
                i = new Intent(this, BrandProducts.class);
                i.putExtra("key_brand", s);
                i.putExtra("video_key", "Cx1kma96na0");
                startActivity(i);
                break;
            case R.id.id_milani_tv:
                s = "milani";
                i = new Intent(this, BrandProducts.class);
                i.putExtra("key_brand", s);
                i.putExtra("video_key", "oepgoVrCySY");
                startActivity(i);
                break;
        }

    }

    public void clickedFav(MenuItem item) {
        Intent i = new Intent(this, FavActivity.class);
        startActivity(i);
    }
}

