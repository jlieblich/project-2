package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class Food extends Product {
    private String mFoodGroup, mExpirationDate;
    private boolean mVegetarian;
    private int mCalories;

    public Food(String foodGroup, String expirationDate, int calories) {
        mVegetarian = false;
        mFoodGroup = foodGroup;
        mExpirationDate = expirationDate;
        mCalories = calories;
    }
}
