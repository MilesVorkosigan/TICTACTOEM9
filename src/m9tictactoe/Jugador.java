
package m9tictactoe;

import javax.swing.ImageIcon;

/**
 *
 * @author MilesMarxant
 * @author Víctor
 */
public class Jugador {
    private boolean iniciadaPartida;
    private boolean tornJugador;
    private int jugadaSeleccionada;
    private String fitxa ;
    

    public Jugador() {
        
    }

    public Jugador(boolean iniciadaPartida, boolean jugadorPrimer, int jugadaSeleccionada, String fitxa) {
        this.iniciadaPartida = iniciadaPartida;
        this.tornJugador = jugadorPrimer;
        this.jugadaSeleccionada = jugadaSeleccionada;
        this.fitxa = fitxa;
    }

   

    public boolean isIniciadaPartida() {
        return iniciadaPartida;
    }

    public void setIniciadaPartida(boolean iniciadaPartida) {
        this.iniciadaPartida = iniciadaPartida;
    }

    public boolean isTornJugador() {
        return tornJugador;
    }

    public void setTornJugador(boolean tornJugador) {
        this.tornJugador = tornJugador;
    }



    public int getJugadaSeleccionada() {
        return jugadaSeleccionada;
    }

    public void setJugadaSeleccionada(int jugadaSeleccionada) {
        this.jugadaSeleccionada = jugadaSeleccionada;
    }

    public String getFitxa() {
        return fitxa;
    }

    public void setFitxa(String fitxa) {
        this.fitxa = fitxa;
    }


    
}
