package com.carlo.itesoclient;

import android.os.Bundle;
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

        productsTechnology = new ArrayList<>();
        productsHome = new ArrayList<>();
        productsElectronics = new ArrayList<>();
        products = new ArrayList<>();

        products.add(new Item("Hola", "TECHNOLOGY", "Farmacias Guadalajara", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quis neque odio. Quisque ut scelerisque sapien. Integer tempor ligula eget."));
        products.add(new Item("Mundo", "HOME", "Electra", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        products.add(new Item("Cruel", "ELECTRONICS", "Coppel", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quis neque odio."));

        sortProductsInCategories();

        adapterItem = new AdapterItem(productsTechnology, this);
        recyclerView.setAdapter(adapterItem);
    }

    private void sortProductsInCategories() {
        for (Item item : products) {
            switch (item.getCategory()) {
                case "TECHNOLOGY":
                    productsTechnology.add(item);
                    break;
                case "HOME":
                    productsHome.add(item);
                    break;
                case "ELECTRONICS":
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
        }
        recyclerView.setAdapter(adapterItem);
        return true;
    }
}
