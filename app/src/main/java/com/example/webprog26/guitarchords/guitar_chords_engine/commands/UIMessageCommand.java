package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * This {@link Command} shows user messages via UIMessageHelper
 */

public class UIMessageCommand implements Command {

    private final UIMessageHelper mUiMessageHelper;
    private final Chord mChord;
    private final String mToChord;

    public UIMessageCommand(UIMessageHelper uiMessageHelper, Chord chord, String toChord) {
        this.mUiMessageHelper = uiMessageHelper;
        this.mChord = chord;
        this.mToChord = toChord;
    }

    @Override
    public void execute() {
        getUiMessageHelper().sendUiMessage(getFormattedMessage(getChord()), getToChord());
    }

    private UIMessageHelper getUiMessageHelper() {
        return mUiMessageHelper;
    }

    public Chord getChord() {
        return mChord;
    }

    private String getFormattedMessage(final Chord chord){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(chord.getChordTitle());

        if(!chord.getChordType().equalsIgnoreCase(Chord.NO_TYPE)){
            stringBuilder.append(chord.getChordType());
        }

        if(!chord.getChordAlteration().equalsIgnoreCase(Chord.NO_PARAM)){
            stringBuilder.append(chord.getChordAlteration());
        }

        return stringBuilder.toString();
    }

    private String getToChord() {
        return mToChord;
    }
}
