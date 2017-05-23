package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.util.Log;

/**
 * Created by webpr on 23.05.2017.
 */

public class GuitarString {

    private static final String TAG = "GuitarString";

    private final String mTitle;
    private float startX;
    private float endX;
    private final boolean isMuted;

    private String mNote;


    public GuitarString(String title, boolean isMuted) {
        this.mTitle = title;
        this.isMuted = isMuted;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        this.mNote = note;
    }

    public void playNote(){
        if(!isMuted){
            String note = getNote();
            if(note != null){
                Log.i(TAG, "Play " + note);
            }
        }
    }
}
