package com.example.webprog26.guitarchords.guitar_chords_engine.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class Chord implements Serializable{

    public static final String NO_TYPE = "no_type";
    public static final String NO_PARAM = "no_param";

    private String mChordTitle;
    private String mChordType;
    private String mChordParam;

    public Chord() {
    }

    public Chord(String chordTitle, String chordType) {
        this.mChordTitle = chordTitle;
        this.mChordType = chordType;
    }

    public Chord(String chordTitle, String chordType, String chordParam) {
        this.mChordTitle = chordTitle;
        this.mChordType = chordType;
        this.mChordParam = chordParam;
    }

    public String getChordTitle() {
        return mChordTitle;
    }

    public void setChordTitle(String chordTitle) {
        this.mChordTitle = chordTitle;
    }

    public String getChordType() {
        return mChordType;
    }

    public void setChordType(String chordType) {
        this.mChordType = chordType;
    }

    public String getChordParam() {
        return mChordParam;
    }

    public void setChordParam(String chordParam) {
        this.mChordParam = chordParam;
    }

    @Override
    public String toString() {
        return "Chord " + getChordTitle() + " with type " + getChordType() + " with param " + getChordParam();
    }
}
