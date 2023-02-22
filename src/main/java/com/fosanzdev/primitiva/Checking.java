package com.fosanzdev.primitiva;

public class Checking {
    private int[] winCombination;
    private int winReintegro;
    private int winComplementalNum;

    public Checking() {
        this.winCombination = CombinationManager.newCombination();
        this.winReintegro = CombinationManager.newReintegro();
        this.winComplementalNum = CombinationManager.newNumber(winCombination);
    }
}
