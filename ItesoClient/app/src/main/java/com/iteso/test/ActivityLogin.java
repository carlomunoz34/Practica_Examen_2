package com.iteso.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityLogin extends AppCompatActivity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.activity_login_username);
        password = findViewById(R.id.activity_login_password);
        login = findViewById(R.id.activity_login_sign_in);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
                Intent intent = new Intent(ActivityLogin.this,ActivityMain.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(ActivitySplashScreen.MY_PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", username.getText().toString());
        editor.putString("PWD", password.getText().toString());
        editor.putBoolean("LOGGED", true);
        editor.apply();
    }
}
