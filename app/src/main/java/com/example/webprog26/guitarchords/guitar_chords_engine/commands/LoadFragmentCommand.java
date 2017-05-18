package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;
import com.example.webprog26.guitarchords.fragments.ChordFragment;

/**
 * This {@link Command} loads next {@link ChordFragment} every time when user changes chord's params with layout_spinners
 */

public abstract class LoadFragmentCommand implements Command {

    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;

    public LoadFragmentCommand(FragmentManager fragmentManager, int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
    }

    protected FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    protected int getContainerViewId() {
        return mContainerViewId;
    }
}
