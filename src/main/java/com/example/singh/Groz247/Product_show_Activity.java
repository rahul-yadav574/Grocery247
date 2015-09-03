package com.example.singh.Groz247;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*Created By Rahul Yadav*/


public class Product_show_Activity extends Category_list {
    private TextView noInternet;
    private Button Refresh;
    private vertical_product_adapter adapter;
    static final String [] categorynameList = new String[] {"Dals & Pulses","Wheat,Rice & Sugar","Oil & Ghee","Beverages","Spices & Herbs","Kitchen Care","Patanjali Section","Confectionary & Bakery"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_show_);


        try{
        getActionBar().setHomeButtonEnabled(true);}
        catch(NullPointerException e){
            e.printStackTrace();
        }

        Intent sub = getIntent();
        String po = sub.getStringExtra("position");
        setTitle(po);

        ListView verticalprodList =(ListView) findViewById(R.id.verticalprodList);
        noInternet = (TextView) findViewById(R.id.noInternet);
        Refresh = (Button) findViewById(R.id.refresh);

       switch(po){
           case "Dals & Pulses":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl1);
                    break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });

                   break;
               }
           case "Wheat,Rice & Sugar":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl2);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Oil & Ghee":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl3);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Beverages":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl4);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Spices & Herbs":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl5);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);

                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Kitchen Care":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl6);

                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Patanjali Section":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl7);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;

               }
           case "Confectionary & Bakery":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl8);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Namkeen & Biscuits":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl9);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Dry Fruits":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl10);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Pasta & Noodles":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl11);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);

                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Bathroom Products":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl12);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Instant Food Items":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl13);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "Personal Care":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl14);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
           case "HouseHold Products":
               if (UtilClass.isNetworkAvailable(this)){
                   adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());
                   verticalprodList.setAdapter(adapter);
                   (new Async_Task_JsonParser()).execute(Constants.CategoryUrl15);
                   break;}
               else {
                   noInternet.setText("Ooops...No Internet Connection");
                   noInternet.setVisibility(View.VISIBLE);
                   Refresh.setVisibility(View.VISIBLE);
                   Refresh.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                           Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(Redirect);
                       }
                   });
                   break;
               }
       }
       /* check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),""+vertical_product_adapter.getCount(),Toast.LENGTH_SHORT).show();
            }
        });
*/
    }
    //AsyncTask Class To Make The Http request And Getting The Products Array From the Url
    public class Async_Task_JsonParser extends AsyncTask<String,Void,List<Product_Sample_Object> >{


        MaterialDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new MaterialDialog.Builder(Product_show_Activity.this)
                    .progress(true,0)
                    .content("Getting Products")
                    .widgetColor(R.color.material_blue_grey_800)
                    .cancelable(false)
                    .show();

        }

        @Override
        protected void onPostExecute(List<Product_Sample_Object> resultantProductList) {
            super.onPostExecute(resultantProductList);


                if (resultantProductList==null) {
                    Log.e("Present","Present Hai ye");
                    noInternet.setText("Check Internet Connectivity");
                    noInternet.setVisibility(View.VISIBLE);
                    Refresh.setVisibility(View.VISIBLE);
                    Refresh.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Redirect = new Intent(getApplicationContext(),Category_list.class);
                            Redirect.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(Redirect);
                        }
                    });
                    dialog.cancel();

                }

                else {

                    adapter.changeTheList(resultantProductList);
                    adapter.notifyDataSetChanged();
                    dialog.cancel();
                }
            }



        @Override
        protected List<Product_Sample_Object> doInBackground(String... params) {
            List<Product_Sample_Object> resultantProductList = new ArrayList<Product_Sample_Object>();
            JSONObject jsonObj;
            JSONArray arr = null;
            try{
                jsonObj = UtilClass.getJSONFromUrl(params[0]);
             /*   switch (n){
                    case"Category1":
                        arr = jsonObj.;
                    case "Category2":
                        arr = jsonObj.getJSONArray("student");
                    case "Category3":
                        arr = jsonObj.getJSONArray("student");
                    case "Category4":
                        arr = jsonObj.getJSONArray("student");
                    case "Category5":
                        arr = jsonObj.getJSONArray("student");
                    case "Category6":
                        arr = jsonObj.getJSONArray("student");
                    case "Category7":
                        arr = jsonObj.getJSONArray("student");
                    case "Category8":
                        arr = jsonObj.getJSONArray("student");

                }*/


                    arr = jsonObj.getJSONArray("finalarray");

                    for (int i = 0; i < arr.length(); i++) {
                        resultantProductList.add(convertItemDetails(arr.getJSONObject(i)));
                    }
                return resultantProductList;
            }

            catch(Throwable t) {
                t.printStackTrace();
            }
            return null;
        }

       }


    //method to return object  from jsonobject to arraylist(resultantproductlist)
    private Product_Sample_Object convertItemDetails(JSONObject obj) throws JSONException {
        String name = obj.getString("name");
        String costprize = obj.getString("costprice");
        String sellprize = obj.getString("ourprice");
        String idofProduct = obj.getString("id");
        int discount = (Integer.parseInt(sellprize)/Integer.parseInt(costprize))*100;
        String imageurl = obj.getString("url");

        return new Product_Sample_Object(name, costprize, sellprize,idofProduct,discount,imageurl,0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
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
        return super.onOptionsItemSelected(item);
    }
}