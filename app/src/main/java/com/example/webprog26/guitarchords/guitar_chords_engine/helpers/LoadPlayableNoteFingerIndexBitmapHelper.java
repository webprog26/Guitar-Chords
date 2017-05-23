package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by webpr on 22.05.2017.
 */

public class LoadPlayableNoteFingerIndexBitmapHelper {

    public static Bitmap getPlayableNoteFingerIndexBitmap(Context context, int fingerIndex){
        return BitmapFactory.decodeResource(context.getResources(), FingerIndexDrawableIDHelper.getFingerIndexDrawableId(fingerIndex));
    }
}
