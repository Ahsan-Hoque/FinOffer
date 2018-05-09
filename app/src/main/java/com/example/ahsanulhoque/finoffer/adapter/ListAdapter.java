package com.example.ahsanulhoque.finoffer.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.R;
import com.example.ahsanulhoque.finoffer.domain.Product;

import java.io.InputStream;
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

        if (!TextUtils.isEmpty(product.getImageUrl())){
            new DownloadImageTask(holder.image, 160, 120).execute(product.getImageUrl());
        }
        String title = product.getName();
        String regPrice = String.valueOf(product.getRegularPrice());
        String newPrice = String.valueOf(product.getPrice());
        String location = product.getLocation();

        holder.title.setText(title);
        holder.regularPrice.setText("€" + regPrice);
        holder.price.setText("€" + newPrice);
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        int height, width;

        public DownloadImageTask(ImageView bmImage, int height, int width) {
            this.bmImage = bmImage;
            this.height = height;
            this.width = width;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, this.height, this.width, false));
        }
    }


}

