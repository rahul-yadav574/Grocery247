package com.example.singh.Groz247;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.reflect.Field;

/*Created By RAhul Yadav*/

public class Category_list extends Activity {
    GridView categorygrid;
    private vertical_product_adapter adapter;
    static final String [] categorynameList = new String[] {"Dals & Pulses","Wheat,Rice & Sugar","Oil & Ghee","Beverages","Spices & Herbs","Kitchen Care","Patanjali Section","Confectionary & Bakery","Namkeen & Biscuits","Dry Fruits","Pasta & Noodles","Bathroom Products","Instant Food Items","Personal Care","Household Products"};
    static final int [] categorypicList = new int[] {R.drawable.dals,R.drawable.wheat,R.drawable.oils,R.drawable.bevrages,R.drawable.spices,R.drawable.kitchen,R.drawable.patanjli,R.drawable.bakery,R.drawable.biscuit,R.drawable.dryfruits,R.drawable.pasta,R.drawable.bathroom,R.drawable.instant,R.drawable.personalcare,R.drawable.household};
    MaterialDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        categorygrid = (GridView)findViewById(R.id.categorygrid);
        categorygrid.setAdapter(new category_list_adapter(getApplicationContext(), categorynameList, categorypicList));

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

        alert = new MaterialDialog.Builder(this)
                .title("Enter Query")
                .cancelable(true)
                .positiveColor(R.color.material_blue_grey_800)
                .positiveText("Search")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Product Name","", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        String Query = input.toString();

                        if (Query.trim().length()>0){
                        if(UtilClass.isNetworkAvailable(getApplicationContext())){
                        Intent SearchPage = new Intent(getApplicationContext(),Search_activity.class);
                        SearchPage.putExtra("Query",Query);
                        startActivity(SearchPage);}

                        else {

                            new MaterialDialog.Builder(Category_list.this)
                                    .positiveText("Cancel")
                                    .cancelable(false)
                                    .content("No Internet Connection")
                                    .positiveColor(R.color.material_blue_grey_900)
                                    .show();
                        }}
                        else{
                            Toast.makeText(getApplicationContext(),"Enter Something To Search",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        final Intent intent = new Intent(getApplicationContext(),Product_show_Activity.class);

        categorygrid.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                intent.putExtra("position","Dals & Pulses");
                                startActivity(intent);
                                break;
                            case 1:
                                intent.putExtra("position","Wheat,Rice & Sugar");
                                startActivity(intent);
                                break;
                            case 2:
                                intent.putExtra("position","Oil & Ghee");
                                startActivity(intent);
                                break;
                            case 3:
                                intent.putExtra("position","Beverages");
                                startActivity(intent);
                                break;
                            case 4:
                                intent.putExtra("position","Spices & Herbs");
                                startActivity(intent);
                                break;
                            case 5:
                                intent.putExtra("position","Kitchen Care");
                                startActivity(intent);
                                break;
                            case 6:
                                intent.putExtra("position","Patanjali Section");
                                startActivity(intent);
                                break;
                            case 7:
                                intent.putExtra("position","Confectionary & Bakery");
                                startActivity(intent);
                                break;
                            case 8:
                                intent.putExtra("position","Namkeen & Biscuits");
                                startActivity(intent);
                                break;
                            case 9:
                                intent.putExtra("position","Dry Fruits");
                                startActivity(intent);
                                break;
                            case 10:
                                intent.putExtra("position","Pasta & Noodles");
                                startActivity(intent);
                                break;
                            case 11:
                                intent.putExtra("position","Bathroom Products");
                                startActivity(intent);
                                break;
                            case 12:
                                intent.putExtra("position","Instant Food Items");
                                startActivity(intent);
                                break;
                            case 13:
                                intent.putExtra("position","Personal Care");
                                startActivity(intent);
                                break;
                            case 14:
                                intent.putExtra("position","HouseHold Products");
                                startActivity(intent);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
    }
    //reference to menu for implementing search view

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.help:
                new MaterialDialog.Builder(Category_list.this)
                        .positiveText("Cancel")
                        .cancelable(false)
                        .content("In Case You Don't Find A Product\n" +
                                "Contact : 8586874695")
                        .callback(new MaterialDialog.ButtonCallback() {
                                      @Override
                                      public void onPositive(MaterialDialog dialog) {
                                          alert.autoDismiss(true);
                                      }
                                  }
                        )
                        .positiveColor(R.color.material_blue_grey_900)
                        .show();

                return true;
            case R.id.mycart:
                Intent Cart = new Intent(getApplicationContext(),Cart.class);
                startActivity(Cart);
                return true;
            case R.id.seaarchIcon:
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }
}
