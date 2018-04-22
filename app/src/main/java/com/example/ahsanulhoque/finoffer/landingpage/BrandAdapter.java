package com.example.ahsanulhoque.finoffer.landingpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.R;


public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandListViewHolder> {
    private String[] data;
    public BrandAdapter(String[]data){
        this.data = data;
    }

    @Override
    public BrandListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.brand_item, parent, false);

        return new BrandAdapter.BrandListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BrandListViewHolder holder, int position) {
        String title = data[position];
        holder.brandTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class BrandListViewHolder extends RecyclerView.ViewHolder{
        ImageView brandImg;
        TextView brandTitle;
        public BrandListViewHolder(View itemView) {
            super(itemView);
            brandTitle = (TextView) itemView.findViewById(R.id.brandTitle);
            brandImg = (ImageView) itemView.findViewById(R.id.brand1IV);
        }
    }
}