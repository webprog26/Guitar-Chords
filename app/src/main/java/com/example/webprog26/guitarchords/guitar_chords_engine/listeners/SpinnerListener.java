package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.manager.ChordsManager;
import com.example.webprog26.guitarchords.guitar_chords_engine.models.Chord;
import com.example.webprog26.guitarchords.interfaces.SpinnerReseter;

/**
 * Listener for user interaction with spinners
 */

public class SpinnerListener implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SpinnerListener";

    private static final String EMPTY_POSITION = "--";

    private static final String FLAT_CHORD_PARAM = "b";
    private static final String SHARP_CHORD_PARAM = "#";

    private static final String C_CHORD = "C";
    private static final String F_CHORD = "F";

    private static final String H_CHORD = "H";
    private static final String E_CHORD = "E";


    private static final int C_POSITION = 0;
    private static final int F_POSITION = 3;
    private static final int H_POSITION = 7;
    private static final int E_POSITION = 2;


    private final ChordsManager mChordsManager;
    private final SpinnerReseter mSpinnerReseter;
    private Chord mChord = new Chord();

    public SpinnerListener(ChordsManager chordsManager, SpinnerReseter spinnerReseter) {
        this.mChordsManager = chordsManager;
        this.mSpinnerReseter = spinnerReseter;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Chord chord = getChord();


        switch (adapterView.getId()){
           case R.id.sp_chords_titles:
               chord.setChordTitle(adapterView.getSelectedItem().toString());
               break;
           case R.id.sp_chords_types:

                if(!adapterView.getSelectedItem().toString().equalsIgnoreCase(EMPTY_POSITION)){
                    chord.setChordType(adapterView.getSelectedItem().toString());
                } else {
                    chord.setChordType(Chord.NO_TYPE);
                }
               break;
            case R.id.sp_chords_params:
                if(!adapterView.getSelectedItem().toString().equalsIgnoreCase(EMPTY_POSITION)){
                    chord.setChordParam(adapterView.getSelectedItem().toString());
                } else {
                    chord.setChordParam(Chord.NO_PARAM);
                }
                break;
       }

        if(chord.getChordTitle() != null && chord.getChordType() != null && chord.getChordParam() != null){

            String toChord = null;
            int toPosition = -1;

            if(chord.getChordParam().equalsIgnoreCase(FLAT_CHORD_PARAM)){

                if(chord.getChordTitle().equalsIgnoreCase(C_CHORD)){
                    toChord = H_CHORD;
                    toPosition = H_POSITION;
                }

                if(chord.getChordTitle().equalsIgnoreCase(F_CHORD)){
                    toChord = E_CHORD;
                    toPosition = E_POSITION;
                }
            }

            if(chord.getChordParam().equalsIgnoreCase(SHARP_CHORD_PARAM)){

                if(chord.getChordTitle().equalsIgnoreCase(H_CHORD)){
                    toChord = C_CHORD;
                    toPosition = C_POSITION;
                }

                if(chord.getChordTitle().equalsIgnoreCase(E_CHORD)){
                    toChord = F_CHORD;
                    toPosition = F_POSITION;
                }
            }

            if(toPosition != -1 && toChord != null){
                getChordsManager().sendUiMessage(chord, toChord);
                changeChordTitleAndResetParams(toChord, toPosition);
            }

            getChordsManager().setFragmentWithListOfChordImages(getChord());
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //
    }


    private Chord getChord() {
        return mChord;
    }

    private ChordsManager getChordsManager() {
        return mChordsManager;
    }

    private SpinnerReseter getSpinnerReseter() {
        return mSpinnerReseter;
    }

    private void changeChordTitleAndResetParams(final String chordTitle, final int position){
        getChord().setChordTitle(chordTitle);
        getChord().setChordParam(Chord.NO_PARAM);
        getSpinnerReseter().resetChordsParamsSpinner();
        getSpinnerReseter().setChordsTitleSpinnerPosition(position);
    }
}
