package com.example.webprog26.guitarchords.guitar_chords_engine.commands;

import com.example.webprog26.guitarchords.guitar_chords_engine.helpers.UIMessageHelper;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;

/**
 * This {@link Command} shows uiser message if wrong chord chosen
 */

public class WrongChordUiMessageCommand extends UIMessageCommand {

    public WrongChordUiMessageCommand(UIMessageHelper uiMessageHelper, Chord chord, String toChord) {
        super(uiMessageHelper, chord, toChord);
    }

    @Override
    public void execute() {
        getUiMessageHelper().sendUiWrongChordMessage(getFormattedWrongChordMessage(getChord()), getToChord());
    }

    private String getFormattedWrongChordMessage(final Chord chord){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(chord.getChordTitle());

        if(!chord.getChordType().equalsIgnoreCase(Chord.NO_TYPE)){
            stringBuilder.append(chord.getChordType());
        }

        if(!chord.getChordAlteration().equalsIgnoreCase(Chord.NO_ALTERATION)){
            stringBuilder.append(chord.getChordAlteration());
        }

        return stringBuilder.toString();
    }
}
