package com.example.singh.Groz247;

/*Created By Rahul Yadav*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends Activity {
    public static final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent MainPage = new Intent();
                MainPage.setClass(getApplicationContext(), Category_list.class);
                MainPage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(MainPage);
                Splash_Screen.this.finish();
                // transition from splash screen to Category List
                overridePendingTransition(R.xml.fadein,
                        R.xml.fadeout);
            }
        }, SPLASH_TIME);}
    }