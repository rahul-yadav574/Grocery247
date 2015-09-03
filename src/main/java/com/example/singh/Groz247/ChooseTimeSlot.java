package com.example.singh.Groz247;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*Created By Rahul Yadav*/

public class ChooseTimeSlot extends Activity {

    private Button placeOrder;
    private RadioGroup timeSlotRadioGroup;
    private RadioButton timeSlotOne,timeSlotTwo,timeSlotThree,timeSlotFour;
    private static String Selected = "";

    Cart_Shared_Pref newPref;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time_slot);

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        newPref = new Cart_Shared_Pref(getApplicationContext());
        placeOrder = (Button)findViewById(R.id.placeOrder);
        timeSlotRadioGroup = (RadioGroup)findViewById(R.id.timeslotRadioGrp);
        timeSlotOne = (RadioButton)findViewById(R.id.timeSlotOne);
        timeSlotTwo = (RadioButton)findViewById(R.id.timeSlotTwo);

        timeSlotOne.setSelected(true);

        Selected = timeSlotOne.getText().toString();
        timeSlotRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.timeSlotOne:
                        Selected = timeSlotOne.getText().toString();
                        break;
                    case R.id.timeSlotTwo:
                        Selected = timeSlotTwo.getText().toString();
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"CHOOSE ONE TIME SLOT",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        gson = new Gson();
        final String cartjson = newPref.getJsonArray();

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Log.e("error", cartjson);
                // if(UtilClass.isNetworkAvailable(getApplicationContext())){
                new PlaceOrder().execute(cartjson, newPref.getCustomerId(),Selected);
            }
            //else{
            //      Toast.makeText(getApplicationContext(),"NO INTERNET",Toast.LENGTH_LONG).show();
            //}
            //}
        });
    }

    protected class PlaceOrder extends AsyncTask<String,Void,Void>{

        MaterialDialog dialog;
        String bool;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new MaterialDialog.Builder(ChooseTimeSlot.this)
                    .progress(true,0)
                    .content("Placing Your Order")
                    .widgetColor(R.color.material_blue_grey_800)
                    .cancelable(false)
                    .show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            new MaterialDialog.Builder(ChooseTimeSlot.this)
                    .positiveText("SHOP MORE")
                    .cancelable(false)
                    .content("Thank You! For Placing Order\n"+"Your Order Will Be Delievered Soon\n" +
                            "Our Team Will Contact You")
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            Intent NewSession = new Intent(getApplicationContext(),Category_list.class);
                            NewSession.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK  | Intent.FLAG_ACTIVITY_NEW_TASK);
                            newPref.logOutUser();
                            startActivity(NewSession);
                        }
                    }
        )
                    .positiveColor(R.color.material_blue_grey_900)
                    .show();

            super.onPostExecute(aVoid);
            dialog.cancel();
           // Log.e("finaloutput",bool);
           /* alertDialog.setPositiveButton("SHOP MORE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent LandingPage = new Intent(getApplicationContext(),Category_list.class);
                    LandingPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(LandingPage);
                }
            });
            alertDialog.setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent LandingPage = new Intent(getApplicationContext(),Category_list.class);
                    LandingPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(LandingPage);
                }
            });
            alertDialog.show();*/
        }
        @Override
        protected Void doInBackground(String... params) {
            bool = PlacingOrder(params[0],params[1],params[2]);
            return null;
        }
    }
    private String PlacingOrder(String ProductJson,String customerId,String Slot){

        String to_print;
        String url = Constants.baseOrderUrl+ProductJson+"&C_id="+customerId+"&Slot="+Slot;
        url = url.replace(" ","%20");
        Log.e("UrlCheck", "" + url);
        to_print =  getJSONFromUrl(url);
        return to_print;
    }

    public static String getJSONFromUrl(String completeurl) {
        InputStream is = null;
        String jsonstring = "";
        try {
            URL url = new URL(completeurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            is = new BufferedInputStream(urlConnection.getInputStream());
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            if (s.hasNext()) {
                jsonstring = s.next();
            }
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
          //  Log.d("error", "error in getjsonfromurl MalformedUrlexception");
        } catch (IOException e) {
           // Log.d("error", "error in getjsonfromurl Ioexception");catch (IOException e)
            return "No Internet";
        }
        return jsonstring;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent HomePageRedirect = new Intent (getApplicationContext(),Cart.class);
            HomePageRedirect.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(HomePageRedirect);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(getApplicationContext(),Cart.class);
        goBack.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goBack);

    }
}