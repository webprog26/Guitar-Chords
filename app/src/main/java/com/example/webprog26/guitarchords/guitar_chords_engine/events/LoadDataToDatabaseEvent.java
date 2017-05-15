package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import java.util.ArrayList;

/**
 * Created by webpr on 15.05.2017.
 */

public class LoadDataToDatabaseEvent {

    private final ArrayList<Chord> mChords;

    public LoadDataToDatabaseEvent(ArrayList<Chord> chords) {
        this.mChords = chords;
    }

    public ArrayList<Chord> getChords() {
        return mChords;
    }
}
