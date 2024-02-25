
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import m9tictactoe.Frame.FrameJoc;
import m9tictactoe.Jugador;
import model.ServidorTCP;

/**
 *
 * @author MilesMarxant
 */
public class test {

    public static void main(String[] args) {
        Jugador jugador = new Jugador(true, true, 0, "0");
        FrameJoc joc = new FrameJoc(jugador);
        ServidorTCP server = new ServidorTCP(3000);
        server.run();
        
        

        while (!joc.isFinalPartida()) {

            if (joc.hanEscullitJugada()) {
                try {
                    server.comunicantJugada(joc.enviarJugadaSeleccionada());
                } catch (IOException ex) {
                    Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        try {
            server.despedir();
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
