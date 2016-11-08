package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

import com.jonathanlieblich.somesortofcommerceyousay.ProductViewAdapter;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class Product {
    private String mName, mDescription, mType, mPrice;
    private int mQuantity, mId;

    public Product(String name, String description, String price) {
        mName = name;
        mDescription = description;
        mPrice = price;
    }

    //Product constructor while in shopping cart
    public Product(String name, int quantity, String price) {
        mName = name;
        mQuantity = quantity;
        mPrice = price;
    }

    public Product() {

    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPrice() {
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

    public void setPrice(String price) {
        mPrice = price;
    }

    public void setType(String type) {
        mType = type;
    }

    public int getId() {
        return mId;
    }

    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    public int getQuantity() {
        return mQuantity;
    }
}
