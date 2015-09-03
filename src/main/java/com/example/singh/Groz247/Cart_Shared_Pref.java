package com.example.singh.Groz247;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


;

/**
 * Created by mahesh singh yadav on 11-08-2015.
 */
public class Cart_Shared_Pref  {

    private static final String SHARED_PREF_NAME = "CART_ITEMS";
    private static final String NAME_OF_CART_LIST = "CART_LIST";
    private static final String CUSTOMER_ID = "Customer_id";

    String JSON_ITEM_LIST ;

    private static  final String LOGGEDINSTATUS = "isLoggedIn";
    private static final String USERNAME = "UserName";
    String username;

    //declaration
    public Context context;
    Gson gson;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<New_Item_Object> item_objects;

//    public String getJSon(){
  //      return sharedPreferences.getString(NAME_OF_CART_LIST,"");
    //}
    public Cart_Shared_Pref(Context context) {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
        item_objects = new ArrayList<>();
        JSON_ITEM_LIST =gson.toJson(item_objects);

        if(!sharedPreferences.contains(NAME_OF_CART_LIST)){
        editor.putString(NAME_OF_CART_LIST, JSON_ITEM_LIST);
        editor.commit();}
    }

    public ArrayList<New_Item_Object> getItem_objects(){
        String json_to_edit = sharedPreferences.getString(NAME_OF_CART_LIST,"");
        Type type = new TypeToken<ArrayList<New_Item_Object>>(){}.getType();
        ArrayList<New_Item_Object> List_edit = gson.fromJson(json_to_edit, type);
        return List_edit;
    }

    public void add_item(New_Item_Object object){

        ArrayList<New_Item_Object> List_edit = getItem_objects();
        if(!List_edit.contains(object)){
        List_edit.add(object);}
        String updated_string = gson.toJson(List_edit);
        editor.putString(NAME_OF_CART_LIST,updated_string);
        editor.commit();
    }

    public void Delete_Item(int Index){
        ArrayList<New_Item_Object> List_edit = getItem_objects();
        List_edit.remove(Index);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(NAME_OF_CART_LIST,Edited_JSON);
        editor.commit();
    }

    public void UpdateQuantity(int Index,int Value){

        ArrayList<New_Item_Object> List_edit = getItem_objects();
        List_edit.get(Index).setQuantity(Value);
        String Edited_JSON = gson.toJson(List_edit);
        editor.putString(NAME_OF_CART_LIST,Edited_JSON);
        editor.commit();
    }

    public void UpdateQuantityById(int Id,int Value){

        ArrayList<New_Item_Object> List_edit = getItem_objects();
        for(New_Item_Object product : List_edit) {
            if (product.getProductId() == Id) {
              //  Log.e("getting the id", ""+product.getProductId());
                product.setQuantity(Value);
                String Edited_JSON = gson.toJson(List_edit);
                editor.putString(NAME_OF_CART_LIST,Edited_JSON);
                editor.commit();
            }
        }
       /*         product.setQuantity(Value);
            }
        }



        String Edited_JSON = gson.toJson(List_edit);

        editor.putString(NAME_OF_CART_LIST,Edited_JSON);

        editor.commit();
*/
    }
    public void createLoginSession(String UserName){
        this.username = UserName;
        editor.putBoolean(LOGGEDINSTATUS,true).commit();
        editor.putString(USERNAME,username).commit();
    }

    public void logOutUser(){
        editor.clear();
        editor.commit();
    }

    public int getTotalPrice() {
        int Total = 0;
        ArrayList<New_Item_Object> List_edit = getItem_objects();
        for (New_Item_Object product : List_edit) {
            Total = Total +(product.getPrice()*product.getQuantity());        }
        return  Total;
    }
    public String getJsonArray(){
        return sharedPreferences.getString(NAME_OF_CART_LIST,"");
    }

    public void saveCustomerId(String id){
        editor.putString(CUSTOMER_ID,id);
        editor.commit();
    }

    public String getCustomerId(){
        return sharedPreferences.getString(CUSTOMER_ID,"");
    }

    public int getCartSize(){
        return getItem_objects().size();
    }
}