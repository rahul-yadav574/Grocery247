package com.example.singh.Groz247;

import java.io.Serializable;

/**
 * Created by abhey singh on 18-08-2015.
 */
public class Product_Sample_Object implements Serializable {
    private String ProductName;
    private String CostPrize;
    private String SellPrize;
    private int Discount;
    private String ImageUrl;
    private int Quantity;
    private String ProductId;

    public Product_Sample_Object(String productName, String costPrize, String sellPrize,String productId, int discount,String imageUrl,int quantity) {
        super();

        this.ProductName = productName;
        this.CostPrize = costPrize;
        this.SellPrize = sellPrize;
        this.ProductId = productId;
        this.Discount = discount;
        this.ImageUrl = imageUrl;
        this.Quantity = quantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getCostPrize() {
        return CostPrize;
    }

    public String getSellPrize() {
        return SellPrize;
    }

    public int getDiscount() {
        return Discount;
    }

    public String getProductId(){return ProductId;}


    public int getQuantity(){return Quantity;}

    public void setQuantity(int quantity){
        this.Quantity = quantity;
    }






}
