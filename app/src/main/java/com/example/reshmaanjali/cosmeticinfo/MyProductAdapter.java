package com.example.reshmaanjali.cosmeticinfo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductAdapter.MyViewHoolder> {
    private Context c;
    private ArrayList<ProductPOJO> myProductPOJO;

    public MyProductAdapter(Context c, ArrayList<ProductPOJO> myProductPOJO) {
        this.c = c;
        this.myProductPOJO = myProductPOJO;
    }

    @NonNull
    @Override
    public MyViewHoolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHoolder(LayoutInflater.from(c).inflate(R.layout.eachproduct, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoolder myViewHoolder, int i) {
        myViewHoolder.tv.setText(myProductPOJO.get(i).getName());
    }

    @Override
    public int getItemCount() {

        return myProductPOJO.size();
    }

    public class MyViewHoolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHoolder(@NonNull final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.id_tv_each_product);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(c, ProductInDetail.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    int s = getAdapterPosition();
                    i.putExtra("names_key", myProductPOJO.get(s).getName());
                    i.putExtra("images_key", myProductPOJO.get(s).getImage_link());
                    i.putExtra("desc_key", myProductPOJO.get(s).getDescription());
                    i.putExtra("prod_id_key", myProductPOJO.get(s).getId());
                    //i.putExtra("prod_details_key",myProductPOJO.get(getAdapterPosition()));
                    c.startActivity(i);
                }
            });
        }
    }
}
