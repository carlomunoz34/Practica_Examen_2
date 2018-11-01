package com.iteso.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.iteso.test.beans.City;
import com.iteso.test.beans.Store;
import com.iteso.test.beans.User;
import com.iteso.test.database.DataBaseHandler;
import com.iteso.test.database.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplashScreen extends AppCompatActivity {

    public static final String MY_PREFERENCES = "com.iteso.sesion13.PREFERENCES";

    public StoreControl storeControl = new StoreControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        TimerTask task= new TimerTask(){
            @Override
            public void run() {
                DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplashScreen.this);
                ArrayList<Store> stores = storeControl.getStores(dh);

                if(stores.size() == 0){
                    Store guadalajaraStore = new Store(1,"Galerías","3333960825",
                                                1,20.4500,-103.1600, new City(1, "Guadalajara"));
                    Store cdmxStore = new Store(2,"Antara","5557964852",
                                                2,19.2542,-99.0739, new City(2, "CDMX"));
                    Store monterreyStore = new Store(3,"Nuevo Sur","9603333825",
                                                3,25.6714,-100.3040, new City(3, "Monterrey"));
                    storeControl.addStore(guadalajaraStore, dh);
                    storeControl.addStore(cdmxStore, dh);
                    storeControl.addStore(monterreyStore, dh);
                    Log.e("DB", "Stores added.");
                }
                Intent intent = new Intent(ActivitySplashScreen.this,ActivityMain.class);
                startActivity(intent);
                finish();
                /*User user = loadPreferences();
                if(user.isLogged()){
                    Intent intent = new Intent(ActivitySplashScreen.this,ActivityMain.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(ActivitySplashScreen.this, ActivityLogin.class);
                    startActivity(intent);
                    finish();
                }*/
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 5000);


    }

    public User loadPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME","UNKNOWN"));
        user.setPassword(sharedPreferences.getString("PWD","1234"));
        user.setLogged(sharedPreferences.getBoolean("LOGGED",false));
        return user;
    }
}