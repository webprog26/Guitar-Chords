package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import java.util.ArrayList;

/**
 * Runs when data has been transformed to POJOs
 */

public class DataHasBeenTransformedToPOJOsEvent {

    private final ArrayList<Chord> mChords;

    public DataHasBeenTransformedToPOJOsEvent(ArrayList<Chord> chords) {
        this.mChords = chords;
    }

    public ArrayList<Chord> getChords() {
        return mChords;
    }
}
