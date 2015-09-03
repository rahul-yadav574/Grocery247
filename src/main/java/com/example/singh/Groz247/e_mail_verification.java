package com.example.singh.Groz247;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import org.json.JSONObject;

/*Created By Rahul Yadav*/

public class e_mail_verification extends Activity {

    Cart_Shared_Pref newPref;
    Button newuser,placeordernew;
    EditText username,password;
    TextView totalPrice,shippingHint,paybleamount,hintExtra;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_mail_verification);

        getActionBar().setHomeButtonEnabled(true);

        newPref = new Cart_Shared_Pref(getApplicationContext());
        newuser = (Button) findViewById(R.id.newuserregistration);
        placeordernew = (Button) findViewById(R.id.PlaceOrder);
        placeordernew.setText("Proceed");
        username = (EditText)findViewById(R.id.mainmailinput);
        password  =(EditText)findViewById(R.id.mainpasswordinput);

        totalPrice = (TextView)findViewById(R.id.totalamountString);
        shippingHint = (TextView)findViewById(R.id.ShippingHint);
        paybleamount = (TextView)findViewById(R.id.paybleAmount);
        hintExtra = (TextView) findViewById(R.id.hintExtra);

        totalPrice.setTextColor(getResources().getColor(R.color.material_blue_grey_800));
        totalPrice.setText("Total Price : ₹" + newPref.getTotalPrice());

        if(newPref.getTotalPrice()>250){

            paybleamount.setText("Amount Payble : ₹ "+newPref.getTotalPrice());


            shippingHint.setText("*Delievery Charges : ₹" + 0 );}

        else{
            paybleamount.setText("Amount payble : ₹"+(newPref.getTotalPrice()+25));
            shippingHint.setTextColor(getResources().getColor(R.color.material_blue_grey_800));
            shippingHint.setText("Delievery Charges : ₹"+25);
        }
        gson = new Gson();

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LandingPage = new Intent(getBaseContext(),Checkout_Details.class);
                startActivity(LandingPage);
            }
        });

        placeordernew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserName = username.getText().toString();
                String PassWord = password.getText().toString();
                if(UserName.trim().length()!=0 && PassWord.trim().length()>0){
                    if (UtilClass.isNetworkAvailable(getApplicationContext())){
                        new UserRegisterCheck().execute(UserName,PassWord);}
                    else{

                        username.setText("");
                        password.setText("");
                        new MaterialDialog.Builder(e_mail_verification.this)
                                .positiveText("Cancel")
                                .cancelable(false)
                                .content("NO Internet Connection")
                                .positiveColor(R.color.material_blue_grey_900)
                                .show();

                    }
                    }
                else {

                    username.setText("");
                    password.setText("");
                    Toast.makeText(getApplicationContext(),"UserName And Password Don't Match",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    protected class UserRegisterCheck extends AsyncTask<String,Void,Void>{

        MaterialDialog dialog;

        String bool;
        @Override
        protected void onPreExecute() {
            dialog = new MaterialDialog.Builder(e_mail_verification.this)
                    .progress(true,0)
                    .content("Logging In")
                    .widgetColor(R.color.material_blue_grey_800)
                    .cancelable(false)
                    .show();
        }

        @Override
        protected Void doInBackground(String... params) {
            bool = loginRequest(params[0],params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.cancel();
            super.onPostExecute(aVoid);
            if(bool.length()>0 && !bool.matches("False")){
                newPref.saveCustomerId(bool);
               // Log.e("the confirmation",bool);
                Intent TimeSlotPage = new Intent(getApplicationContext(),ChooseTimeSlot.class);
                startActivity(TimeSlotPage);
            }
            else{
                new MaterialDialog.Builder(e_mail_verification.this)
                        .positiveText("Try Again")
                        .cancelable(true)
                        .content("Details Provided Not Match")
                        .positiveColor(R.color.material_blue_grey_900)
                        .show();
            }
        }
    }

    private String loginRequest(String username, String password){

        JSONObject jsonObject;

       /* try{
            jsonObject2.put("password",password);
            jsonObject2.put("mail",username);

        }

        catch (Exception e){
            Log.d("error","error in creating login requeest method");
        }

        String url = jsonObject2.toString();

        try{
            url = URLEncoder.encode(url,"UTF-8");
        }catch(UnsupportedEncodingException e){
            Log.d("error","error in encodding the json object");
        }
*/
        String url = Constants.baseLoginRequestUrl+username+"&"+"password="+password;
        Log.e("UrlCheck",""+url);
        jsonObject = UtilClass.getJSONFromUrl(url);
        try{
            if(jsonObject.getString("C_id")!=null){
                return jsonObject.getString("C_id");
            }
            else{
                return "False";
            }
        }
        catch (Exception e){
           // Log.d("error","error in login reqquest");
        }
        return "False";
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