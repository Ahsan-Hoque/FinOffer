package com.example.ahsanulhoque.finoffer.landingpage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahsanulhoque.finoffer.AddProduct;
import com.example.ahsanulhoque.finoffer.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemListviewHolder> {
    public String[] data;
    public ListAdapter(String[] data){
        this. data = data;
    }

    @Override
    public ItemListviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.per_item, parent, false);
        return new ItemListviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemListviewHolder holder, int position) {
        String title = data[position];
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ItemListviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView title;
        public Context context;

        public ItemListviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.product1TitleTV);
            image = (ImageView) itemView.findViewById(R.id.product1IV);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position  =   getAdapterPosition();
            Log.w("aaaaaaaaaaaaaaa", "Selected=>"+position+"id:"+view.getId());

            Intent intent = new Intent(context, AddProduct.class);
            context.startActivity(intent);

            switch (view.getId()){
                case R.id.product1IV:
                    Log.w("aaaaaaaaaaaaaaa", "Selected"+position);
                    break;
                 default:
                     Log.w("aaaaaaaaaaaaaaa", "Selected"+position+ "id:"+view.getId());
            }
        }
    }

    /*public static class ItemListviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView name;
        ImageView image;
        TextView title;

        public ItemListviewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
//            title = (TextView) itemView.findViewById(R.id.product1TitleTV);
            image = (ImageView) itemView.findViewById(R.id.product1IV);
            title = (TextView) itemView.findViewById(R.id.product1TitleTV);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }*/


}

