package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;
import com.example.webprog26.guitarchords.fragments.ChordFragment;

/**
 * This {@link Command} loads next {@link ChordFragment} every time when user changes chord's params with spinners
 */

public class LoadChordFragmentCommand implements Command {

    private static final String CHORDS_FRAGMENT_TAG = "chords_fragment_tag";

    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;
    private final Chord mChord;

    public LoadChordFragmentCommand(FragmentManager fragmentManager, int containerViewId, Chord chord) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
        this.mChord = chord;
    }

    @Override
    public void execute() {
        ChordFragment chordFragment = (ChordFragment) getFragmentManager().findFragmentByTag(CHORDS_FRAGMENT_TAG);

        if(chordFragment != null){
            getFragmentManager().beginTransaction().replace(getContainerViewId(),ChordFragment.newInstance(getChord()), CHORDS_FRAGMENT_TAG).commit();
        } else {
            getFragmentManager().beginTransaction().add(getContainerViewId(), ChordFragment.newInstance(getChord()), CHORDS_FRAGMENT_TAG).commit();
        }
    }

    private FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    private int getContainerViewId() {
        return mContainerViewId;
    }

    private Chord getChord() {
        return mChord;
    }
}
