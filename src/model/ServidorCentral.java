
package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServidorCentral {

//	public static void main(String[] args) throws SocketException {
//		int port = 7879;
//                Queue<Partida> colaPartidas = new ConcurrentLinkedQueue<>();
//		DatagramSocket socket = new DatagramSocket(port);
//		System.out.printf("Escoltant al port %d...",port);
//		
//		while(true) {
//			byte[] buffer = new byte[1024];
//			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//			System.out.println("Esperant un nou paquet...");
//			try {
//				//missatge del client
//				socket.receive(packet);
//				String msg = new String(packet.getData()).trim();
//				String address = packet.getAddress().getHostAddress();
//				System.out.printf("%s --> %s\n",address, msg);
//				
//				//resposta echo
//				msg = "ECHO: "+msg;
//				System.out.println(msg);
//				byte[] bytesOUT = msg.getBytes();
//				System.out.println(packet.getSocketAddress().toString());
//				DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, packet.getSocketAddress());
//				socket.send(outPacket);
//				
//				
//			} catch (IOException e) {
//				System.out.println("Error: "+e.getMessage());
//			}
//		}
//	}
    
    private static final int PUERTO_ESCUCHA = 7879;
    private static final Queue<Partida> colaPartidas = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(PUERTO_ESCUCHA);
        System.out.printf("Escoltant al port %d...\n", PUERTO_ESCUCHA);

        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData()).trim();
                String[] partes = mensaje.split(" ");

                if (partes.length == 2 && partes[0].equals("CREAR")) {
                    // Mensaje de creación de partida
                    int portJuego = Integer.parseInt(partes[1]);
                    Partida nuevaPartida = new Partida(packet.getAddress().getHostAddress(), portJuego);
                    System.out.println("intento crear partida");

                    if (registrarPartida(nuevaPartida)) {
                        System.out.println("envio el ok crear partida");
                        enviarRespuesta(socket, packet.getAddress().getHostAddress(), packet.getPort(), "OK " + nuevaPartida.getPort());
                    } else {
                        System.out.println("envio error partida existente");
                        enviarRespuesta(socket, packet.getAddress().getHostAddress(), packet.getPort(), "ERROR Partida ya registrada");
                    }

                } else if (partes.length == 1 && partes[0].equals("UNIR-ME")) {
                    // Mensaje de unirse a una partida
                    Partida partida = obtenerPartida();
                    if (partida != null) {
                        System.out.println("envio ok unirse a partida");
                        enviarRespuesta(socket, packet.getAddress().getHostAddress(), packet.getPort(), "OK " + partida.getPort());
                    } else {
                        System.out.println("envio no hay partidas disponibles, a esperar...");
                        // No hay partidas disponibles, esperar...
                        // (Puedes implementar una lógica para gestionar la espera)
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static synchronized boolean registrarPartida(Partida nuevaPartida) {
    // Verificar si ya hay una partida registrada con la misma IP
    for (Partida partida : colaPartidas) {
        if (partida.getIp().equals(nuevaPartida.getIp())) {
            return false; // Ya hay una partida registrada con la misma IP
        }
    }

    // Si no hay una partida registrada con la misma IP, la registramos
    return colaPartidas.add(nuevaPartida);
    }

    private static Partida obtenerPartida() {
        Partida partida = colaPartidas.poll(); // Utiliza el método poll que es atómico
        return partida;
    }

//    private static void enviarRespuesta(DatagramSocket socket, String ip, int port, String mensaje) throws IOException {
//        String respuesta = ip + "::" + port;
//        byte[] bytesOUT = respuesta.getBytes();
//        DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, socket.getInetAddress(), port);
//        socket.send(outPacket);
//    }
    
    private static void enviarRespuesta(DatagramSocket socket, String ip, int port, String mensaje) {
    try {
        //resposta echo
//	msg = "ECHO: "+msg;
//	System.out.println(msg);
//	byte[] bytesOUT = msg.getBytes();
//	System.out.println(packet.getSocketAddress().toString());
//	DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, packet.getSocketAddress());
//	socket.send(outPacket);
        String respuesta = mensaje;
        byte[] bytesOUT = respuesta.getBytes();
        System.out.println("Valor de ip: " + ip);
        System.out.println("Valor de port: " + port);
        InetAddress address = InetAddress.getByName(ip);
        DatagramPacket outPacket = new DatagramPacket(bytesOUT, bytesOUT.length, address, port);
        socket.send(outPacket);
        System.out.println("He enviado el datagrama");
    } catch (IOException e) {
        System.out.println("Error al enviar respuesta: " + e.getMessage());
    }
    }
}



