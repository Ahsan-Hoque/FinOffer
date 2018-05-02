package com.example.ahsanulhoque.finoffer.landingpage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ahsanulhoque.finoffer.R;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemListviewHolder> {
    private String[] data;
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

    public class ItemListviewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;

        public ItemListviewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.product1TitleTV);
            image = (ImageView) itemView.findViewById(R.id.product1IV);
        }
    }


}

