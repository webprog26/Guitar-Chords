package com.example.webprog26.guitarchords.guitar_chords_engine.manager;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.example.webprog26.guitarchords.fragments.dialogs.ReferenceDialog;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.LoadChordFragmentCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.commands.UIMessageCommand;
import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Chords manager
 */

public class ChordsManager {

    private static final String TAG = "ChordsManager";

    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;

    private final UIMessageHelper mUiMessageHelper;

    private Chord mCurrentChord;
    private final Context mContext;

    public ChordsManager(FragmentManager fragmentManager, int containerViewId, Context context) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
        this.mContext = context;
        this.mUiMessageHelper = new UIMessageHelper(this);
    }

    public void setFragmentWithListOfChordImages(Chord chord){
        Log.i(TAG, chord.toString());
        setCurrentChord(chord);
        new LoadChordFragmentCommand(getFragmentManager(), getContainerViewId(), chord).execute();
    }

    public void sendUiMessage(final Chord chord, final String toChord){
        new UIMessageCommand(getUiMessageHelper(), chord, toChord).execute();
    }

    public void showReference(String chordTitle){
        ReferenceDialog referenceDialog = ReferenceDialog.newInstance(chordTitle);
        referenceDialog.show(getFragmentManager(), null);
    }

    private FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public int getContainerViewId() {
        return mContainerViewId;
    }

    public Context getContext() {
        return mContext;
    }

    public Chord getCurrentChord() {
        return mCurrentChord;
    }

    private void setCurrentChord(Chord currentChord) {
        this.mCurrentChord = currentChord;
    }

    private UIMessageHelper getUiMessageHelper() {
        return mUiMessageHelper;
    }

    public String getChordSecondTitle() {
        return getCurrentChord().getChordSecondTitle();
    }

    public void setChordSecondTitle(String chordSecondTitle) {
        getCurrentChord().setChordSecondTitle(chordSecondTitle);
    }
}
