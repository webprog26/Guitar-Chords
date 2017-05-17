package com.example.webprog26.guitarchords.guitar_chords_engine.listeners;

import android.view.View;
import android.widget.AdapterView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.guitar_chords_engine.managers.ChordsManager;
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
    //Empty Chord instance to ctore parameters chosen vy user via spinners
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
                    chord.setChordAlteration(adapterView.getSelectedItem().toString());
                } else {
                    chord.setChordAlteration(Chord.NO_PARAM);
                }
                break;
       }

        if(chord.getChordTitle() != null && chord.getChordType() != null && chord.getChordAlteration() != null){

            String toChord = null;
            int toPosition = -1;

            //Handling C flat, F flat
            if(chord.getChordAlteration().equalsIgnoreCase(FLAT_CHORD_PARAM)){

                //C flat case
                if(chord.getChordTitle().equalsIgnoreCase(C_CHORD)){
                    toChord = H_CHORD;
                    toPosition = H_POSITION;
                }

                //F flat case
                if(chord.getChordTitle().equalsIgnoreCase(F_CHORD)){
                    toChord = E_CHORD;
                    toPosition = E_POSITION;
                }
            }

            //Handling H sharp, E sharp cases
            if(chord.getChordAlteration().equalsIgnoreCase(SHARP_CHORD_PARAM)){

                //H sharp case
                if(chord.getChordTitle().equalsIgnoreCase(H_CHORD)){
                    toChord = C_CHORD;
                    toPosition = C_POSITION;
                }

                //E sharp case
                if(chord.getChordTitle().equalsIgnoreCase(E_CHORD)){
                    toChord = F_CHORD;
                    toPosition = F_POSITION;
                }
            }

//            if(toChord != null && toPosition != -1){
            if(toChord != null){
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

    /**
     * In case of chosing unexisting chord resets spinners and changes user selection to right chord
     * @param chordTitle {@link String}
     * @param position int
     */
    private void changeChordTitleAndResetParams(final String chordTitle, final int position){
        getChord().setChordTitle(chordTitle);
        getChord().setChordAlteration(Chord.NO_PARAM);
        getSpinnerReseter().resetChordsParamsSpinner();
        getSpinnerReseter().setChordsTitleSpinnerPosition(position);
    }
}
