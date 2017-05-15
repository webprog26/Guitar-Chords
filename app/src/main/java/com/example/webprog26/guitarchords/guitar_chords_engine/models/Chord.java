package com.example.webprog26.guitarchords.guitar_chords_engine.models;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.ChordShape;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class Chord implements Serializable{

    public static final String NO_TYPE = "no_type";
    public static final String NO_PARAM = "no_param";

    private String mChordTitle;
    private String mChordSecondTitle;
    private String mChordType;
    private String mChordAlteration;
    private ArrayList<ChordShape> mChordShapes = new ArrayList<>();

    public Chord() {
    }

    public Chord(String chordTitle, String chordType) {
        this.mChordTitle = chordTitle;
        this.mChordType = chordType;
    }

    public Chord(String mChordTitle,
                 String mChordSecondTitle,
                 String mChordType,
                 String mChordAlteration,
                 ArrayList<ChordShape> mChordShapes) {
        this.mChordTitle = mChordTitle;
        this.mChordSecondTitle = mChordSecondTitle;
        this.mChordType = mChordType;
        this.mChordAlteration = mChordAlteration;
        this.mChordShapes = mChordShapes;
    }

    public String getChordTitle() {
        return mChordTitle;
    }

    public void setChordTitle(String chordTitle) {
        this.mChordTitle = chordTitle;
    }

    public String getChordSecondTitle() {
        return mChordSecondTitle;
    }

    public void setChordSecondTitle(String chordSecondTitle) {
        this.mChordSecondTitle = chordSecondTitle;
    }

    public String getChordType() {
        return mChordType;
    }

    public void setChordType(String chordType) {
        this.mChordType = chordType;
    }

    public String getChordAlteration() {
        return mChordAlteration;
    }

    public void setChordAlteration(String chordAlteration) {
        this.mChordAlteration = chordAlteration;
    }

    public ArrayList<ChordShape> getChordShapes() {
        return mChordShapes;
    }

    public void setChordShapes(ArrayList<ChordShape> mChordShapes) {
        this.mChordShapes = mChordShapes;
    }

    @Override
    public String toString() {
        return "Chord " + getChordTitle() + " with type " + getChordType() + " with alteration " + getChordAlteration();
    }
}
