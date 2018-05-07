package com.example.ahsanulhoque.finoffer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.Product;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemListviewHolder> {
    private List<Product> productList;

    public ListAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public ItemListviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.per_item, parent, false);
        return new ItemListviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListviewHolder holder, int position) {
        Product product = productList.get(position);
        String title = product.getName();
        String regPrice = String.valueOf(product.getRegularPrice());
        String newPrice = String.valueOf(product.getPrice());
        String location = product.getLocation();
        holder.title.setText(title);
        holder.regularPrice.setText(regPrice);
        holder.price.setText(newPrice);
        holder.location.setText(location);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ItemListviewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView location;
        TextView regularPrice;
        TextView price;

        public ItemListviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.product1TitleTV);
            image = (ImageView) itemView.findViewById(R.id.product1IV);
            location = (TextView) itemView.findViewById(R.id.locationTV);
            regularPrice = (TextView) itemView.findViewById(R.id.priceTV);
            price = (TextView) itemView.findViewById(R.id.newPriceTV);
        }
    }


}

