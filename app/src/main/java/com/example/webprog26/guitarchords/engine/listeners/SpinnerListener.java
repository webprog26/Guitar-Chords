package com.example.webprog26.guitarchords.engine.listeners;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.webprog26.guitarchords.R;
import com.example.webprog26.guitarchords.engine.manager.ChordsManager;
import com.example.webprog26.guitarchords.engine.models.Chord;
import com.example.webprog26.guitarchords.interfaces.SpinnerReseter;

/**
 * Created by webprog26 on 09.05.2017.
 */

public class SpinnerListener implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SpinnerListener";

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

                if(!adapterView.getSelectedItem().toString().equalsIgnoreCase("--")){
                    chord.setChordType(adapterView.getSelectedItem().toString());
                } else {
                    chord.setChordType(Chord.NO_TYPE);
                }
               break;
            case R.id.sp_chords_params:
                if(!adapterView.getSelectedItem().toString().equalsIgnoreCase("--")){
                    chord.setChordParam(adapterView.getSelectedItem().toString());
                } else {
                    chord.setChordParam(Chord.NO_PARAM);
                }
                break;
       }

        if(chord.getChordTitle() != null && chord.getChordType() != null && chord.getChordParam() != null){

            if(chord.getChordParam().equalsIgnoreCase("b")){

                if(chord.getChordTitle().equalsIgnoreCase("C")){
                    Log.i(TAG, "There is no "  + chord.getChordTitle() + chord.getChordType() + " " + chord.getChordParam() + ".");
                    changeChordTitleAndResetParams("H");

                } else if(chord.getChordTitle().equalsIgnoreCase("F")){
                    Log.i(TAG, "There is no "  + chord.getChordTitle() + chord.getChordType() + " " + chord.getChordParam() + ".");
                    changeChordTitleAndResetParams("E");
                }
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
    private void changeChordTitleAndResetParams(String chordTitle){
        getChord().setChordTitle(chordTitle);
        getChord().setChordParam(Chord.NO_PARAM);
        getSpinnerReseter().resetChordsParamsSpinner();
    }
}
