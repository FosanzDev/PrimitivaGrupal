package com.test.fosanzdev.primitiva;

import java.util.Random;

public class CombinationManager {

    private static final Random random = new Random();

    /**
     * Generates a new random combination of 6 numbers between 0 and 49
     * Generated numbers are not repeated
     *
     * @return a new random combination of 6 numbers between 0 and 49
     */
    public static int[] newCombination(){
        //Create a new combination array
        int[] combination = new int[6];

        for (int i = 0; i < combination.length; i++) {
            //Generate a new random number
            int k = random.nextInt(49);

            //Check if the number is already in the combination
            while(contains(combination, k))
                k = random.nextInt(49);

            //Add the number to the combination
            combination[i] = k;
        }

        //Return the combination
        return combination;
    }

    /**
     * Generates a new random number between 0 and 9
     * @return a new random number between 0 and 9
     */
    public static int newReintegro(){
        return random.nextInt(9);
    }

    /**
     * Generates a new random number between 0 and 49
     * @ return a new random number between 0 and 49
     */
    public static int newNumber(){
        return random.nextInt(49);
    }


    /**
     * Checks if a number is in a combination
     * @param combination the combination to check
     * @param k the number to check
     * @return true if the number is in the combination, false otherwise
     */
    public static boolean contains(int[] combination, int k){
        for (int j : combination) {
            if (j == k) {
                return true;
            }
        }
        return false;
    }


}
