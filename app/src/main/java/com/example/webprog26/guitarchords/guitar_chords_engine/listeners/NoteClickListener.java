package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.webprog26.guitarchords.chord_shapes.note.Note;

/**
 * Created by webpr on 30.05.2017.
 */

public class NoteClickListener implements View.OnClickListener {

    private final Note note;

    public NoteClickListener(Note note) {
        this.note = note;
    }

    @Override
    public void onClick(View v) {
        Drawable noteImageDrawable;
        if(!note.isFingerIndexVisible()){
            noteImageDrawable = note.getNoteFingerIndexDrawable();
            note.setFingerIndexVisible(true);
        } else {
            noteImageDrawable = note.getNoteMainDrawable();
            note.setFingerIndexVisible(false);
        }

        if(noteImageDrawable != null){
            ((ImageView) v).setImageDrawable(noteImageDrawable);
        }
    }

    public Note getNote() {
        return note;
    }
}
