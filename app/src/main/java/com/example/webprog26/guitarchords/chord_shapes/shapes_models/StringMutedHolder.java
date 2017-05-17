package com.example.webprog26.guitarchords.chord_shapes.shapes_models;

/**
 * Muted strings state holder
 */

public class StringMutedHolder {

    private boolean sixthStringMuted = false;
    private boolean fifthStringMuted = false;
    private boolean fourthStringMuted = false;
    private boolean thirdStringMuted = false;
    private boolean secondStringMuted = false;
    private boolean firstStringMuted = false;

    public boolean isSixthStringMuted() {
        return sixthStringMuted;
    }

    public void setSixthStringMuted(boolean sixthStringMuted) {
        this.sixthStringMuted = sixthStringMuted;
    }

    public boolean isFifthStringMuted() {
        return fifthStringMuted;
    }

    public void setFifthStringMuted(boolean fifthStringMuted) {
        this.fifthStringMuted = fifthStringMuted;
    }

    public boolean isFourthStringMuted() {
        return fourthStringMuted;
    }

    public void setFourthStringMuted(boolean fourthStringMuted) {
        this.fourthStringMuted = fourthStringMuted;
    }

    public boolean isThirdStringMuted() {
        return thirdStringMuted;
    }

    public void setThirdStringMuted(boolean thirdStringMuted) {
        this.thirdStringMuted = thirdStringMuted;
    }

    public boolean isSecondStringMuted() {
        return secondStringMuted;
    }

    public void setSecondStringMuted(boolean secondStringMuted) {
        this.secondStringMuted = secondStringMuted;
    }

    public boolean isFirstStringMuted() {
        return firstStringMuted;
    }

    public void setFirstStringMuted(boolean firstStringMuted) {
        this.firstStringMuted = firstStringMuted;
    }
}
