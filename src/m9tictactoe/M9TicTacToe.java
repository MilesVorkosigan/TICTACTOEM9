


package m9tictactoe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;



public class M9TicTacToe {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
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
                Scanner teclat = new Scanner(System.in);
		            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
		int serverPort = 7879;
		
                Random random = new Random();
                int clientPort = random.nextInt(5535) + 1024;
                
		DatagramSocket socket = new DatagramSocket(clientPort);
		
		System.out.print("MSG[enter per finalitzar]: ");
		String msg = teclat.nextLine();
		while(!msg.isEmpty()) {
			//enviar missatge a servidor
			byte[] bytesOUT = msg.getBytes();
			DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, serverAddress, serverPort);
			socket.send(outPacket);
                        System.out.println(socket.getPort());
                        
			
			//rebre echo del servidor
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			socket.receive(packet);
			System.out.println(new String(packet.getData()).trim());
			
			//obtenir nou missatge de teclat
			System.out.print("MSG[enter per finalitzar]: ");
			msg = teclat.nextLine();
        }

    }

}
