package com.iteso.test.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.test.beans.ItemProduct;

import java.util.ArrayList;

public class ItemProductControl {

    public CategoryControl categoryControl = new CategoryControl();
    public StoreControl storeControl = new StoreControl();
    private int nextIdStoreProducts = 0;
    private int nextIdProducts = 0;

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Llenado de la tabla PRODUCTS
        String getCountProduct = "SELECT COUNT(" + DataBaseHandler.PRODUCT_IDPRODUCT
                + ") FROM " + DataBaseHandler.TABLE_PRODUCT;
        Cursor cursor_nip = db.rawQuery(getCountProduct, null);
        cursor_nip.moveToNext();
        nextIdProducts = cursor_nip.getInt(0) + 1;

        itemProduct.setCode(nextIdProducts);
        values.put(DataBaseHandler.PRODUCT_IDPRODUCT, nextIdProducts);
        values.put(DataBaseHandler.PRODUCT_TITLE, itemProduct.getTitle());
        values.put(DataBaseHandler.PRODUCT_IMAGE, itemProduct.getImage());
        values.put(DataBaseHandler.PRODUCT_IDCATEGORY, itemProduct.getCategory().getId());
        db.insert(DataBaseHandler.TABLE_PRODUCT,null,values);

        values.clear();

        //Llenado de la tabla STOREPRODUCTS
        String getCountStoreProduct = "SELECT COUNT(" + DataBaseHandler.STOREPRODUCT_ID
                + ") FROM " + DataBaseHandler.TABLE_STOREPRODUCT;

        Cursor cursor_nisp = db.rawQuery(getCountStoreProduct, null);
        cursor_nisp.moveToNext();
        nextIdStoreProducts = cursor_nisp.getInt(0) + 1;
        values.put(DataBaseHandler.STOREPRODUCT_ID, nextIdStoreProducts);
        values.put(DataBaseHandler.STOREPRODUCT_IDPRODUCT, itemProduct.getCode());
        values.put(DataBaseHandler.STOREPRODUCT_IDSTORE, itemProduct.getStore().getId());
        db.insert(DataBaseHandler.TABLE_STOREPRODUCT,null,values);

        Log.e("DB","StoreProducts: "+(nextIdStoreProducts) + "elemento.");
        Log.e("DB","Products: "+ (nextIdProducts) + "elemento.");

        try{
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "SELECT p." + DataBaseHandler.PRODUCT_IDPRODUCT + ","
                + DataBaseHandler.PRODUCT_TITLE + ","
                + DataBaseHandler.PRODUCT_IMAGE + ","
                + DataBaseHandler.STOREPRODUCT_IDSTORE + ","
                + DataBaseHandler.PRODUCT_IDCATEGORY
                + " FROM " + DataBaseHandler.TABLE_PRODUCT + " p JOIN "
                + DataBaseHandler.TABLE_STOREPRODUCT + " sp ON p."
                + DataBaseHandler.PRODUCT_IDPRODUCT + " = sp." + DataBaseHandler.STOREPRODUCT_IDPRODUCT
                + " WHERE " + DataBaseHandler.PRODUCT_IDCATEGORY + " = "+ idCategory;


        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            itemProduct.setStore(storeControl.getStoreById(cursor.getInt(3), dh));
            itemProduct.setCategory(categoryControl.getCategoryById(cursor.getInt(4), dh));
            itemProducts.add(itemProduct);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return itemProducts;
    }

    public Cursor getItemProducts(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();

        String select = "SELECT p."
                + DataBaseHandler.PRODUCT_TITLE + ","
                + DataBaseHandler.STORE_NAME + ","
                + DataBaseHandler.PRODUCT_IDCATEGORY
                + " FROM " + DataBaseHandler.TABLE_PRODUCT +
                " p JOIN " + DataBaseHandler.TABLE_STOREPRODUCT +
                " sp ON p." + DataBaseHandler.PRODUCT_IDPRODUCT + " = sp." + DataBaseHandler.STOREPRODUCT_IDPRODUCT +
                " JOIN " + DataBaseHandler.TABLE_STORE + " st ON sp." + DataBaseHandler.STOREPRODUCT_ID + " = st." +
                DataBaseHandler.STORE_ID;


        return db.rawQuery(select, null);
    }
}
