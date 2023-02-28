package com.fosanzdev.primitiva;
import java.util.Scanner;

/**
 *
 * The Main class will to show the menu será la encargada de mostrar los menús e interactuar con el usuario, además de
 * enviar la información introducida por el usuario a las distintas clases que conforman La Primitiva.
 * @autor Marco Holtman
 *
 */
public class Main {
    public static Scanner lector;
    //This lector will read what the user insert on the keyboard.
    public static Boleto boleto;
    //This is the object where the numbers are going to be stored.
    public static Category prize;

    public static void main(String[] args) {
        int[] numbers = new int[7];
        //This is the array where the numbers introduced by the user are going to be stored.
        Scanner lector = new Scanner(System.in);
        int option,optionTwo;
        //Variables that will read the number introduced by the user in the menus.
        //"option" is the variable to read .
        //"optionTwo" is for the Second menu (Submenu).
        boolean exitProgram = false;
        //If "exitProgram" becomes true the user will be out of the program.
        boolean changeCombination = false;
        //If "changeCombination" becomes true the user will be returned to the main Menu.
        do {
            option = showMenu();
            switch (option) {
                case 1:
                    System.out.println("INTRODUCE LOS NÚMEROS DEL BOLETO UNO POR UNO");
                    for (int i = 0; i < 6; i++){
                        //This loop will recieve a number sent it by the user
                        // and it will be saved in the array "numbers"
                        System.out.println("Introduce un número (0-49): ");
                        int num = lector.nextInt(49);
                        numbers[i] = num;
                    }
                    boleto = new Boleto(numbers);
                    //This is the object "Boleto" filled with the numbers of the array "numbers".
                    break;
                case 2:
                    boleto = new Boleto();
                    //This is an empty object "Boleto" that later will be filled with random numbers.
                    break;
                default:
                    System.out.println("OPCIÓN INCORRECTA");
            }
            if (option == 0) {
                exitProgram = true;
            } else {
                do {
                    boolean valid;
                    optionTwo = showSubmenu();
                    switch(optionTwo) {

                        case 1:
                            prize = game(boleto);
                            printPrices(prize);
                        case 2:
                            do {
                                prize = game(boleto);
                                valid = prize != Category.NONE;
                                if(valid) {
                                    printPrices(prize);
                                }
                            } while (!valid);

                        case 3:
                            do {
                                prize = game(boleto);
                                valid = prize != Category.NONE && prize != Category.CAT_ESP;
                                if(valid) {
                                    printPrices(prize);
                                }
                            } while (!valid);
                        case 4:
                            int contCat_None = 0;
                            int contCat1 = 0;
                            int contCat2 = 0;
                            int contCat3 = 0;
                            int contCat4 = 0;
                            int contCat5 = 0;
                            int contCat_ESP = 0;
                            for (int i = 0; i <10000; i++){
                                prize = game(boleto);
                                switch(prize) {
                                    case NONE -> contCat_None++;
                                    case CAT_5 -> contCat5++;
                                    case CAT_4 -> contCat4++;
                                    case CAT_3 -> contCat3++;
                                    case CAT_2 -> contCat2++;
                                    case CAT_1 -> contCat1++;
                                    case CAT_ESP -> contCat_ESP++;
                                }
                            }
                            System.out.println("-- RESULTADOS --");
                            System.out.println("Ningún premio: " + contCat_None);
                            System.out.println("Premio de categoría 5: " + contCat5);
                            System.out.println("Premio de categoría 4: " + contCat4);
                            System.out.println("Premio de categoría 3: " + contCat3);
                            System.out.println("Premio de categoría 2: " + contCat2);
                            System.out.println("Premio de categoría 1: " + contCat1);
                            System.out.println("Premio de categoría especial: " + contCat_ESP);

                        case 5:
                            do {
                                prize = game(boleto);
                                valid = prize == Category.CAT_ESP;
                                if(valid) {
                                    printPrices(prize);
                                }
                            } while (!valid);

                    }
                    if(optionTwo == 0) {
                        changeCombination = true;
                    }
                }while(!changeCombination);
            }
        } while (!exitProgram);
        System.out.println("¡HASTA LA PRÓXIMA!");
    }

    /**
     * This method will show up the Main Menu. It will wait to the user answer and when
     * the user chooses a valid option it will be out of the loop.
     * @return The option chosen by the user
     */
    public static int showMenu() {
        int option;
        boolean valid;
        do {
            System.out.println("JUEGO DE LA PRIMITIVA");
            System.out.println("---------------------");
            System.out.println("¿DESEA INTRODUCIR LOS NÚMEROS USTED MSIMO (1) O QUIERE QUE LOS GENERE ALEATORIAMENTE? (2):");
            System.out.println("1.-INTRODUCE 6 NÚMEROS MANUALMENTE (EL REINTEGRO SE GENERA ALEATORIAMENTE)");
            System.out.println("2.-GENERAR LA COMBINACIÓN DE NÚMEROS ALEATORIAMENTE");
            System.out.println("---------------------");
            System.out.println("0.-SALIR");
            System.out.println("Introduce una opción: ");
            option = lector.nextInt();
            lector.nextLine();
            valid = option >= 0 && option <= 2;
            if (!valid) {
                System.out.println("OPCIÓN INCORRECTA, ELIJA UNA OPCIÓN CONTENIDA DENTRO DEL RANGO");
            }
        } while (!valid);
        return option;
    }

    /**
     * This method will show up the Second Menu. It will wait to the user answer and when
     * the user chooses a valid option it will be out of the loop.
     * @return The option chosen by the user
     */
    public static int showSubmenu() {
        int option;
        boolean valid;
        do {
            System.out.println("ELIGE LA MODALIDAD DE JUEGO");
            System.out.println("---------------------");
            System.out.println("1. JUEGO ÚNICO");
            System.out.println("2. JUGAR HASTA OBTENER PREMIO");
            System.out.println("3. JUGAR HASTA OBTENER PREMIO (SIN REINTEGRO)");
            System.out.println("4. CICLO DE 10000 SORTEOS");
            System.out.println("5. JUGAR HASTA OBTENER PREMIO CATEGORÍA ESPECIAL");
            System.out.println("---------------------");
            System.out.println("0. CAMBIAR COMBINACIÓN");
            System.out.println("Introduce una opción: ");
            option = lector.nextInt();
            lector.nextLine();
            valid = option >= 0 && option <= 5;
            if (!valid) {
                System.out.println("OPCIÓN INCORRECTA, ELIJA UNA OPCIÓN CONTENIDA DENTRO DEL RANGO");
            }
        } while (!valid);
        return option;
    }

    public static Category game(Boleto boletoJugador) {
        Checking winCombination = new Checking();
        Category prize = winCombination.checkWin(boletoJugador);
        return prize;
    }

    /**
     * This method will print on screen which prize has won the user.
     * @param prize Prize of the game played.
     */
    public static void printPrices(Category prize) {
        switch(prize) {
            case NONE -> System.out.println("NO SE HA GANADO NINGÚN PREMIO...");
            case CAT_5 -> System.out.println("¡HAS GANADO UN PREMIO DE CATEGORÍA 5!");
            case CAT_4 -> System.out.println("¡HAS GANADO UN PREMIO DE CATEGORÍA 4!");
            case CAT_3 -> System.out.println("¡HAS GANADO UN PREMIO DE CATEGORÍA 3!");
            case CAT_2 -> System.out.println("¡HAS GANADO UN PREMIO DE CATEGORÍA 2!");
            case CAT_1 -> System.out.println("¡HAS GANADO UN PREMIO DE CATEGORÍA 1!");
            case CAT_ESP -> System.out.println("¡¡¡HAS GANADO UN PREMIO DE CATEGORÍA ESPECIAL!!!");
        }
    }
}