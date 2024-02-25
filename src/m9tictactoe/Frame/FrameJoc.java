package m9tictactoe.Frame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import m9tictactoe.Jugador;

public class FrameJoc extends JPanel implements ActionListener {

    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    private Jugador player;
    String JUGADOR_FITXA = "X";
    String CONTRARI_FITXA = "O";
    int jugadaSeleccionada;
   
    private boolean seleccionatCas = false;
 
    private boolean finalPartida = false;

    public boolean isFinalPartida() {
        return finalPartida;
    }

    public void setFinalPartida(boolean finalPartida) {
        this.finalPartida = finalPartida;
    }

    public Jugador getPlayer() {
        return player;
    }

    public void setPlayer(Jugador player) {
        this.player = player;
    }

    public FrameJoc(Jugador player) {
        //iniciar jugador
        this.player = player;
        determinarFitxaJugador();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < 9; i++) {
            if (actionEvent.getSource() == buttons[i]) {
                //condició que el jugador pot escollir pot escollir i fa el listener
                if (player.isTornJugador() && player.isIniciadaPartida()) {
                    if (Objects.equals(buttons[i].getText(), "")) {
                        jugadaSeleccionada = i;
                        seleccionatCas = true;
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText(player.getFitxa());
                        player.setTornJugador(false);
                        textField.setText(CONTRARI_FITXA + " turn");
                        disableButtons();
                        check();

                    }

                } else {
                    //lociga esperarar la jugada del segon jugador
                    System.out.println("Esperem resposat Contrari");

                }
            }
        }
    }

    public void firstTurn() {
        //disable all buttons until the first has not decided

        disableButtons();
        //Esperem que el servidor ens doni inci de joc i 
        while (true) {
            if (player.isIniciadaPartida()) {
                if (player.isTornJugador()) {
                    //re-enable all buttons quan el jugador es el primer i pot jugar
                    enableButtons();
                    break;
                }

            }

        }

    }

    public void check() {
        //check X win conditions
        if ((Objects.equals(buttons[0].getText(), "X"))
                && (Objects.equals(buttons[1].getText(), "X"))
                && (Objects.equals(buttons[2].getText(), "X"))) {
            xWins(0, 1, 2);
        } else if ((Objects.equals(buttons[3].getText(), "X"))
                && (Objects.equals(buttons[4].getText(), "X"))
                && (Objects.equals(buttons[5].getText(), "X"))) {
            xWins(3, 4, 5);
        } else if ((Objects.equals(buttons[6].getText(), "X"))
                && (Objects.equals(buttons[7].getText(), "X"))
                && (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(6, 7, 8);
        } else if ((Objects.equals(buttons[0].getText(), "X"))
                && (Objects.equals(buttons[3].getText(), "X"))
                && (Objects.equals(buttons[6].getText(), "X"))) {
            xWins(0, 3, 6);
        } else if ((Objects.equals(buttons[1].getText(), "X"))
                && (Objects.equals(buttons[4].getText(), "X"))
                && (Objects.equals(buttons[7].getText(), "X"))) {
            xWins(1, 4, 7);
        } else if ((Objects.equals(buttons[2].getText(), "X"))
                && (Objects.equals(buttons[5].getText(), "X"))
                && (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(2, 5, 8);
        } else if ((Objects.equals(buttons[0].getText(), "X"))
                && (Objects.equals(buttons[4].getText(), "X"))
                && (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(0, 4, 8);
        } else if ((Objects.equals(buttons[2].getText(), "X"))
                && (Objects.equals(buttons[4].getText(), "X"))
                && (Objects.equals(buttons[6].getText(), "X"))) {
            xWins(2, 4, 6);
        } //check O win conditions
        else if ((Objects.equals(buttons[0].getText(), "O"))
                && (Objects.equals(buttons[1].getText(), "O"))
                && (Objects.equals(buttons[2].getText(), "O"))) {
            oWins(0, 1, 2);
        } else if ((Objects.equals(buttons[3].getText(), "O"))
                && (Objects.equals(buttons[4].getText(), "O"))
                && (Objects.equals(buttons[5].getText(), "O"))) {
            oWins(3, 4, 5);
        } else if ((Objects.equals(buttons[6].getText(), "O"))
                && (Objects.equals(buttons[7].getText(), "O"))
                && (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(6, 7, 8);
        } else if ((Objects.equals(buttons[0].getText(), "O"))
                && (Objects.equals(buttons[3].getText(), "O"))
                && (Objects.equals(buttons[6].getText(), "O"))) {
            oWins(0, 3, 6);
        } else if ((Objects.equals(buttons[1].getText(), "O"))
                && (Objects.equals(buttons[4].getText(), "O"))
                && (Objects.equals(buttons[7].getText(), "O"))) {
            oWins(1, 4, 7);
        } else if ((Objects.equals(buttons[2].getText(), "O"))
                && (Objects.equals(buttons[5].getText(), "O"))
                && (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(2, 5, 8);
        } else if ((Objects.equals(buttons[0].getText(), "O"))
                && (Objects.equals(buttons[4].getText(), "O"))
                && (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(0, 4, 8);
            System.out.println("Prova");
        } else if ((Objects.equals(buttons[2].getText(), "O"))
                && (Objects.equals(buttons[4].getText(), "O"))
                && (Objects.equals(buttons[6].getText(), "O"))) {
            oWins(2, 4, 6);
        } else if ((!Objects.equals(buttons[0].getText(), ""))
                && (!Objects.equals(buttons[1].getText(), ""))
                && (!Objects.equals(buttons[2].getText(), ""))
                && (!Objects.equals(buttons[3].getText(), ""))
                && (!Objects.equals(buttons[4].getText(), ""))
                && (!Objects.equals(buttons[5].getText(), ""))
                && (!Objects.equals(buttons[6].getText(), ""))
                && (!Objects.equals(buttons[7].getText(), ""))
                && (!Objects.equals(buttons[8].getText(), ""))) {
            draw();
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        disableButtons();
        textField.setText("X wins!");
        finalPartida = true;
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        disableButtons();
        textField.setText("O wins!");
        finalPartida = true;
    }

    public void draw() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackground(Color.CYAN);
        }

        disableButtons();
        textField.setText("Draw!");
        finalPartida = true;
    }

    public void enableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    private void determinarFitxaJugador() {
        //quan jugador ha de canviar de fitxa
        String aux = "";
        if (!player.getFitxa().equals(JUGADOR_FITXA)) {
            aux = JUGADOR_FITXA;
            JUGADOR_FITXA = player.getFitxa();
            CONTRARI_FITXA = aux;

        }
    }

    public void rebreJugadaSeleccionada(int jugadaRebuda) {

        System.out.println("jugadaRebuda " + jugadaRebuda);
        buttons[jugadaRebuda].setForeground(new Color(255, 0, 0));
        buttons[jugadaRebuda].setText(CONTRARI_FITXA);
        player.setTornJugador(true);
        textField.setText(JUGADOR_FITXA + " turn");
        enableButtons();
        check();
    }

    public boolean hanEscullitJugada() {
        return seleccionatCas;
    }
    public int enviarJugadaSeleccionada(){
        return jugadaSeleccionada;
    }

}
