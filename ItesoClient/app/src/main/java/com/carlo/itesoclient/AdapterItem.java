package com.carlo.itesoclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carlo.itesoclient.beans.Item;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolder> {
    private ArrayList<Item> mDataSet;
    private Context context;

    public AdapterItem(ArrayList<Item> mDataSet, Context context) {
        this.mDataSet = mDataSet;
        this.context = context;
    }

    public void setmDataSet(ArrayList<Item> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public AdapterItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItem.ViewHolder holder, final int i) {
        holder.title.setText(mDataSet.get(i).getTitle());

        holder.store.setText(String.format("%s %s",
                context.getResources().getString(R.string.store), mDataSet.get(i).getStore()));

        holder.description.setText(String.format("%s %s",
                context.getResources().getString(R.string.description), mDataSet.get(i).getDescription()));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, store, description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.product_title);
            store = itemView.findViewById(R.id.product_store);
            description = itemView.findViewById(R.id.product_description);
        }
    }
}
