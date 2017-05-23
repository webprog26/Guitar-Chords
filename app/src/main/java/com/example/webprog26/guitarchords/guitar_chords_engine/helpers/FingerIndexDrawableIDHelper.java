package com.example.webprog26.guitarchords.guitar_chords_engine.helpers;

import com.example.webprog26.guitarchords.R;

/**
 * Created by webpr on 22.05.2017.
 */

public class FingerIndexDrawableIDHelper {

    private static final int FIRST_FINGER_INDEX = R.drawable.first_finger;
    private static final int SECOND_FINGER_INDEX = R.drawable.second_finger;
    private static final int THIRD_FINGER_INDEX = R.drawable.third_finger;
    private static final int FOURTH_FINGER_INDEX = R.drawable.fourth_finger;

    public static int getFingerIndexDrawableId(int fingerIndex){
        switch (fingerIndex){
            case 1:
                return FIRST_FINGER_INDEX;

            case 2:
                return SECOND_FINGER_INDEX;

            case 3:
                return THIRD_FINGER_INDEX;

            case 4:
                return FOURTH_FINGER_INDEX;

            default:
                return -1;
        }
    }
}
