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

    public void newWinningCombination() {
        this.winCombination = CombinationManager.newCombination();
        this.winReintegro = CombinationManager.newReintegro();
        this.winComplementalNum = CombinationManager.newNumber(winCombination);
    }

    public Category checkWin(Boleto playerTicket) {
        int contNumbers = 0;
        boolean reintegro = false;
        boolean complementalNum = false;
        for (int i = 0; i < playerTicket.getNumeros().length; i++) {
            if (CombinationManager.contains(winCombination,playerTicket.getNumeros()[i])) {
                contNumbers++;
            }
            if(winComplementalNum == playerTicket.getNumeros()[i]) {
                complementalNum = true;
            }
        }
        if(winReintegro == playerTicket.getReintegro()) {
            reintegro=true;
        }

        switch(contNumbers) {
            case 1:
            case 2:
                return Category.NONE;
            case 3:
                return Category.CAT_5;
            case 4:
                return Category.CAT_4;
            case 5:
                if (complementalNum) {
                    return Category.CAT_2;
                }
                return Category.CAT_3;
            case 6:
                if (reintegro){
                    return Category.CAT_ESP;
                }
                return Category.CAT_1;
        }
        return Category.NONE;
    }
}
