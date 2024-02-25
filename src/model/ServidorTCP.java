package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MilesMarxant
 */
public class ServidorTCP implements Runnable {

    private int port;
    private ServerSocket serverSc;
    private Socket sc;
    private DataInputStream in;
    private DataOutputStream out;

    private String name;

    public ServidorTCP(int port) {

        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServidorTCP(int port, ServerSocket ServerSocket) {
        this.port = port;
        this.serverSc = ServerSocket;
    }

    @Override
    public void run() {

        // crear port
        // port = crearAleatoriamentPort();
        try {
            serverSc = new ServerSocket(port);
            System.out.println("Servidor TCP escoltant " + serverSc);

            System.out.println("Esperando conexión de cliente...");
            sc = serverSc.accept();
            System.out.println("Cliente conectado desde " + sc.getRemoteSocketAddress());

            // Manejar la conexión con el cliente
            manejarConnexioClient();
            saludar();

        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

    }

    private void manejarConnexioClient() throws IOException {
        in = new DataInputStream(sc.getInputStream());
        out = new DataOutputStream(sc.getOutputStream());

    }

    public void saludar() throws IOException {
        /// Guio que volem Saludar esperar
        System.out.println("Escriu el teu nom Usuari");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        System.out.println(" Nom" + name);
        scanner.close();

        System.out.println("Esperant la resposta del contrari");
        out.writeUTF("Hola em dic " + name + " preparat per jugar?");
        System.out.println("Resposta : " + in.readUTF());
    }

    public int comunicantJugada(int seleccionadaJugada) throws IOException {
        out.writeInt(seleccionadaJugada);
        int jugadaRebuda = in.readInt();
        return jugadaRebuda;
    }

    public void despedir() throws IOException {
        out.writeUTF("Bon joc Client");
        String despedida = in.readUTF();
        System.out.println(despedida);
        tancarConexion();
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

    private void tancarConexion() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (sc != null) {
                sc.close();
            }
            if (serverSc != null) {
                serverSc.close();
            }
        } catch (IOException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

}
