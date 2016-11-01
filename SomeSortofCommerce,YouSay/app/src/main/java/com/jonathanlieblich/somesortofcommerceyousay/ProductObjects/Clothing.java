package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class Clothing extends Product {
    private String mSize, mBrand, mColor;

    public Clothing(String size, String brand, String color) {
        mSize = size;
        mBrand = brand;
        mColor = color;
    }
}
