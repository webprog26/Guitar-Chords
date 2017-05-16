package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

import java.util.ArrayList;

/**
 * Created by webpr on 16.05.2017.
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
