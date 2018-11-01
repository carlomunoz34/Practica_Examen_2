package com.iteso.test.tools;


import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    private Context context;

    public WebAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }
}