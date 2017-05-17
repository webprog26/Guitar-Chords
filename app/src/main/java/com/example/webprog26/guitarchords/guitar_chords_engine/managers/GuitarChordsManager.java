package com.example.webprog26.guitarchords.guitar_chords_engine.managers;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * Created by webpr on 17.05.2017.
 */

public abstract class GuitarChordsManager {

    private final FragmentManager mFragmentManager;
    private final int mContainerViewId;

    private final UIMessageHelper mUiMessageHelper;

    private final Context mContext;

    public GuitarChordsManager(FragmentManager fragmentManager, int containerViewId, Context context) {
        this.mFragmentManager = fragmentManager;
        this.mContainerViewId = containerViewId;
        this.mContext = context;
        this.mUiMessageHelper = new UIMessageHelper(this);
    }

    public abstract void showReference(String referenceText);

    public FragmentManager getFragmentManager() {
        return mFragmentManager;
    }

    public int getContainerViewId() {
        return mContainerViewId;
    }

    public UIMessageHelper getUiMessageHelper() {
        return mUiMessageHelper;
    }

    public Context getContext() {
        return mContext;
    }
}
