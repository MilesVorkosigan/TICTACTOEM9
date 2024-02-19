
package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 *
 * @author MilesMarxant
 */
class ClientFil extends Thread{
    private DataInputStream in;
    private DataOutputStream out;

    public ClientFil(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }
    @Override
   public void run(){
       
   }
    
}
