package com.example.singh.Groz247;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Cart extends Activity {


    private ListView cartlist;
    private TextView noInternetForCart;
    private Button cartzeroElements;
    Cart_Shared_Pref newPref;
    ArrayList<New_Item_Object> Products_list;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }

        getActionBar().setHomeButtonEnabled(true);

        gson = new Gson();
        newPref = new Cart_Shared_Pref(getBaseContext());

        Products_list  = newPref.getItem_objects();
        noInternetForCart = (TextView)findViewById(R.id.noInternetForCart);
        cartzeroElements = (Button)findViewById(R.id.cartzeroElements);


        cartzeroElements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LandingPage = new Intent(getApplicationContext(),Category_list.class);
                LandingPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(LandingPage);
            }
        });

        if(UtilClass.isNetworkAvailable(this)) {
            if (newPref.getCartSize() > 0) {
                cartlist = (ListView) findViewById(R.id.cartlist);
                cartlist.setAdapter(new cart_list_adapter(this, Products_list));
                setTitle("Cart");
            } else {
                cartzeroElements.setText("ADD SOME ITEMS");
                cartzeroElements.setVisibility(View.VISIBLE);
            }
        }

        else{
            noInternetForCart.setText("Ooops...No Internet Connection");
            noInternetForCart.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent HomePageRedirect = new Intent (getApplicationContext(),Category_list.class);
            HomePageRedirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(HomePageRedirect);
            return true;
        }
        else if (id == R.id.CheckOutMenuButton){

            if(UtilClass.isNetworkAvailable(this)){

                if (newPref.getCartSize()>0){
                    Intent LoginRedirect = new Intent(getApplicationContext(),e_mail_verification.class);
                    startActivity(LoginRedirect);
                    return true;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}