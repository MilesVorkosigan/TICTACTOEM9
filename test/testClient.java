
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
        FrameJoc joc = new FrameJoc(jugador);

        ClientTCP client = new ClientTCP("localhost", 8888);

    client.run();
    

        while (!joc.isFinalPartida()) {
            // Si el cliente ha recibido una jugada del servidor, actualiza el juego
            if (!joc.hanEscullitJugada()) {
                joc.rebreJugadaSeleccionada(client.jugadaRebuda());
            }

            // Si el jugador ha seleccionado una jugada, envíala al servidor
            if (joc.hanEscullitJugada()) {
                client.jugadaResposta(joc.enviarJugadaSeleccionada());
            }
        }

        // Cuando la partida termina, despide al cliente
        client.despedir();
    }
}
