package com.fosanzdev.primitiva;
import java.util.Scanner;

public class Main {
    static int opcion;
    int[] boleto = new int[7];
    public static void main(String[] args) {
        int[] vector = new int[7];
        Scanner lector = new Scanner(System.in);
        do {
            mostrarMenu();
            opcion = lector.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("INTRODUCE LOS NÚMEROS DEL BOLETO UNO POR UNO");
                    for (int i = 1; i <= 6; i++){
                        lector.nextInt();
                    }
                    break;
                case 2:
                    break;
                default:
                    System.out.println("OPCIÓN INCORRECTA");
            }
        } while (opcion != 0);
        System.out.println("¡HASTA LA PRÓXIMA!");

        public static void mostrarMenu() {
            int opcion;
            boolean valido;
            do {
                System.out.println("JUEGO DE LA PRIMITIVA");
                System.out.println("---------------------");
                System.out.println("¿DESEA INTRODUCIR LOS NÚMEROS USTED MSIMO (1) O QUIERE QUE LOS GENERE ALEATORIAMENTE? (2):");
                System.out.println("1.-INTRODUCE 5 NÚMEROS MANUALMENTE (EL REINTEGRO SE GENERA ALEATORIAMENTE)");
                System.out.println("2.-GENERAR LA COMBINACIÓN DE NÚMEROS ALEATORIAMENTE");
                System.out.println("---------------------");
                System.out.println("0.-SALIR");
                opcion = lector.nextInt();
                lector.nextLine();
                valido = opcion >= 0 && opcion <= 2;
                if (!valido) {
                    System.out.println("OPCIÓN INCORRECTA");
                }
            } while (!valido);
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
                System.out.println("0. VOLVER AL MENÚ PRINCIPAL");
                opcion = lector.nextInt();
                lector.nextLine();
                valido = opcion >= 0 && opcion <= 5;
                if (!valido) {
                    System.out.println("OPCIÓN INCORRECTA");
                }
            } while (!valido);
        }
    }
}