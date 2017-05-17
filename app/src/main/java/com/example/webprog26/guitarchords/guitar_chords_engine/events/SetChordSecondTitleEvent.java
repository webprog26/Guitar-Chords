package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Runs when chord second title should be set
 */

public class SetChordSecondTitleEvent {

    private final Chord mChord;

    public SetChordSecondTitleEvent(Chord chord) {
        this.mChord = chord;
    }

    public Chord getChord() {
        return mChord;
    }
}
