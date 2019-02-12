package com.example.reshmaanjali.cosmeticinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.MyFavViewHolder> {

    private FavInterface favInterface;
    private Context c;
    String[] results;
    public FavAdapter(Context c, FavInterface favInterface) {
        this.c = c;
        this.favInterface = favInterface;
    }

    @NonNull
    @Override
    public MyFavViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyFavViewHolder(LayoutInflater.from(c).inflate(R.layout.eachproduct, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyFavViewHolder myFavViewHolder, int i) {
        myFavViewHolder.textView.setText(results[i]);
    }

    @Override
    public int getItemCount() {
        if (results == null)
            return 0;
        return results.length;
    }

    public void setResults(String[] results) {
        this.results = results;
        notifyDataSetChanged();
    }

    interface FavInterface {
        void puttingData(int position);
    }

    public class MyFavViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public MyFavViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.id_tv_each_product);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            favInterface.puttingData(getAdapterPosition());
        }
    }
}
