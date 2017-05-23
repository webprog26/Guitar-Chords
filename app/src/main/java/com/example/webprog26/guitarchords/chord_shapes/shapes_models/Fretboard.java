package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

/**
 * Created by webpr on 23.05.2017.
 */

public class Fretboard {

    public static final String FIRST_STRING = "first_string";
    public static final String SECOND_STRING = "second_string";
    public static final String THIRD_STRING = "third_string";
    public static final String FOURTH_STRING = "fourth_string";
    public static final String FIFTH_STRING = "fifth_string";
    public static final String SIXTH_STRING = "sixth_string";

    private final GuitarString[] guitarStrings;

    public Fretboard(StringMutedHolder stringMutedHolder) {

        guitarStrings = new GuitarString[]{
                new GuitarString(SIXTH_STRING, stringMutedHolder.isSixthStringMuted()),
                new GuitarString(FIFTH_STRING, stringMutedHolder.isFifthStringMuted()),
                new GuitarString(FOURTH_STRING, stringMutedHolder.isFourthStringMuted()),
                new GuitarString(THIRD_STRING, stringMutedHolder.isThirdStringMuted()),
                new GuitarString(SECOND_STRING, stringMutedHolder.isSecondStringMuted()),
                new GuitarString(FIRST_STRING, stringMutedHolder.isFirstStringMuted())
        };
    }

    public GuitarString getGuitarString(int index){
        return guitarStrings[index];
    }

}
