package com.iteso.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iteso.test.beans.Category;
import com.iteso.test.beans.ItemProduct;
import com.iteso.test.beans.Store;
import com.iteso.test.database.CategoryControl;
import com.iteso.test.database.DataBaseHandler;
import com.iteso.test.database.ItemProductControl;
import com.iteso.test.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    private Spinner image, category, store;
    private EditText title;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        final DataBaseHandler dh = DataBaseHandler.getInstance(this);
        final StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();

        image = findViewById(R.id.activity_item_images);
        title = findViewById(R.id.activity_item_title);
        category = findViewById(R.id.activity_item_categories);
        store = findViewById(R.id.activity_item_stores);
        save = findViewById(R.id.activity_item_save);

        final String[] images = getResources().getStringArray(R.array.images);
        final ArrayList<Category> categoriesAsItem = categoryControl.getCategories(dh);
        final ArrayList<Store> storesAsItems = storeControl.getStores(dh);

        final ArrayList<String> categories = new ArrayList<>(), stores = new ArrayList<>();
        for(Category category : categoriesAsItem)
            categories.add(category.getName());

        for(Store store : storesAsItems)
            stores.add(store.getName());

        image.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, images));

        category.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, categories.toArray()));

        store.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, stores.toArray()));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemTitle = title.getText().toString();
                Category itemCategory = new Category();
                Store itemStore = new Store();
                Integer itemImage = image.getSelectedItemPosition() + 1;

                for(Store s : storesAsItems)
                    if(s.getName().equals(store.getSelectedItem().toString()))
                        itemStore = s;

                for(Category c : categoriesAsItem)
                    if(c.getName().equals(category.getSelectedItem().toString()))
                        itemCategory = c;

                ItemProduct itemProduct = new ItemProduct(itemTitle, itemImage, itemStore, itemCategory);
                //Toast.makeText(ActivityItem.this, itemProduct.toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("ITEM",itemProduct);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
