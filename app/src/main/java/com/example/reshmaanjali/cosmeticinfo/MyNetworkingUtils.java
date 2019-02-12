package com.example.reshmaanjali.cosmeticinfo;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class MyNetworkingUtils {
    //String base_url="http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline";
    private static String base_url = "http://makeup-api.herokuapp.com/api/v1/products.json";

    MyNetworkingUtils() {

    }

    public static URL buildingUrl(String s) {
        Uri uri = Uri.parse(base_url).buildUpon().appendQueryParameter("brand", s).build();

        URL url = null;
        try {
            url = new URL(uri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static ArrayList<ProductPOJO> gettingResponse(URL url) throws IOException {
        ArrayList<ProductPOJO> resultsList = new ArrayList<>();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream in = new BufferedInputStream(conn.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sBuilderLines = new StringBuilder();
        String line = "";
        while ((line = reader.readLine()) != null)
            sBuilderLines.append(line).append('\n');
        in.close();

        if (sBuilderLines != null) {
            try {
                JSONArray results = new JSONArray(sBuilderLines.toString());

                for (int i = 0; i < results.length(); i++) {
                    JSONObject rec = results.getJSONObject(i);
                    ProductPOJO myPOJOObj = new ProductPOJO();
                    myPOJOObj.setId(rec.optString("id"));
                    myPOJOObj.setBrand(rec.optString("brand"));
                    myPOJOObj.setName(rec.optString("name"));
                    myPOJOObj.setPrice(rec.optString("price"));
                    myPOJOObj.setPrice_sign(rec.optString("price_sign"));
                    myPOJOObj.setCurrency(rec.optString("currency"));
                    myPOJOObj.setImage_link(rec.optString("image_link"));
                    myPOJOObj.setProduct_link(rec.optString("product_link"));
                    myPOJOObj.setDescription(rec.optString("description"));
                    myPOJOObj.setRating(rec.optString("rating"));
                    myPOJOObj.setProduct_type(rec.optString("product_type"));
                    resultsList.add(myPOJOObj);
                    Log.i("RESULTSLIST : ", resultsList.toString());
                }
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        return resultsList;
    }

}
