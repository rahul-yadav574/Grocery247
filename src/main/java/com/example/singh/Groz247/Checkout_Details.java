package com.example.singh.Groz247;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Field;

/*Created By Rahul YAdav*/

public class Checkout_Details extends Activity {

    private Button RegisterUser;
    private EditText fullnameinput,phoneinput,addressinput,emailinput,passwordinput;
    Gson gson;
    Cart_Shared_Pref newPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_address_details);

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
        RegisterUser = (Button)findViewById(R.id.PlaceOrder);
        fullnameinput =(EditText)findViewById(R.id.fullnameinput);
        phoneinput = (EditText)findViewById(R.id.phoneinput);
        addressinput = (EditText)findViewById(R.id.addressinput);
        emailinput = (EditText)findViewById(R.id.emailinput);
        passwordinput =  (EditText)findViewById(R.id.passwordinput);

        gson = new Gson();
        newPref = new Cart_Shared_Pref(getApplicationContext());

        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = fullnameinput.getText().toString();
                String Phone = phoneinput.getText().toString();
                String Email = emailinput.getText().toString();
                String Address = addressinput.getText().toString();
                String PassWord = passwordinput.getText().toString();
                if(FullName.trim().length()>0){
                    if(Phone.trim().length()>=10){
                        if(Email.trim().length()>0 && Email.contains("@")){
                            if (Address.trim().length()>0){
                                if(PassWord.trim().length()>=8){
                                    if(UtilClass.isNetworkAvailable(getApplicationContext())){
                                    new RegisterNewUser().execute(FullName,PassWord,Address,Phone,Email);
                                }

                                    else {
                                        new MaterialDialog.Builder(Checkout_Details.this)
                                                .positiveText("Cancel")
                                                .cancelable(false)
                                                .content("NO Internet Connection")
                                                .positiveColor(R.color.material_blue_grey_900)
                                                .show();
                                    }
                                }
                                else{
                                    new MaterialDialog.Builder(Checkout_Details.this)
                                            .positiveText("Cancel")
                                            .cancelable(false)
                                            .content("Minimum Password Length Is 8")
                                            .positiveColor(R.color.material_blue_grey_900)
                                            .show();
                                }
                            }
                            else{
                                new MaterialDialog.Builder(Checkout_Details.this)
                                        .positiveText("Cancel")
                                        .cancelable(false)
                                        .content("Address Field Can't Be Empty")
                                        .positiveColor(R.color.material_blue_grey_900)
                                        .show();}
                        }
                        else {
                            new MaterialDialog.Builder(Checkout_Details.this)
                                    .positiveText("Try Again")
                                    .cancelable(false)
                                    .content("InCorrect E-Mail")
                                    .positiveColor(R.color.material_blue_grey_900)
                                    .show();}
                    }
                    else {
                        new MaterialDialog.Builder(Checkout_Details.this)
                                .positiveText("Try Again")
                                .cancelable(false)
                                .content("Phone No. Length Should Be At least 10")
                                .positiveColor(R.color.material_blue_grey_900)
                                .show();}
                }
                else{
                    new MaterialDialog.Builder(Checkout_Details.this)
                            .positiveText("Try Again")
                            .cancelable(false)
                            .content("Full Name Input Can't  Be Empty")
                            .positiveColor(R.color.material_blue_grey_900)
                            .show();}
            }
        });
    }

    protected class RegisterNewUser extends AsyncTask<String,Void,Void>{
        MaterialDialog dialog;
        boolean bool;
        @Override
        protected void onPreExecute() {
            dialog = new MaterialDialog.Builder(Checkout_Details.this)
                    .progress(true,0)
                    .content("Redirecting...")
                    .widgetColor(R.color.material_blue_grey_800)
                    .cancelable(false)
                    .show();
        }

        @Override
        protected Void doInBackground(String... params) {
            bool = newUserRequest(params[0],params[1],params[2],params[3],params[4]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            dialog.cancel();
            if(bool){
                Intent LogInRedirect = new Intent(getApplicationContext(),e_mail_verification.class);
                startActivity(LogInRedirect);
             //   Log.e("NEW_USER_REGIISTER","YOU ARE REGISTERED");
               // Log.d("Output Json ",newPref.getJsonArray());
            }
            else{
               // Log.e("NOT_REGISTER","REQUEST_FAILS");
            }
            super.onPostExecute(aVoid);
        }
    }
    private boolean newUserRequest(String username, String password,String address,String phone ,String email){
        /*JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject;
        try{
            jsonObject2.put("username",username);
            jsonObject2.put("password",password);
            jsonObject2.put("address",address);
            jsonObject2.put("phone",phone);
            jsonObject2.put("email",email);
        }

        catch (Exception e){
            Log.d("error", "error in creating login requeest method");
        }
        String url = jsonObject2.toString();
        try{
            url = URLEncoder.encode(url, "UTF-8");
        }catch(UnsupportedEncodingException e){
            Log.d("error","error in encodding the json object");
        }
*/
        String url = Constants.baseNewUserRequestUrl+username+"&mail="+email+"&password="+password+"&add="+address+"&contact="+phone;
        url = url.replace(" ","%20");
       // Log.e("response",url);
        JSONObject jsonObject = UtilClass.getJSONFromUrl(url);
        try{
            if(jsonObject.getInt("success")==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
           // Log.d("error","error in login request");
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent HomePageRedirect = new Intent (getApplicationContext(),e_mail_verification.class);
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