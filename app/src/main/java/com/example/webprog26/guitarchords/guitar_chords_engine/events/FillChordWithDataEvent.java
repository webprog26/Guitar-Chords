package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Created by webpr on 16.05.2017.
 */

public class FillChordWithDataEvent {

    private final Chord mChord;

    public FillChordWithDataEvent(Chord chord) {
        this.mChord = chord;
    }

    public Chord getChord() {
        return mChord;
    }
}
