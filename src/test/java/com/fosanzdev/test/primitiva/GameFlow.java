package com.test.fosanzdev.primitiva;

import com.fosanzdev.jresources.JDeploy;
import com.fosanzdev.jresources.JRequest;

public class GameFlow {

    public static void main(String[] args) {
        Comprobador ganador = new Comprobador();
        Boleto jugador = readCombination();



        //------------------ GAME FLOW ------------------



        do{
            // 1. Ask the user for the game mode
            JDeploy.deployMenu("PRIMITIVA", "0. Salir", ". ", MENU);

            int mode = JRequest.requestInt("Introduzca una opcion -> ");

            // 2. Check the game mode
            switch (mode) {
                case 0 -> {
                    System.out.println("Hasta la proxima!");
                    System.exit(0);
                }
                case 1 -> {
                    ganador.newWinningCombination();
                    singleGame(ganador, jugador);
                }
                case 2 -> prizedGame(ganador, jugador);
                case 3 -> prizedGameNoReint(ganador, jugador);
                case 4 -> cycleGame(ganador, jugador);
                case 5 -> maxPrize(ganador, jugador);
                case 6 -> jugador = readCombination();
                default -> System.out.println("Opcion no valida");
            }

        } while (true);

    }



    // --------------------- IO METHODS ---------------------



    // Menu options
    private static final String[] MENU = {
            "Juego único", "Juego hasta obtener premio",
            "Juego hasta obtener premio (sin reintegro)",
            "Ciclo de 10.000 sorteos", "Juego hasta obtener la categoria especial",
            "Cambiar combinacion"};


    /**
     * Reads the user's combination
     * @return a new ticket with a random combination or the user's combination
     */
    public static Boleto readCombination(){
        boolean opt = JRequest.readOption("¿Desea jugar con numeros propios? (y/n) -> ");

        // 2. If the user wants to play with random numbers, generate them
        if (!opt)
            return new Boleto();

            // 3. If the user wants to play with his own numbers, ask for them
        else {
            int[] combinacion = new int[6];

            for (int i = 0; i < combinacion.length; i++) {
                // 3.1. Ask for the number
                int num = JRequest.requestInt("Introduzca el numero " + (i + 1) + " -> ");

                // 3.2. Check if the number is valid
                while (num < 0 || num > 49 || CombinationManager.contains(combinacion, num)){
                    System.out.println("El numero introducido no es valido");
                    num = JRequest.requestInt("Introduzca el numero " + (i + 1) + " -> ");
                }

                // 3.3. Add the number to the combination
                combinacion[i] = num;
            }

            // 3.4. Create the ticket
            return new Boleto(combinacion);
        }
    }

    /**
     * Returns the result of the game
     * @param ganador the winning combination
     * @param jugador the player's combination
     */
    public static void singleGame(Comprobador ganador, Boleto jugador){
        System.out.println("Juego sencillo:");
        System.out.println("Su combinacion: " + jugador);
        System.out.println("Combinacion ganadora: " + ganador);
        System.out.println("Premio: " + ganador.checkCombination(jugador));
    }

    /**
     * Plays until the player gets a prize
     * @param ganador the winning combination
     * @param jugador the player's combination
     */
    public static void prizedGame(Comprobador ganador, Boleto jugador){
        System.out.println("Juego hasta obtener premio:");
        // Check the combination
        ResultEnum premio;
        premio = ganador.checkCombination(jugador);

        // Checks if the player gets a prize
        while (premio == ResultEnum.NONE){
            ganador.newWinningCombination();
            premio = ganador.checkCombination(jugador);
        }

        // Show the results
        singleGame(ganador, jugador);
    }

    /**
     * Plays until the player gets a prize (without the reintegro)
     * @param ganador the winning combination
     * @param jugador the player's combination
     */
    public static void prizedGameNoReint(Comprobador ganador, Boleto jugador){
        System.out.println("Juego hasta obtener premio (sin reintegro):");
        // Check the combination
        ResultEnum premio;
        premio = ganador.checkCombination(jugador);

        // If the player gets a prize, check if it's the category especial
        while (premio == ResultEnum.NONE || premio == ResultEnum.CAT_ESPECIAL){
            ganador.newWinningCombination();
            premio = ganador.checkCombination(jugador);
        }

        // Show the results
        singleGame(ganador, jugador);
    }

    /**
     * Plays 10.000 times and shows the results
     * @param ganador the winning combination
     * @param jugador the player's combination
     */
    public static void cycleGame(Comprobador ganador, Boleto jugador){
        System.out.println("Ciclo de 10.000 sorteos:");
        ResultEnum[] premios = new ResultEnum[10000];

        // Fill the result array with the results of the 10.000 games
        for (int i = 0; i < premios.length; i++) {
            ganador.newWinningCombination();
            premios[i] = ganador.checkCombination(jugador);
        }

        // Declare the counters
        int cat_esp = 0;
        int cat_1 = 0;
        int cat_2 = 0;
        int cat_3 = 0;
        int cat_4 = 0;
        int cat_5 = 0;

        // Count the results
        for (ResultEnum premio : premios) {
            switch (premio) {
                case CAT_ESPECIAL -> cat_esp++;
                case CAT1 -> cat_1++;
                case CAT2 -> cat_2++;
                case CAT3 -> cat_3++;
                case CAT4 -> cat_4++;
                case CAT5 -> cat_5++;
            }
        }

        // Show the results
        System.out.println("Premios obtenidos: ");
        System.out.println("Categoria especial: " + cat_esp);
        System.out.println("Categoria 1: " + cat_1);
        System.out.println("Categoria 2: " + cat_2);
        System.out.println("Categoria 3: " + cat_3);
        System.out.println("Categoria 4: " + cat_4);
        System.out.println("Categoria 5: " + cat_5);
    }

    /**
     * Plays until the player gets the special category
     * @param ganador the winning combination
     * @param jugador the player's combination
     */
    public static void maxPrize(Comprobador ganador, Boleto jugador){
        System.out.println("Juego hasta obtener la categoria especial:");
        // Check if the player has the special category
        ResultEnum premio;
        premio = ganador.checkCombination(jugador);

        // If the player has the special category, show the result
        // If not, generate new combinations until the player gets the special category
        while (premio != ResultEnum.CAT_ESPECIAL){
            ganador.newWinningCombination();
            premio = ganador.checkCombination(jugador);
        }

        // Show the result
        singleGame(ganador, jugador);
    }
}
