package com.iteso.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.test.beans.ItemProduct;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private ArrayList<ItemProduct> mDataSet;
    private Context context;


    //ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button mDetail;
        public TextView mProductTitle;
        public TextView mProductStore;
        public TextView mProductCity;
        public TextView mProductPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;
        public ViewHolder(View v){
            super(v);
            mEventLayout = v.findViewById(R.id.item_product_layout);
            mDetail = v.findViewById(R.id.item_product_detail);
            mProductTitle = v.findViewById(R.id.item_product_title);
            mProductStore = v.findViewById(R.id.item_product_store);
            mProductCity = v.findViewById(R.id.item_product_location);
            mProductPhone = v.findViewById(R.id.item_product_phone);
            mProductImage = v.findViewById(R.id.item_product_image);
            mProductThumbnail = v.findViewById(R.id.item_product_thumbnail);
        }
    }

    //Constructor
    public AdapterProduct(Context context, ArrayList<ItemProduct> myDataSet){
        this.mDataSet = myDataSet;
        this.context = context;
    }

    //Create new views
   public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
   }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getTitle());
        holder.mProductStore.setText(mDataSet.get(position).getStore().toString());
        holder.mProductCity.setText(mDataSet.get(position).getStore().getCity().getName());
        //holder.mProductPhone.setText(mDataSet.get(position).getPhone());
        switch(mDataSet.get(position).getImage()){
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac); break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware); break;
        }
        Bitmap bitmap = ((BitmapDrawable)holder.mProductThumbnail.getDrawable()).getBitmap();
        holder.mProductThumbnail.setImageBitmap(bitmap);

        //Show Items Info
        holder.mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mDataSet.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });

        /*//Call to phone number
        holder.mProductPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mDataSet.get(position).getPhone()));
                //context.startActivity(intent);
            }
        });

        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProduct itemProduct = mDataSet.get(position);
                Intent intent = new Intent(context,ActivityProduct.class);
                intent.putExtra("ITEM", itemProduct);
                ((ActivityMain) context).startActivityForResult(intent, 1);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
