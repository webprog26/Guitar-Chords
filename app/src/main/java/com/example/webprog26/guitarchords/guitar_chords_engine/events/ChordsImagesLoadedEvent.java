package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Runs when chord images has been loaded from specific directory in assets
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
