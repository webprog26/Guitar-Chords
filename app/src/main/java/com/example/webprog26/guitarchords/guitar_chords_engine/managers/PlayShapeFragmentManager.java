package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.shapes_models.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.shapes_models.PlayableShape;

/**
 * Created by webpr on 23.05.2017.
 */

public class PlayShapeFragmentManager {

    private final PlayableShape mPlayableShape;
    private final Fretboard mFretboard;



    public PlayShapeFragmentManager(PlayableShape playableShape) {
        this.mPlayableShape = playableShape;
        this.mFretboard = new Fretboard(playableShape.getStringMutedHolder());

        int index = (playableShape.getNotes().size() - 1);
        Log.i("GuitarString", index + " index");

        for(int i = 0; i < 6; i++){
            GuitarString guitarString = getFretboard().getGuitarString(i);
            if(!guitarString.isMuted()){
                String noteTitle = playableShape.getNotes().get(index).getNoteTitle();
                Log.i("GuitarString", noteTitle);
                guitarString.setNote(noteTitle);
                 if(index > 0){
                     index--;
                 }
            }
        }
    }

    public void setStringsCoordinates(float startX, float endX, int index){
        GuitarString guitarString = getFretboard().getGuitarString(index);
        guitarString.setStartX(startX);
        guitarString.setEndX(endX);
    }

    public PlayableShape getPlayableShape() {
        return mPlayableShape;
    }

    public Fretboard getFretboard() {
        return mFretboard;
    }
}
