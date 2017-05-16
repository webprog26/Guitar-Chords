package com.example.webprog26.guitarchords.guitar_chords_engine.events;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Created by webpr on 16.05.2017.
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
