package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

/**
 * Runs when user clicks on chord image
 */

public class ChordImageClickEvent {

    private final int clickedChordShapePosition;

    public ChordImageClickEvent(int clickedChordShapePosition) {
        this.clickedChordShapePosition = clickedChordShapePosition;
    }

    public int getClickedChordShapePosition() {
        return clickedChordShapePosition;
    }
}
