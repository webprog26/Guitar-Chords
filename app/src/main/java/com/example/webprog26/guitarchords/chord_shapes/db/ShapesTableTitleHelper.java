package com.example.webprog26.guitarchords.chord_shapes.db;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Creates chord shapes table title from chord parameters inputted by user
 */

public class ShapesTableTitleHelper {

    /**
     * Creates chord shapes table title from chord parameters inputted by user
     * @param chord {@link Chord}
     * @return String
     */
    public static String getChordShapesTableTitle(final Chord chord){
        return chord.getChordTitle().toLowerCase()
                + "_" + chord.getChordType().toLowerCase()
                + "_" + chord.getChordAlteration().toLowerCase();
    }
}
