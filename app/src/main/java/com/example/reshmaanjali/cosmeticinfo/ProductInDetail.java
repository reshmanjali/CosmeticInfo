package com.example.reshmaanjali.cosmeticinfo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInDetail extends AppCompatActivity {
    @BindView(R.id.id_img_prod_in_detail)
    ImageView imageView;
    @BindView(R.id.id__prod_name_in_detail)
    TextView prodName;
    @BindView(R.id.id__description_in_detail)
    TextView prodDesc;
    @BindView(R.id.id_fav_btn_in_detail)
    Button fav_btn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_in_detail);
        ButterKnife.bind(this);
        //final ProductPOJO receivedPOJO=getIntent().getParcelableExtra("prod_details_key");
        Intent receivedIntent = getIntent();
        final String name = receivedIntent.getStringExtra("names_key");
        final String id = receivedIntent.getStringExtra("prod_id_key");
        final String imageLink = receivedIntent.getStringExtra("images_key");
        final String desc = receivedIntent.getStringExtra("desc_key");
        prodName.setText(name);
        prodDesc.setText(desc);
        Picasso.with(this).load(imageLink).resize(300, 300).placeholder(R.mipmap.ic_launcher_round).into(imageView);
        //String prod_id=receivedPOJO.getId();
        if (checkingElementPresence(id)) {

            fav_btn.setText(R.string.rfav);
        } else {

            fav_btn.setText(R.string.afav);
        }
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkingElementPresence(id)) {
                    ContentValues cv = new ContentValues();
                    cv.put(ProductContract.PROD_ID_COL, id);
                    cv.put(ProductContract.PROD_NAME_COL, name);
                    cv.put(ProductContract.PROD_IMAGE_URL_COL, imageLink);
                    cv.put(ProductContract.PROD_DESC_COL, desc);
                    getContentResolver().insert(ProductContract.CONTENT_URI, cv);
                    Toast.makeText(ProductInDetail.this, R.string.rrfav, Toast.LENGTH_SHORT).show();
                    fav_btn.setText(R.string.rrrfav);
                } else {
                    Uri uri = Uri.parse(ProductContract.CONTENT_URI + "/*");
                    getContentResolver().delete(uri, ProductContract.PROD_ID_COL + " = '" + id + "'", null);
                    Toast.makeText(ProductInDetail.this, R.string.aafav, Toast.LENGTH_SHORT).show();
                    fav_btn.setText(R.string.aaaafav);
                }
                MyWidgetServicing.selfCall(ProductInDetail.this);
            }
        });
    }

    Boolean checkingElementPresence(String id) {
        Cursor c = getContentResolver().query(Uri.parse(ProductContract.CONTENT_URI + "/*"), null, id, null, null);
        if (c.getCount() > 0) return true;
        else return false;
    }
}
