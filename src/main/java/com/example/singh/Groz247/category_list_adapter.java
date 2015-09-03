package com.example.singh.Groz247;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rahul Yadav on 7/23/2015.
 */
public class category_list_adapter extends BaseAdapter{

    private Context context;
    private final String[] categorynameList;
    private final int[] categorypicList;
    public category_list_adapter(Context context,String[] categorynameList,int[] categorypicList) {
        this.context = context;
        this.categorynameList=categorynameList;
        this.categorypicList = categorypicList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater categoryInflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rootView = categoryInflater.inflate(R.layout.category_custom_view,parent,false);
            ImageView categorypic = (ImageView) rootView.findViewById(R.id.categorypic);
            TextView categoryname = (TextView) rootView.findViewById(R.id.categoryname);
            categoryname.setText(categorynameList[position]);
            categorypic.setImageResource(categorypicList[position]);
        return rootView;
    }
    @Override
    public int getCount() {
        return categorynameList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}