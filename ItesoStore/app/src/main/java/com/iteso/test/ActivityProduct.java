package com.iteso.test;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.test.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {

    ImageView image;
    EditText title, store, location, phone;
    ItemProduct itemProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        itemProduct = getIntent().getParcelableExtra("ITEM");

        image = findViewById(R.id.activity_product_image);
        title = findViewById(R.id.activity_product_title);
        store = findViewById(R.id.activity_product_store);
        location = findViewById(R.id.activity_product_location);
        phone = findViewById(R.id.activity_product_phone);
        title.setText(itemProduct.getTitle());
        store.setText(itemProduct.getStore().toString());
        //location.setText(itemProduct.getLocation());
        //phone.setText(itemProduct.getPhone());

         switch(itemProduct.getImage()){
            case 0: image.setImageResource(R.drawable.mac); break;
            case 1: image.setImageResource((R.drawable.alienware)); break;
            default:image.setImageResource(R.drawable.mac); break;
        }
    }

    public void saveChanges(View v){
        itemProduct.setTitle(title.getText().toString());
        //itemProduct.setStore(store.getText().toString());
        //itemProduct.setLocation(location.getText().toString());
        //itemProduct.setPhone(phone.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("ITEM",itemProduct);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    public void cancelChanges(View v){
        Intent intent = new Intent(this, ActivityMain.class);
        setResult(Activity.RESULT_CANCELED,intent);
        startActivity(intent);
    }
}
