package m9tictactoe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import m9tictactoe.Frame.FrameJoc;
import model.ClientTCP;
import model.ServidorTCP;

public class M9TicTacToe {

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
        //Hem tingutt problemes amb el frame no deixaba continuar però lalógica era aquesta
        Jugador jugador = new Jugador(true, true, 0, "0");
        // FrameJoc joc = new FrameJoc(jugador);
        ServidorTCP server = new ServidorTCP(gamePort);
        server.run();
//                while (!joc.isFinalPartida()) {
//
//            if (joc.hanEscullitJugada()) {
//                try {
//                    server.comunicantJugada(joc.enviarJugadaSeleccionada());
//                } catch (IOException ex) {
//                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            }
//        }
        try {
            server.despedir();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        String gameIp = "127.0.0.1";
        Jugador jugador = new Jugador(true, false, 0, "X");

        ClientTCP client;
        client = new ClientTCP(gameIp, Integer.parseInt(partes[1]));

        client.run();
        // hi ha un bug del Frame que no permet comunicació per tant envio les comunicacions editades
        //FrameJoc jocClient = new FrameJoc(jugador);
/*         jocClient.inciarPartida();

        while (!jocClient.isFinalPartida()) {
            // Si el cliente ha recibido una jugada del servidor, actualiza el juego

            jocClient.rebreJugadaSeleccionada(client.jugadaRebuda());

            while (!jocClient.hanEscullitJugada()) {
                // Si el jugador ha seleccionado una jugada, envíala al servidor
                if (jocClient.hanEscullitJugada()) {
                    client.jugadaResposta(jocClient.enviarJugadaSeleccionada());
                }
            }

        } */

        // Cuando la partida termina, despide al cliente
        client.despedir();
    }

}
