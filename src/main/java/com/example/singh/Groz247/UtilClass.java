package com.example.singh.Groz247;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by Rahul Yadav on 20-08-2015.
 */
public class UtilClass {
    //to Parse The  Json Object From Urll
    public static JSONObject getJSONFromUrl(String completeurl){
        InputStream is = null;

        JSONObject jsonObject=null;
        String jsonstring="";
        try {
            URL url = new URL(completeurl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            is = new BufferedInputStream(urlConnection.getInputStream());
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            if(s.hasNext()){
                jsonstring= s.next();
            }
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            //Log.d("error", "error in getjsonfromurl MalformedUrlexception");
        }

        catch(SocketTimeoutException r){

            Log.e("Socket","error in timeout");
            r.printStackTrace();

        }catch (IOException e){

           Log.d("error", "error in getjsonfromurl Ioexception");


        }


        try {
            jsonObject = new JSONObject(jsonstring);
        } catch (JSONException e) {
           // Log.d("error", "Json exception");
        }
        return jsonObject;
    }
       //to Check Whether Connection Is Available
    public static Boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo  = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();


    }
}