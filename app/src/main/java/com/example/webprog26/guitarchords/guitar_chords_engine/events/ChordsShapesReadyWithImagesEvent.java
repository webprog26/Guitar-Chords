package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import android.graphics.Bitmap;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

import java.util.ArrayList;

/**
 * Runs when chord images has been loaded from specific directory in assets
 */

public class ChordsShapesReadyWithImagesEvent {

    private final ArrayList<ChordShape> mChordShapes;

    public ChordsShapesReadyWithImagesEvent(ArrayList<ChordShape> chordShapes) {
        this.mChordShapes = chordShapes;
    }

    public ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }
}
