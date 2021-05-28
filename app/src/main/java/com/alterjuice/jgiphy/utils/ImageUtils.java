package com.alterjuice.jgiphy.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ImageUtils {
    
    public static void loadGifIntoView(String gifImageUrl, ImageView intoImageView){
        loadGifIntoView(gifImageUrl, intoImageView, null);
    }
    public static void loadGifIntoView(String gifImageUrl, ImageView intoImageView, Drawable placeholder){
        Glide.with(intoImageView.getContext())
                .load(gifImageUrl)
                .placeholder(placeholder)
                .into(intoImageView);
    }
    public static void loadGifIntoView(String gifImageUrl, ImageView intoImageView, int placeholderResId){
        Glide.with(intoImageView.getContext())
                .load(gifImageUrl)
                .placeholder(placeholderResId)
                .into(intoImageView);
    }

    /**
     * Load the item's full-size image into our {@link ImageView}.
     */
    public static void loadImageIntoView(String imageUrl, ImageView intoImageView){
        loadImageIntoView(imageUrl, intoImageView, null);
    }
    public static void loadImageIntoView(String imageUrl, ImageView intoImageView, int placeholderResId) {
        Picasso.with(intoImageView.getContext())
                .load(imageUrl)
                .noFade()
                .placeholder(placeholderResId)
                .into(intoImageView);
    }
    public static void loadImageIntoView(String imageUrl, ImageView intoImageView, Drawable placeholder) {
        Picasso.with(intoImageView.getContext())
                .load(imageUrl)
                .noFade()
                .placeholder(placeholder)
                .into(intoImageView);
    }

}
