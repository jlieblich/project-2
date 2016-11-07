package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class Product {
    private String mName, mDescription, mType;
    private double mPrice;
    private int mQuantity, mId;

    public Product(String name, String description, double price) {
        mName = name;
        mDescription = description;
        mPrice = price;
    }

    //Product constructor while in shopping cart
    public Product(String name, int quantity, double price) {
        mName = name;
        mQuantity = quantity;
        mPrice = price;
    }

    public Product() {

    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public double getPrice() {
        return mPrice;
    }

    public String getType() {
        return mType;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDescription(String desc) {
        mDescription = desc;
    }

    public void setPrice(double price) {
        mPrice = price;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getId() {
        return mId;
    }
}
