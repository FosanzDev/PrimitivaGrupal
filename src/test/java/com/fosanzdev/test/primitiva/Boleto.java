package com.test.fosanzdev.primitiva;

public class Boleto {

    // Atributes
    // ---------
    // numeros --> Array of 6 numbers representing the chosen combination
    // reintegro --> Number representing the reintegro
    // (note: reintegro will always be a random number between 0 and 9)
    // ---------

    private int[] numeros;
    private int reintegro;


    // Constructor (with user input)
    public Boleto(int[] numeros){
        this.numeros = numeros;
        this.reintegro = CombinationManager.newReintegro();
    }

    // Constructor (without user input)
    public Boleto(){
        this(CombinationManager.newCombination());
    }


    // Getters
    public int[] getNumeros() {
        return numeros;
    }

    public int getReintegro() {
        return reintegro;
    }

    // toString Override (for printing purposes)
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        for (int i : numeros)
            s.append(i).append(", ");

        s.append("R: ").append(reintegro);

        return s.toString();
    }
}
