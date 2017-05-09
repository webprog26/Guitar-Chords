package com.example.webprog26.guitarchords.engine.events;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class ChordsImagesLoadedEvent {

    private final ArrayList<Bitmap> mChordsImages;

    public ChordsImagesLoadedEvent(ArrayList<Bitmap> chordsImages) {
        this.mChordsImages = chordsImages;
    }

    public ArrayList<Bitmap> getChordsImages() {
        return mChordsImages;
    }
}
