
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author MilesMarxant
 */
class ServidorFil extends Thread{
    private DataInputStream in ;
    private DataOutputStream out ;
    private String nomClient ;

    public ServidorFil(DataInputStream in, DataOutputStream out, String nomClient) {
        this.in = in;
        this.out = out;
        this.nomClient = nomClient;
    }
    
    @Override
    public void run(){
        
    }
}
