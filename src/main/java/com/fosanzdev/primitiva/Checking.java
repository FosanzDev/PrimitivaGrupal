package com.fosanzdev.primitiva;

public class Checking {
    //Atributes:

    //A random combination which determinate if the user's numbers are equal to some of this numbers ( to win min 3 nums)
    private int[] winCombination;

    //Extra numbers between 0 and 9
    private int winReintegro;

    // Other extra number which is not equal to the ticket's numbers
    private int winComplementalNum;

    //New constructor without parameters which assigns the wining combination with random numbers
    public Checking() {
        this.winCombination = CombinationManager.newCombination();
        this.winReintegro = CombinationManager.newReintegro();
        this.winComplementalNum = CombinationManager.newNumber(winCombination);
    }

    /**
     * This method is done to create a new winning combination
     */
    public void newWinningCombination() {
        this.winCombination = CombinationManager.newCombination();
        this.winReintegro = CombinationManager.newReintegro();
        this.winComplementalNum = CombinationManager.newNumber(winCombination);
    }

    /**
     * - This method checks if the ticket's player is equal to the numbers of the winning combination.
     * <br>
     * - It depends on the numbers which you guess, you've the next prices:
     * <br>
     * · 3 numbers --> Prize of category 5
     * <br>
     * · 4 numbers --> Prize of category 4
     * <br>
     * · 5 numbers with complementalNum --> Prize of category 2
     * <br>
     * · 5 numbers without complementalNum --> Prize of category 3
     * <br>
     * · 6 numbers without reintegro --> Prize of category 1
     * <br>
     * · 6 numbers with reintegro --> Prize of special category
     * <br>
     * - These prices are kept in an enum class called Category (all the prices of this game)
     * <br>
     * @param playerTicket
     * @return The price which is the category what the user wins
     */
    public Category checkWin(Boleto playerTicket) {
        //Variables:
        //Count the numbers which the user guess
        int contNumbers = 0;
        //If guess reintegro
        boolean reintegro = false;
        //If guess complementalNum
        boolean complementalNum = false;
        for (int i = 0; i < playerTicket.getNumeros().length; i++) {
            //If the assigned number contain the number of the win combination
            if (CombinationManager.contains(winCombination,playerTicket.getNumeros()[i])) {
                contNumbers++;
            }
            //if contains complemental num
            if(winComplementalNum == playerTicket.getNumeros()[i]) {
                complementalNum = true;
            }
        }
        //If contains reintegro
        if(winReintegro == playerTicket.getReintegro()) {
            reintegro=true;
        }
        //List of the prices depending on the numbers which you guess:
        // If you guess one of the prices:
        switch(contNumbers) {
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
        //If you don't have one of the previous prices:
        return Category.NONE;
    }
}
