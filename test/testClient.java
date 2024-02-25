
import java.io.IOException;
import m9tictactoe.Frame.FrameJoc;
import m9tictactoe.Jugador;
import model.ClientTCP;

/**
 *
 * @author MilesMarxant
 */
public class testClient {

    public static void main(String[] args) throws IOException {
        Jugador jugador = new Jugador(true, false, 0, "X");
        

        ClientTCP client = new ClientTCP("127.0.0.1", 3000);

        client.run();
        // hi ha un bug del Frame que no permet comunicació per tant envio les comunicacions editades
       //FrameJoc jocClient = new FrameJoc(jugador);
/*         jocClient.inciarPartida();

        while (!jocClient.isFinalPartida()) {
            // Si el cliente ha recibido una jugada del servidor, actualiza el juego

            jocClient.rebreJugadaSeleccionada(client.jugadaRebuda());

            while (!jocClient.hanEscullitJugada()) {
                // Si el jugador ha seleccionado una jugada, envíala al servidor
                if (jocClient.hanEscullitJugada()) {
                    client.jugadaResposta(jocClient.enviarJugadaSeleccionada());
                }
            }

        } */
      

        // Cuando la partida termina, despide al cliente
        client.despedir();
    }
}
