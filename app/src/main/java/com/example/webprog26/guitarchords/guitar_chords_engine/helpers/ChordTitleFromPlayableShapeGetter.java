package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import android.content.Context;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.ChordTitlesHolder;

/**
 * Created by webpr on 18.05.2017.
 */

public class ChordTitleFromPlayableShapeGetter {

    private final Context mContext;

    public ChordTitleFromPlayableShapeGetter(Context context) {
        this.mContext = context;
    }

    public String getChordTitleFromPlayableShape(ChordTitlesHolder chordTitlesHolder){
        if(chordTitlesHolder.getChordSecondTitle() == null){
            return getContext().getString(R.string.chord_with_one_title, chordTitlesHolder.getChordTitle());
        }
        return getContext().getString(R.string.chord_with_two_titles, chordTitlesHolder.getChordTitle(), chordTitlesHolder.getChordSecondTitle());
    }

    public Context getContext() {
        return mContext;
    }
}
