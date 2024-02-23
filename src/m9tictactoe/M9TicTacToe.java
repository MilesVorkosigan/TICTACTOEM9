package m9tictactoe;

import java.util.Scanner;

/**
 *
 * @author MilesMarxant
 */
public class M9TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean jogar = true;
        System.out.println("Bon dia Selecciona el que vols fer");
        System.out.println("""
                           1- Connectar-se a una partida
                           2- Crear una partida nova
                           3- Sortir""");
        Scanner sc = new Scanner(System.in);
        while (jogar) {
            if (sc.hasNextInt()) {
                switch (sc.nextInt()) {
                    case 1 -> crearServidorTCP();
                    case 2 -> crearClientTCP();
                    case 3 -> jogar = false;
                    default -> System.out.println("Error, Has de seleccionar 1,2,3 opcions");
                }

            } else {
                System.out.println("Error, Has de seleccionar 1,2,3 opcions");
            }

        }

    }

    private static void crearServidorTCP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void crearClientTCP() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
