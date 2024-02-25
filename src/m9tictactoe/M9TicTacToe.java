package m9tictactoe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import m9tictactoe.Frame.FrameJoc;

public class M9TicTacToe {
private final boolean INICIAR_PARTIDA=true;
private final boolean TORN_JUGADOR=true;
private final boolean TORN_CONTRARI = false;
private final boolean SEGON_TORN=false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException {
        
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
        int serverPort = 7879;

        Random random = new Random();
        int clientPort = random.nextInt(5535) + 1024;

        DatagramSocket socket = new DatagramSocket(clientPort);
        
        boolean jogar = true;
        System.out.println("Bon dia Selecciona el que vols fer");
        System.out.println("""
                           1- Crear una partida nova
                           2- Connectar-se a una partida
                           3- Sortir""");
        Scanner sc = new Scanner(System.in);
        while (jogar) {
            if (sc.hasNextInt()) {
                switch (sc.nextInt()) {
                    case 1 -> {
                        
                      
                        crearServidorTCP(socket, serverPort, serverAddress);
                    }
                    case 2 ->
                        crearClientTCP(socket, serverPort, serverAddress);
                    case 3 ->
                        jogar = false;
                    default ->
                        System.out.println("Error, Has de seleccionar 1,2,3 opcions");
                }

            } else {
                System.out.println("Error, Has de seleccionar 1,2,3 opcions");
            }
        }
        sc.close();
    }

    private static void crearServidorTCP(DatagramSocket socket, int serverPort, InetAddress serverAddress) throws IOException {
        //enviar missatge a servidor
        Random random = new Random();
        int gamePort = random.nextInt(5535) + 1024;
        String msg = "CREAR " + gamePort;
        System.out.println(msg);
        byte[] bytesOUT = msg.getBytes();
        DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, serverAddress, serverPort);
        socket.send(outPacket);
        System.out.println(socket.getPort());
        
        //rebre echo del servidor
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData()).trim();
        System.out.println(response);
        
        //realizar conexión TCP con el Jugador 2 una vez solicite unirse a la partida y comenzar a jugar
        
    }

    private static void crearClientTCP(DatagramSocket socket, int serverPort, InetAddress serverAddress) throws IOException {
        //enviar missatge a servidor
        String msg = "UNIR-ME";
        System.out.println(msg);
        byte[] bytesOUT = msg.getBytes();
        DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, serverAddress, serverPort);
        socket.send(outPacket);
        System.out.println(socket.getPort());
        
        //rebre echo del servidor
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData()).trim();
        System.out.println(response);
        String[] partes = response.split(" ");
        
        if (partes.length == 2 && partes[0].equals("OK")) {
            int gamePort = Integer.parseInt(partes[1]); //se guarda el puerto de la partida
            System.out.println("game port: " + gamePort);
        }
        
        //realizar conexión TCP con el Jugador 1 (creador de la partida) y comenzar a jugar
    }

}
