package com.test.fosanzdev.primitiva;

public class Comprobador {

    // Atributes
    // ---------
    // combinacionGanadora: number combination of the winning ticket
    // reintegroGanador: winning ticket's reintegro
    // complementario: complementary number
    // ----------

    private int[] combinacionGanadora;
    private int reintegroGanador;
    private int complementario;

    // Constructor
    public Comprobador(){
        combinacionGanadora = CombinationManager.newCombination();
        reintegroGanador = CombinationManager.newReintegro();
        complementario = CombinationManager.newNumber();
    }

    /**
     * Generates a new winning combination
     */
    public void newWinningCombination(){
        combinacionGanadora = CombinationManager.newCombination();
        reintegroGanador = CombinationManager.newReintegro();
        complementario = CombinationManager.newNumber();
    }


    /**
     * Checks if the player's combination matches the winning combination
     * @param jugador Boleto object representing the player's combination
     * @return ResultEnum object representing the result of the check
     */
    public ResultEnum checkCombination(Boleto jugador){
        // Declare variables
        int aciertos = 0;
        boolean reint = false;
        boolean comp = false;

        // Check if the player's combination matches the winning combination
        for (int i : jugador.getNumeros()){
            if (CombinationManager.contains(combinacionGanadora, i))
                aciertos++;

            // Also check if the complementary number is in the player's combination
            if (i == complementario)
                comp = true;
        }

        // Check if the player's reintegro matches the winning reintegro
        if (jugador.getReintegro() == reintegroGanador)
            reint = true;


        // Return the result of the check
        if (aciertos == 6 && reint)
            return ResultEnum.CAT_ESPECIAL;

        else if (aciertos == 6)
            return ResultEnum.CAT1;

        else if (aciertos == 5 && comp)
            return ResultEnum.CAT2;

        else if (aciertos == 5)
            return ResultEnum.CAT3;

        else if (aciertos == 4)
            return ResultEnum.CAT4;

        else if (aciertos == 3)
            return ResultEnum.CAT5;

        else
            return ResultEnum.NONE;
    }

    // toString override (for printing purposes)
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        for(int i : combinacionGanadora)
            s.append(i).append(", ");

        s.append("R: ").append(reintegroGanador).append(", C: ").append(complementario);

        return s.toString();
    }

}
