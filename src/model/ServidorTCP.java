package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MilesMarxant
 */
public class ServidorTCP {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8888);
            Socket sc;
            while (true) {
                sc = server.accept();
                DataInputStream in = new DataInputStream(sc.getInputStream());
                DataOutputStream out = new DataOutputStream(sc.getOutputStream());
                out.writeUTF("Escriu el teu nom");
                String nomClient =in.readUTF() ;
                ServidorFil fil = new ServidorFil(in,out,nomClient);
                fil.start();
                System.out.println("Creada connexió amb "+nomClient);
            }

        } catch (IOException e) {
        }
    }

}
