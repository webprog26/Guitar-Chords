package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import java.util.ArrayList;

/**
 * Created by webpr on 16.05.2017.
 */

public class LoadShapesFromDatabaseEvent {

    private final Chord mChord;

    public LoadShapesFromDatabaseEvent(Chord chord) {
        this.mChord = chord;
    }

    public Chord getChord() {
        return mChord;
    }
}
