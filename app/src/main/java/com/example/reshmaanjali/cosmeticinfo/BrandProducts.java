package com.example.reshmaanjali.cosmeticinfo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandProducts extends AppCompatActivity {
    @BindView(R.id.id_lin_brand_products)
    LinearLayout linearLayout;
    @BindView(R.id.id_rec_list_products)
    RecyclerView recyclerViewProducts;
    @BindView(R.id.id_youtube_tutorial)
    Button video_btn;
    private LinearLayoutManager myLinearLayoutManager;
    private int pos = 0;
    private MyProductAdapter myAdapter;
    private ArrayList<ProductPOJO> resultArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_products);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            pos = savedInstanceState.getInt("POSITION");
            resultArrayList = savedInstanceState.getParcelableArrayList("list");
            displayAdapter(resultArrayList);
            recyclerViewProducts.scrollToPosition(pos);
        } else {
            resultArrayList = new ArrayList<>();
            Snackbar.make(linearLayout, getString(R.string.lodng_snack_brndPro), Snackbar.LENGTH_SHORT).show();
            new ProductListPopulator().execute(getIntent().getStringExtra("key_brand"));
        }
        final String video_key_received = getIntent().getStringExtra("video_key");
        video_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), VideoActivity.class);
                i.putExtra("youTube_key", video_key_received);
                startActivity(i);
            }
        });

    }

    private void displayAdapter(ArrayList<ProductPOJO> resultArrayList) {
        myLinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(myLinearLayoutManager);
        myAdapter = new MyProductAdapter(getApplicationContext(), resultArrayList);
        recyclerViewProducts.setAdapter(myAdapter);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (myLinearLayoutManager != null) {
            pos = myLinearLayoutManager.findFirstVisibleItemPosition();
            outState.putInt("POSITION", pos);
            outState.putParcelableArrayList("list", resultArrayList);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class ProductListPopulator extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            try {
                resultArrayList = MyNetworkingUtils.gettingResponse(MyNetworkingUtils.buildingUrl(strings[0]));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            displayAdapter(resultArrayList);
        }
    }
}
