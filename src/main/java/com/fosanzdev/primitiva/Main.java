package com.fosanzdev.primitiva;
import java.util.Scanner;

public class Main {
    public static Scanner lector;
    public static Boleto boleto;

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
                    opcionDos = mostrarSubmenu();
                    switch(opcionDos) {
                        case 1:

                        case 2:

                        case 3:

                        case 4:

                        case 5:

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
}