package com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string;

import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.note.Note;

/**
 * Created by webpr on 23.05.2017.
 */

public class GuitarString {

    private static final String TAG = "GuitarString";

    private final String mTitle;
    private float startX;
    private float endX;
    private float playableY;
    private final boolean isMuted;

    private Note mNote;


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

    public Note getNote() {
        return mNote;
    }

    public void setNote(Note note) {
        this.mNote = note;
    }

    public float getPlayableY() {
        return playableY;
    }

    public void setPlayableY(float playableY) {
        this.playableY = playableY;
    }

    public void playNote(){
        if(!isMuted){
            String noteTitle = getNote().getNoteTitle();
            if(noteTitle != null){
                Log.i(TAG, "Play " + noteTitle);
            }
        }
    }
}
