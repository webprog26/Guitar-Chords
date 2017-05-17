package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * This {@link Command} shows user messages via UIMessageHelper
 */

public abstract class UIMessageCommand implements Command {

    private final UIMessageHelper mUiMessageHelper;
    private final Chord mChord;
    private final String mToChord;

    UIMessageCommand(UIMessageHelper uiMessageHelper, Chord chord, String toChord) {
        this.mUiMessageHelper = uiMessageHelper;
        this.mChord = chord;
        this.mToChord = toChord;
    }

    UIMessageHelper getUiMessageHelper() {
        return mUiMessageHelper;
    }

    protected Chord getChord() {
        return mChord;
    }

    String getToChord() {
        return mToChord;
    }
}
