package com.example.ahsanulhoque.finoffer.landingpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ahsanulhoque on 25/04/2018.
 */

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.MyViewHolder> {

    private Context mContext;
    private List <Product> mData;

    public ProductViewAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
