package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

import android.graphics.Bitmap;
import android.graphics.Point;

/**
 * Created by webpr on 22.05.2017.
 */

public class PlayableNote {

    private final String mPlayableNoteTitle;
    private final int mFingerIndex;
    private final Point mPlayableNoteCoordinates;
    private final Bitmap mPlayableNoteFingerIndexBitmap;

    public PlayableNote(Note note, Bitmap playableNotefingerIndexBitmap) {
        this.mPlayableNoteTitle = note.getNoteTitle();
        this.mFingerIndex = note.getFingerIndex();
        this.mPlayableNoteCoordinates = note.getNoteCoordinates();
        this.mPlayableNoteFingerIndexBitmap = playableNotefingerIndexBitmap;
    }

    public Bitmap getPlayableNoteFingerIndexBitmap() {
        return mPlayableNoteFingerIndexBitmap;
    }

    public String getPlayableNoteTitle() {
        return mPlayableNoteTitle;
    }

    public int getFingerIndex() {
        return mFingerIndex;
    }

    public Point getPlayableNoteCoordinates() {
        return mPlayableNoteCoordinates;
    }
}
