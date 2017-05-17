package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Runs when chord should be filled with additional data
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
