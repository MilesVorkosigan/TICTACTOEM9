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
public class ClientTCP {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            Socket sock  = new Socket("localhost", 8888);
            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            String msg = in.readUTF();
            System.out.println(msg);
            ClientFil fil= new ClientFil(in,out);
            String nomClient  =sc.next();
            out.writeUTF(nomClient);
            fil.start();
            fil.join();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
