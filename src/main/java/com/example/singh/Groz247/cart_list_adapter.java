package com.example.singh.Groz247;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rahul Yadav on 7/31/2015.;
 */
public class cart_list_adapter extends BaseAdapter {



    ArrayList<New_Item_Object> Products_list;
    private Context context;

    Cart_Shared_Pref newPref;
    Cart cart;

    public cart_list_adapter(Context context,ArrayList<New_Item_Object> products_list) {
        this.context=context;
        this.Products_list = products_list;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        newPref = new Cart_Shared_Pref(context);
        cart = new Cart();

        ViewHolder viewHolder = new ViewHolder();
        if(convertView == null){

            LayoutInflater cartInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = cartInflater.inflate(R.layout.cart_single_item,parent,false);
            viewHolder.quantity = (TextView) v.findViewById(R.id.quantity);
            viewHolder.removefromcart = (Button) v.findViewById(R.id.removefromcart);
            viewHolder.quantityincrement = (Button) v.findViewById(R.id.updatequantityincrement);
            viewHolder.quantitydecrement = (Button) v.findViewById(R.id.quantityupdatedecrement);
            viewHolder.itemname  = (TextView) v.findViewById(R.id.cartitemname);
            viewHolder.itemprice = (TextView) v.findViewById(R.id.price);
            viewHolder.itemTotalPrice = (TextView) v.findViewById(R.id.itemTotalPrice);

            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();}
            //reference to all the views in the cart item
           //
            //setting all the names, quantity, Price for the item of the cart ....by taking it from Shared Preference Json
            viewHolder.itemname.setText("" + Products_list.get(position).Name);
            viewHolder.quantity.setText("" + Products_list.get(position).Quantity);
            viewHolder.itemprice.setText("₹"+ Products_list.get(position).Price);
            viewHolder.itemTotalPrice.setText("₹"+ Integer.parseInt(Products_list.get(position).Price)*Products_list.get(position).Quantity);

        viewHolder.removefromcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPref.Delete_Item(position);
                Products_list.remove(position);
                cart_list_adapter.this.notifyDataSetChanged();
            }
        });

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.quantityincrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Value = Integer.parseInt(finalViewHolder.quantity.getText().toString());
                Value = Value +1;
                newPref.UpdateQuantity(position,Value);
                finalViewHolder.quantity.setText(""+Value);
                Products_list.get(position).setQuantity(Value);

                cart_list_adapter.this.notifyDataSetChanged();
            }
        });

        viewHolder.quantitydecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Value = Integer.parseInt(finalViewHolder.quantity.getText().toString());
                if(Value>1){
                Value = Value - 1;
                newPref.UpdateQuantity(position,Value);
                finalViewHolder.quantity.setText(""+Value);
                Products_list.get(position).setQuantity(Value);
                cart_list_adapter.this.notifyDataSetChanged();}
                else{
                    Toast.makeText(context,"Use Delete Button",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return Products_list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        public TextView quantity;
        public TextView itemprice;
        public TextView itemname;
        public TextView itemTotalPrice;
        public Button quantityincrement;
        public Button quantitydecrement;
        public Button removefromcart;
    }
}