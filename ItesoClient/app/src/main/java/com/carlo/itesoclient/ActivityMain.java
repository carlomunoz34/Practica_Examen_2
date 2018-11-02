package com.carlo.itesoclient;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.carlo.itesoclient.beans.Item;

import java.util.ArrayList;

public class ActivityMain extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> productsTechnology, productsHome, productsElectronics, products;
    private AdapterItem adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.activity_main_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        update();
    }

    private void update() {
        productsTechnology = new ArrayList<>();
        productsHome = new ArrayList<>();
        productsElectronics = new ArrayList<>();
        products = new ArrayList<>();

        Uri uri = Uri.parse("content://com.iteso.test.PROVIDER");
        String[] projection = {"Products"};

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, projection, null, null);

        while(cursor.moveToNext()){
            String itemTitle = cursor.getString(0);
            String itemStore = cursor.getString(1);
            int itemCategory = cursor.getInt(2);

            products.add(new Item(itemTitle, itemCategory, itemStore));
        }

        try{
            cursor.close();
        }catch (Exception e){

        }

        sortProductsInCategories();

        adapterItem = new AdapterItem(productsTechnology, this);
        recyclerView.setAdapter(adapterItem);
    }

    private void sortProductsInCategories() {
        for (Item item : products) {
            switch (item.getCategory()) {
                case 1:
                    productsTechnology.add(item);
                    break;
                case 2:
                    productsHome.add(item);
                    break;
                case 3:
                    productsElectronics.add(item);
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_technology:
                adapterItem.setmDataSet(productsTechnology);
                break;
            case R.id.menu_home:
                adapterItem.setmDataSet(productsHome);
                break;
            case R.id.menu_electronics:
                adapterItem.setmDataSet(productsElectronics);
                break;
            case R.id.menu_refresh:
                update();
                break;
        }
        recyclerView.setAdapter(adapterItem);
        return true;
    }
}
