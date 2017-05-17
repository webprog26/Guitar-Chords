package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

import java.util.ArrayList;

/**
 * Runs when data should be uploaded to {@link android.database.sqlite.SQLiteDatabase}
 */

public class UploadDataToDatabaseEvent {

    private final ArrayList<Chord> mChords;

    public UploadDataToDatabaseEvent(ArrayList<Chord> chords) {
        this.mChords = chords;
    }

    public ArrayList<Chord> getChords() {
        return mChords;
    }
}
