package com.fosanzdev.primitiva;
import javax.crypto.spec.PSource;
import java.util.Scanner;

public class Main {
    public static Scanner lector;
    public static Boleto boleto;
    public static Category prize;

    public static void main(String[] args) {
        int[] numeros = new int[7];
        Scanner lector = new Scanner(System.in);
        int opcion,opcionDos;
        boolean salirPrograma = false;
        boolean cambiarCombinacion = false;
        do {
            opcion = mostrarMenu();
            switch (opcion) {
                case 1:
                    System.out.println("INTRODUCE LOS NÚMEROS DEL BOLETO UNO POR UNO");
                    for (int i = 0; i < 6; i++){
                        System.out.println("Introduce un número (0-49): ");
                        int num = lector.nextInt(49);
                        numeros[i] = num;
                    }
                    boleto = new Boleto(numeros);
                    break;
                case 2:
                    boleto = new Boleto();
                    break;
                default:
                    System.out.println("OPCIÓN INCORRECTA");
            }
            if (opcion == 0) {
                salirPrograma = true;
            } else {
                do {
                    boolean valid;
                    opcionDos = mostrarSubmenu();
                    switch(opcionDos) {

                        case 1:
                            prize = juego(boleto);
                            printPrices(prize);
                        case 2:
                            do {
                                prize = juego(boleto);
                                valid = prize != Category.NONE;
                                if(valid) {
                                    printPrices(prize);
                                }
                            } while (!valid);

                        case 3:
                            do {
                                prize = juego(boleto);
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
                                prize = juego(boleto);
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
                                prize = juego(boleto);
                                valid = prize == Category.CAT_ESP;
                                if(valid) {
                                    printPrices(prize);
                                }
                            } while (!valid);

                    }
                    if(opcionDos == 0) {
                        cambiarCombinacion = true;
                    }
                }while(!cambiarCombinacion);
            }
        } while (!salirPrograma);
        System.out.println("¡HASTA LA PRÓXIMA!");
    }

    public static int mostrarMenu() {
        int opcion;
        boolean valido;
        do {
            System.out.println("JUEGO DE LA PRIMITIVA");
            System.out.println("---------------------");
            System.out.println("¿DESEA INTRODUCIR LOS NÚMEROS USTED MSIMO (1) O QUIERE QUE LOS GENERE ALEATORIAMENTE? (2):");
            System.out.println("1.-INTRODUCE 6 NÚMEROS MANUALMENTE (EL REINTEGRO SE GENERA ALEATORIAMENTE)");
            System.out.println("2.-GENERAR LA COMBINACIÓN DE NÚMEROS ALEATORIAMENTE");
            System.out.println("---------------------");
            System.out.println("0.-SALIR");
            System.out.println("Introduce una opción: ");
            opcion = lector.nextInt();
            lector.nextLine();
            valido = opcion >= 0 && opcion <= 2;
            if (!valido) {
                System.out.println("OPCIÓN INCORRECTA, PULSE OTRA DENTRO DEL RANGO");
            }
        } while (!valido);
        return opcion;
    }

    public static int mostrarSubmenu() {
        int opcion;
        boolean valido;
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
            opcion = lector.nextInt();
            lector.nextLine();
            valido = opcion >= 0 && opcion <= 5;
            if (!valido) {
                System.out.println("OPCIÓN INCORRECTA");
            }
        } while (!valido);
        return opcion;
    }

    public static Category juego(Boleto boletoJugador) {
        Checking winCombination = new Checking();
        Category prize = winCombination.checkWin(boletoJugador);
        return prize;
    }

    public static void printPrices(Category prize) {
        switch(prize) {
            case NONE -> System.out.println("No se ha ganado nungún premio");
            case CAT_5 -> System.out.println("Has ganado un premio de categroria 5");
            case CAT_4 -> System.out.println("Has ganado un premio de categroria 4");
            case CAT_3 -> System.out.println("Has ganado un premio de categroria 3");
            case CAT_2 -> System.out.println("Has ganado un premio de categroria 2");
            case CAT_1 -> System.out.println("Has ganado un premio de categroria 1");
            case CAT_ESP -> System.out.println("Has ganado un premio de categroria especial");
        }
    }
}