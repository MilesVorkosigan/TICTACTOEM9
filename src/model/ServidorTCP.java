package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author MilesMarxant
 */
public class ServidorTCP implements Runnable {

    private int port;
    private ServerSocket serverSc;

    private boolean running;
    private String name;
    private String nameEnemy;

    public ServidorTCP(int port) {

        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ServidorTCP(int port, ServerSocket ServerSocket) {
        this.port = port;
        this.serverSc = ServerSocket;
    }

    @Override
    public void run() {
        DataInputStream in;
        DataOutputStream out;
        System.out.println("Escriu el teu nom Usuari");
        Scanner scanner = new Scanner(System.in);
        name = scanner.nextLine();
        scanner.close();
        //crear port
        // port = crearAleatoriamentPort();
        port = 8888;
        int jugada = 15;
        try {
            serverSc = new ServerSocket(port);
            Socket sc;
            sc = serverSc.accept();
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF("Hola el meu nom és " + name + ".");

            nameEnemy = in.readUTF();
            System.out.println("Creada connexió amb " + nameEnemy);
            out.writeUTF("Molt bé " + nameEnemy + ", avui tindras un mal dia");
            while (true) {
                System.out.println("linea" + jugada);
                break;

            }
            sc.close();

        } catch (IOException e) {
        }

    }

    private int crearAleatoriamentPort() {
        Random random = new Random();
        // El rang dels ports vàlits es de 0 a 65535
        return random.nextInt(65536);
    }

}
