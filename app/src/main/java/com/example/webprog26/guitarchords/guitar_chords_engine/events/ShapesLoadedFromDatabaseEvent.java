package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

import java.util.ArrayList;

/**
 * Runs when chord shapes has been loaded from {@link android.database.sqlite.SQLiteDatabase}
 */

public class ShapesLoadedFromDatabaseEvent {

    private final ArrayList<ChordShape> mChordShapes;

    public ShapesLoadedFromDatabaseEvent(ArrayList<ChordShape> chordShapes) {
        this.mChordShapes = chordShapes;
    }

    public ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }
}
