package com.example.webprog26.guitarchords.engine.manager;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.webprog26.guitarchords.engine.commands.LoadChordFragmentCommand;
import com.example.webprog26.guitarchords.engine.models.Chord;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class ChordsManager {

    private static final String TAG = "ChordsManager";

    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;


    public ChordsManager(FragmentManager fragmentManager, int containerViewId) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
    }

    public void setFragmentWithListOfChordImages(Chord chord){
        Log.i(TAG, chord.toString());
        new LoadChordFragmentCommand(getFragmentManager(), getContainerViewId(), chord).execute();
    }

    private FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    private int getContainerViewId() {
        return mContainerViewId;
    }
}
