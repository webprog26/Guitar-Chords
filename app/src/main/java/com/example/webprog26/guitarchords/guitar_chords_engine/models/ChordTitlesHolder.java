package com.example.webprog26.guitarchords.guitar_chords_engine.models;

import java.io.Serializable;

/**
 * Created by webpr on 18.05.2017.
 */

public class ChordTitlesHolder implements Serializable{

    private final String mChordTitle;
    private final String mChordSecondTitle;

    public ChordTitlesHolder(final Chord chord) {
        this.mChordTitle = chord.getChordTitle();
        this.mChordSecondTitle = chord.getChordSecondTitle();
    }

    public String getChordTitle() {
        return mChordTitle;
    }

    public String getChordSecondTitle() {
        return mChordSecondTitle;
    }
}
