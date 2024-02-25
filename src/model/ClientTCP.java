package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MilesMarxant
 */
public class ClientTCP implements Runnable {

    private String servidorIP;
    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private int port;

    public ClientTCP(String servidorIP, int port) {
        this.servidorIP = servidorIP;
        this.port = port;
    }

    @Override

    public void run() {

        try {

            sock = new Socket(servidorIP, port);
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
            saludar();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saludar() {
        String salutacio;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Si us plau escriu el teu nom");
            name = scanner.nextLine();
        }
        try {
            salutacio = in.readUTF();
            System.out.println(salutacio);
            out.writeUTF("Hola em dic " + name + " espero que tinguis un mal dia");
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int jugadaRebuda() {
        try {
            int jugada = -1; 
            
            while (jugada == -1) { 
                System.out.println("test");
                jugada = in.readInt(); 
            }
            return jugada;
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            return -10;
        }

    }

    public void jugadaResposta(int jugadaSeleccionada) {
        try {
            out.writeInt(jugadaSeleccionada);
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void despedir() throws IOException {
        String despedida = in.readUTF();
        System.out.println(despedida);
        out.writeUTF("Bon joc");

        tancarConexion();
    }

    private void tancarConexion() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (sock != null) {
                sock.close();
            }

        } catch (IOException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public void esPartidaAcabada(boolean partidaAcabada) {
        if (partidaAcabada) {
            try {
                despedir();
            } catch (IOException ex) {
                Logger.getLogger(ServidorTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
