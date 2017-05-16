package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

/**
 * Runs when user clicks on chord image
 */

public class ChordImageClickEvent {

    private final ChordShape mChordShape;

    public ChordImageClickEvent(ChordShape mChordShape) {
        this.mChordShape = mChordShape;
    }

    public ChordShape getChordShape() {
        return mChordShape;
    }
}
