package com.example.singh.Groz247;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.util.List;
/**
 * Created by Rahul Yadav on 7/24/2015.
 */
public class vertical_product_adapter extends BaseAdapter {

    private Context context;
    private List<Product_Sample_Object> productList;
    Cart_Shared_Pref cart_shared_pref;

    private ImageLoaderConfiguration config;
    private File cacheDir;
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();




    public vertical_product_adapter(Context context, List<Product_Sample_Object> productList) {
        this.context = context;
        this.productList = productList;
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));



        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"JunkFolder");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .showImageOnFail(R.drawable.abc_textfield_activated_mtrl_alpha)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        config = new ImageLoaderConfiguration.Builder(context)
                .memoryCache(new WeakMemoryCache())
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(5)
                .defaultDisplayImageOptions(options)
                .build();


        imageLoader.init(config);


    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        cart_shared_pref = new Cart_Shared_Pref(context);






        final Product_Sample_Object oneItem = productList.get(position);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater minflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = minflater.inflate(R.layout.custom_product_vertical_layout,parent,false);
        //id for the views
            viewHolder.productname = (TextView) v.findViewById(R.id.productname);
            viewHolder.productimage = (ImageView) v.findViewById(R.id.productimage);
            viewHolder.costprice = (TextView) v.findViewById(R.id.costprize);
            viewHolder.sellprice = (TextView) v.findViewById(R.id.sellprize);
            viewHolder.qincrement = (Button) v.findViewById(R.id.quantityincrement);
            viewHolder.qdecrement = (Button) v.findViewById(R.id.quantitydecrement);
            viewHolder.quantityset = (TextView) v.findViewById(R.id.quantityset);
            viewHolder.productId = (TextView) v.findViewById(R.id.productId);
            viewHolder.imageProgressBar = (ProgressBar) v.findViewById(R.id.imageProgressBar);


            v.setTag(viewHolder);}
        else {
            viewHolder = (ViewHolder) v.getTag();
           }
            //setting the names and image resource of the products list
            viewHolder.productname.setText(oneItem.getProductName());
            //viewHolder.productimage.setImageResource(R.drawable.backgrnd);
            viewHolder.sellprice.setText("₹"+oneItem.getCostPrize());
            viewHolder.costprice.setText("₹"+ oneItem.getSellPrize());
            //to make it strike through ...adding paint  flags
            viewHolder.costprice.setPaintFlags(viewHolder.costprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.quantityset.setText(""+oneItem.getQuantity());
            viewHolder.productId.setText(""+oneItem.getProductId());

        final ViewHolder finalViewHolder1 = viewHolder;
        imageLoader.displayImage(Constants.baseImageUrl+oneItem.getImageUrl(),viewHolder.productimage, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

                finalViewHolder1.imageProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                finalViewHolder1.imageProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
            }
        });




               //Toast.makeText(context,""+getCount(),Toast.LENGTH_SHORT).show();
        //listener for the button click to add item to the cart
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.qincrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (Integer.parseInt(finalViewHolder.quantityset.getText().toString())==0) {
                    cart_shared_pref.add_item(new New_Item_Object(oneItem.getProductName(), oneItem.getCostPrize(),oneItem.getProductId(),1,oneItem.getImageUrl()));
                    finalViewHolder.quantityset.setText(""+((Integer.parseInt(finalViewHolder.quantityset.getText().toString()))+1));
                   Toast.makeText(context,"Item Added To Cart",Toast.LENGTH_SHORT).show();

                }
                else{
                    int newQuantity = ((Integer.parseInt(finalViewHolder.quantityset.getText().toString()))+1);
                    finalViewHolder.quantityset.setText(""+newQuantity);
                    cart_shared_pref.UpdateQuantityById(Integer.parseInt(oneItem.getProductId()),newQuantity);
                    oneItem.setQuantity(newQuantity);
                }
            }
        });

        viewHolder.qdecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(finalViewHolder.quantityset.getText().toString())>1){
                    int newQuantity = ((Integer.parseInt(finalViewHolder.quantityset.getText().toString()))-1);
                    finalViewHolder.quantityset.setText(""+newQuantity);
                    oneItem.setQuantity(newQuantity);
                    cart_shared_pref.UpdateQuantityById(Integer.parseInt(oneItem.getProductId()),newQuantity);
                }
                else{
                    Toast.makeText(context,"Delete Item from Cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }
    @Override
    public Object getItem(int position) {
        if (productList != null)
            return productList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        if(productList!=null){
            return productList.size();}
        return 0;
    }

    public void changeTheList(List<Product_Sample_Object> productList) {
        this.productList = productList;
    }

    public static class ViewHolder{
        public TextView quantityset;
        public TextView costprice;
        public TextView productname;
        public Button qincrement;
        public Button qdecrement;
        public ImageView productimage;
        public TextView sellprice;
        public TextView productId;
        public ProgressBar imageProgressBar;

    }
}