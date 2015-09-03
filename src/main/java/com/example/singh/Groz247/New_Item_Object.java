package com.example.singh.Groz247;

/**
 * Created by mahesh singh yadav on 11-08-2015.
 */
public class New_Item_Object {

    String Name;
    String Price;
    int Quantity;
    String ProductId;
    String ImageUrl;
    public New_Item_Object(String name, String price, String productId,int quantity,String imageUrl) {
        this.Name = name;
        this.Price = price;
        this.Quantity = quantity;
        this.ProductId = productId;
        this.ImageUrl = imageUrl;
    }
    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public int getProductId(){
        return Integer.parseInt(ProductId);
    }
    public String getImageUrl(){
        return ImageUrl;
    }

    public int getPrice() {
        return Integer.parseInt(Price);
    }

    public int getQuantity(){
        return Quantity;
    }
}
