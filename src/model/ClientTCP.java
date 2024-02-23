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

    private Socket sock;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private int port;

    @Override

    public void run() {


        try {
            Scanner scanner = new Scanner(System.in);
           
            scanner.useDelimiter("\n");
            int jugada =15;
            System.out.println("Si us plau escriu el teu nom");
            name=scanner.nextLine();
            // el port ens ho donarà el UDP
            port =8888;
            Socket sc  = new Socket("127.0.0.1", port);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());
            String msg = in.readUTF();
            System.out.println(msg);
            
            
            out.writeUTF(name);
            System.out.println(in.readUTF());
            
            while (true) {
                
                System.out.println("linea"+jugada);
                 break;
           
                
             }
            sc.close();
          
        } catch (IOException ex) {
            System.out.println("""
                               No es pot establir una connexió.
                               Assegura't que el servidor estigui en execució
                               i que el port sigui accessible.""");
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
