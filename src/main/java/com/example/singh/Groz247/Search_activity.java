package com.example.singh.Groz247;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/*Created By Rahul Yadav*/

public class Search_activity extends Category_list {

    private ListView searchProductList;
    private vertical_product_adapter adapter;
    private TextView noProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        Intent getQuery = getIntent();
        String Query = getQuery.getStringExtra("Query");
        setTitle(Query);

        searchProductList = (ListView)findViewById(R.id.verticalprodList);
        noProducts = (TextView)findViewById(R.id.noProductForSearch);
        adapter = new vertical_product_adapter(getApplicationContext(),new ArrayList<Product_Sample_Object>());

            String url = Constants.baseSearchUrl+Query;

            searchProductList.setAdapter(adapter);
            new Search_JsonParser().execute(url);

    }


    public class Search_JsonParser extends AsyncTask<String,Void,List<Product_Sample_Object>> {

        MaterialDialog dialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new MaterialDialog.Builder(Search_activity.this)
                    .progress(true,0)
                    .content("Searching..")
                    .widgetColor(R.color.material_blue_grey_800)
                    .cancelable(false)
                    .show();
        }

        @Override
        protected void onPostExecute(List<Product_Sample_Object> resultantProductList) {
            super.onPostExecute(resultantProductList);


            adapter.changeTheList(resultantProductList);
            adapter.notifyDataSetChanged();
            if(adapter.getCount()==0){
            noProducts.setText("NO SUCH PRODUCTS");
            noProducts.setVisibility(View.VISIBLE);}


            dialog.dismiss();

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

        if (id==android.R.id.home){

            Intent  LandingPageRedirect = new Intent(getApplicationContext(),Category_list.class);
            LandingPageRedirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(LandingPageRedirect);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}