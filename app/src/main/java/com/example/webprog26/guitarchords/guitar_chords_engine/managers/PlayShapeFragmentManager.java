package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.util.Log;

import com.example.webprog26.guitarchords.chord_shapes.fretboard.Fretboard;
import com.example.webprog26.guitarchords.chord_shapes.fretboard.guitar_string.GuitarString;
import com.example.webprog26.guitarchords.chord_shapes.note.Note;
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
                Note note = playableShape.getNotes().get(index);
                Log.i("GuitarString", note.getNoteTitle());
                guitarString.setNote(note);
                 if(index > 0){
                     index--;
                 }
            }
        }
    }

    public void setStringsCoordinates(float startX, float endX, int index){
        GuitarString guitarString = getGuitarString(index);
        guitarString.setStartX(startX);
        guitarString.setEndX(endX);
    }

    public void setStringPlayableY(float playableY, int index){
        GuitarString guitarString = getGuitarString(index);
        guitarString.setPlayableY(playableY);
    }

    private GuitarString getGuitarString(int index){
        return getFretboard().getGuitarString(index);
    }

    public void playNote(int guitarStringIndex){
        GuitarString guitarString = getGuitarString(guitarStringIndex);

        if(guitarString != null){

            Note noteToPlay = guitarString.getNote();

            if(noteToPlay != null){
                guitarString.playNote();
            }
        }
    }

    public PlayableShape getPlayableShape() {
        return mPlayableShape;
    }

    public Fretboard getFretboard() {
        return mFretboard;
    }
}