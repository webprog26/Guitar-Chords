package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.example.webprog26.guitarchords.fragments.dialogs.ReferenceDialog;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.LoadChordFragmentCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.WrongChordUiMessageCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Chords manager
 */

public class ChordsManager extends GuitarChordsManager{

    private static final String TAG = "ChordsManager";


    private Chord mCurrentChord;

    public ChordsManager(FragmentManager fragmentManager,
                         int containerViewId,
                         Context context) {
        super(fragmentManager, containerViewId, context);
    }

    public void setFragmentWithListOfChordImages(Chord chord){
        Log.i(TAG, chord.toString());
        setCurrentChord(chord);
        new LoadChordFragmentCommand(getFragmentManager(), getContainerViewId(), chord).execute();
    }

    public void sendUiMessage(final Chord chord, final String toChord){
        new WrongChordUiMessageCommand(getUiMessageHelper(), chord, toChord).execute();
    }

    public void showReference(String referenceText){
        ReferenceDialog referenceDialog = ReferenceDialog.newInstance(referenceText);
        referenceDialog.show(getFragmentManager(), null);
    }


    public void setCurrentChord(Chord mCurrentChord) {
        this.mCurrentChord = mCurrentChord;
    }

    public Chord getCurrentChord() {
        return mCurrentChord;
    }

    public String getChordSecondTitle() {
        return getCurrentChord().getChordSecondTitle();
    }

    public void setChordSecondTitle(String chordSecondTitle) {
        getCurrentChord().setChordSecondTitle(chordSecondTitle);
    }
}
