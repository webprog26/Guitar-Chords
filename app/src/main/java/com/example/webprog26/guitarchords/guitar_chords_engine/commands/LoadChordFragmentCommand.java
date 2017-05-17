package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.fragments.ChordFragment;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Created by webpr on 17.05.2017.
 */

public class LoadChordFragmentCommand extends LoadFragmentCommand {

    private static final String CHORDS_FRAGMENT_TAG = "chords_fragment_tag";

    private final Chord mChord;


    public LoadChordFragmentCommand(FragmentManager fragmentManager, int containerViewId, Chord chord) {
        super(fragmentManager, containerViewId);
        this.mChord = chord;
    }

    @Override
    public void execute() {
        ChordFragment chordFragment = (ChordFragment) getFragmentManager().findFragmentByTag(CHORDS_FRAGMENT_TAG);
        FragmentManager fragmentManager = getFragmentManager();

        if(chordFragment != null){
            fragmentManager.beginTransaction().replace(getContainerViewId(),ChordFragment.newInstance(getChord()), CHORDS_FRAGMENT_TAG).commit();
        } else {
            fragmentManager.beginTransaction().add(getContainerViewId(), ChordFragment.newInstance(getChord()), CHORDS_FRAGMENT_TAG).commit();
        }
    }

    private Chord getChord() {
        return mChord;
    }
}
