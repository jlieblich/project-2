package com.jonathanlieblich.somesortofcommerceyousay.ProductObjects;

/**
 * Created by jonlieblich on 10/31/16.
 */

public class ArtPiece extends Product {
    private String mArtist, mStyle;
    private boolean mUnique;

    public ArtPiece(String artist, String style) {
        mArtist = artist;
        mStyle = style;
    }
}
