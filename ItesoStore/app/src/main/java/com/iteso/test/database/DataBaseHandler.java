package com.iteso.test.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.iteso.test.beans.Category;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;

    private static DataBaseHandler dataBaseHandler;

    //Tabla City
    public static final String TABLE_CITY= "City";
    public static final String CITY_ID = "Id";
    public static final String CITY_NAME = "Name";

    //Tabla Category
    public static final String TABLE_CATEGORY= "Category";
    public static final String CATEGORY_ID = "Id";
    public static final String CATEGORY_NAME = "Name";

    //Tabla Store
    public static final String TABLE_STORE= "Store";
    public static final String STORE_ID = "Id";
    public static final String STORE_NAME = "Name";
    public static final String STORE_PHONE = "Phone";
    public static final String STORE_IDCITY = "IdCity";
    public static final String STORE_THUMBNAIL = "Thumbnail";
    public static final String STORE_LATITUDE = "Latitude";
    public static final String STORE_LONGITUDE = "Longitude";

    //Tablas Product
    public static final String TABLE_PRODUCT = "Product";
    public static final String PRODUCT_IDPRODUCT = "IdProduct";
    public static final String PRODUCT_TITLE  = "Title";
    public static final String PRODUCT_IMAGE  = "Image";
    public static final String PRODUCT_IDCATEGORY  = "IdCategory";

    //Tablas StoreProduct
    public static final String TABLE_STOREPRODUCT = "StoreProduct";
    public static final String STOREPRODUCT_ID = "Id";
    public static final String STOREPRODUCT_IDPRODUCT  = "IdProduct";
    public static final String STOREPRODUCT_IDSTORE = "IdStore";



    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null)
            dataBaseHandler = new DataBaseHandler(context);
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tableCity = "CREATE TABLE "+ TABLE_CITY + " ("
                + CITY_ID + " INTEGER PRIMARY KEY,"
                + CITY_NAME + " TEXT)";
        String tableCategory = "CREATE TABLE "+ TABLE_CATEGORY + " ("
                + CATEGORY_ID + " INTEGER PRIMARY KEY,"
                + CATEGORY_NAME + " TEXT)";
        String tableStore = "CREATE TABLE "+ TABLE_STORE + " ("
                + STORE_ID + " INTEGER PRIMARY KEY,"
                + STORE_NAME + " TEXT,"
                + STORE_PHONE + " TEXT,"
                + STORE_IDCITY + " INTEGER,"
                + STORE_THUMBNAIL + " INTEGER,"
                + STORE_LATITUDE + " DOUBLE,"
                + STORE_LONGITUDE + " DOUBLE)";
        String tableProduct = "CREATE TABLE "+ TABLE_PRODUCT + " ("
                + PRODUCT_IDPRODUCT + " INTEGER PRIMARY KEY,"
                + PRODUCT_TITLE + " TEXT,"
                + PRODUCT_IMAGE + " INTEGER,"
                + PRODUCT_IDCATEGORY + " INTEGER)";
        String tableStoreProduct = "CREATE TABLE "+ TABLE_STOREPRODUCT + " ("
                + STOREPRODUCT_ID + " INTEGER PRIMARY KEY,"
                + STOREPRODUCT_IDPRODUCT + " INTEGER,"
                + STOREPRODUCT_IDSTORE + " INTEGER)";

        db.execSQL(tableCity);
        db.execSQL(tableCategory);
        db.execSQL(tableStore);
        db.execSQL(tableProduct);
        db.execSQL(tableStoreProduct);


        String insertCategoryTechnology = "INSERT INTO " + TABLE_CATEGORY + " VALUES(" + 1 + ",'Technology')";
        String insertCategoryHome = "INSERT INTO " + TABLE_CATEGORY + " VALUES(" + 2 + ",'Home')";
        String insertCategoryElectronics = "INSERT INTO " + TABLE_CATEGORY + " VALUES(" + 3 + ",'Electronics')";

        db.execSQL(insertCategoryTechnology);
        db.execSQL(insertCategoryHome);
        db.execSQL(insertCategoryElectronics);


        String insertCity1 = "INSERT INTO "+ TABLE_CITY +" VALUES("+ 1 +",'Guadalajara')";
        String insertCity2 = "INSERT INTO "+ TABLE_CITY +" VALUES("+ 2 +",'CDMX')";
        String insertCity3 = "INSERT INTO "+ TABLE_CITY +" VALUES("+ 3+",'Monterrey')";

        db.execSQL(insertCity1);
        db.execSQL(insertCity2);
        db.execSQL(insertCity3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
