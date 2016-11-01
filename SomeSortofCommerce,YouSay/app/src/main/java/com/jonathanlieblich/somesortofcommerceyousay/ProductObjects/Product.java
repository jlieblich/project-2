package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class Product {
    private String mName, mDescription;
    private double mPrice;

    public Product(String name, String description, double price) {
        mName = name;
        mDescription = description;
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
}
