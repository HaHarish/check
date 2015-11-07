package com.nidarooms.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Harish on 03/11/15.
 */
public class FontStyleUtils {

    // Get Roboto Font Medium
    public static Typeface typefaceMedium;

    // Get Roboto Font Regular
    public static Typeface typefaceRegular;

    public FontStyleUtils(Context ctx){

        // Assign Roboto Font file from Asset folder
        typefaceMedium = Typeface.createFromAsset(ctx.getAssets(), "Roboto-Medium.ttf");
        typefaceRegular = Typeface.createFromAsset(ctx.getAssets(), "Roboto-Regular.ttf");
    }
}
