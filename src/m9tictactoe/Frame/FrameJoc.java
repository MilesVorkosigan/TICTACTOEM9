package m9tictactoe.Frame;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;
import m9tictactoe.Jugador;

public class FrameJoc extends JPanel implements ActionListener {
    
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    Jugador player;
    String JUGADOR_FITXA ="X";
    String CONTRARI_FITXA ="O";

    
    
    public FrameJoc() {
        //iniciar jugador
        iniciarJugador();
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
                if (player.isTornJugador()&&player.isIniciadaPartida()) {
                    if (Objects.equals(buttons[i].getText(), "")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText(JUGADOR_FITXA);
                        player.setTornJugador(false);
                        textField.setText(JUGADOR_FITXA+" turn");
                        disableButtons();
                        check();
                    }
                    //fem que no pugui seleccionar cap butó fins esperar resposta
                    
                    //esperarRespostaContrari();
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
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        //re-enable all buttons
        enableButtons();
        

    }
    
    public void check() {
        //check X win conditions
        if ((Objects.equals(buttons[0].getText(), "X")) &&
                (Objects.equals(buttons[1].getText(), "X")) &&
                (Objects.equals(buttons[2].getText(), "X"))) {
            xWins(0, 1, 2);
        } else if ((Objects.equals(buttons[3].getText(), "X")) &&
                (Objects.equals(buttons[4].getText(), "X")) &&
                (Objects.equals(buttons[5].getText(), "X"))) {
            xWins(3, 4, 5);
        } else if ((Objects.equals(buttons[6].getText(), "X")) &&
                (Objects.equals(buttons[7].getText(), "X")) &&
                (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(6, 7, 8);
        } else if ((Objects.equals(buttons[0].getText(), "X")) &&
                (Objects.equals(buttons[3].getText(), "X")) &&
                (Objects.equals(buttons[6].getText(), "X"))) {
            xWins(0, 3, 6);
        } else if ((Objects.equals(buttons[1].getText(), "X")) &&
                (Objects.equals(buttons[4].getText(), "X")) &&
                (Objects.equals(buttons[7].getText(), "X"))) {
            xWins(1, 4, 7);
        } else if ((Objects.equals(buttons[2].getText(), "X")) &&
                (Objects.equals(buttons[5].getText(), "X")) &&
                (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(2, 5, 8);
        } else if ((Objects.equals(buttons[0].getText(), "X")) &&
                (Objects.equals(buttons[4].getText(), "X")) &&
                (Objects.equals(buttons[8].getText(), "X"))) {
            xWins(0, 4, 8);
        } else if ((Objects.equals(buttons[2].getText(), "X")) &&
                (Objects.equals(buttons[4].getText(), "X")) &&
                (Objects.equals(buttons[6].getText(), "X"))) {
            xWins(2, 4, 6);
        }
        
        //check O win conditions
        else if ((Objects.equals(buttons[0].getText(), "O")) &&
                (Objects.equals(buttons[1].getText(), "O")) &&
                (Objects.equals(buttons[2].getText(), "O"))) {
            oWins(0, 1, 2);
        } else if ((Objects.equals(buttons[3].getText(), "O")) &&
                (Objects.equals(buttons[4].getText(), "O")) &&
                (Objects.equals(buttons[5].getText(), "O"))) {
            oWins(3, 4, 5);
        } else if ((Objects.equals(buttons[6].getText(), "O")) &&
                (Objects.equals(buttons[7].getText(), "O")) &&
                (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(6, 7, 8);
        } else if ((Objects.equals(buttons[0].getText(), "O")) &&
                (Objects.equals(buttons[3].getText(), "O")) &&
                (Objects.equals(buttons[6].getText(), "O"))) {
            oWins(0, 3, 6);
        } else if ((Objects.equals(buttons[1].getText(), "O")) &&
                (Objects.equals(buttons[4].getText(), "O")) &&
                (Objects.equals(buttons[7].getText(), "O"))) {
            oWins(1, 4, 7);
        } else if ((Objects.equals(buttons[2].getText(), "O")) &&
                (Objects.equals(buttons[5].getText(), "O")) &&
                (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(2, 5, 8);
        } else if ((Objects.equals(buttons[0].getText(), "O")) &&
                (Objects.equals(buttons[4].getText(), "O")) &&
                (Objects.equals(buttons[8].getText(), "O"))) {
            oWins(0, 4, 8);
            System.out.println("Prova");
        } else if ((Objects.equals(buttons[2].getText(), "O")) &&
                (Objects.equals(buttons[4].getText(), "O")) &&
                (Objects.equals(buttons[6].getText(), "O"))) {
            oWins(2, 4, 6);
        } else if((!Objects.equals(buttons[0].getText(), "")) &&
                (!Objects.equals(buttons[1].getText(), "")) &&
                (!Objects.equals(buttons[2].getText(), "")) &&
                (!Objects.equals(buttons[3].getText(), "")) &&
                (!Objects.equals(buttons[4].getText(), "")) &&
                (!Objects.equals(buttons[5].getText(), "")) &&
                (!Objects.equals(buttons[6].getText(), "")) &&
                (!Objects.equals(buttons[7].getText(), "")) &&
                (!Objects.equals(buttons[8].getText(), ""))){
            draw();
        }
    }
    
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        disableButtons();
        textField.setText("X wins!");
    }
    
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        
        disableButtons();
        textField.setText("O wins!");
    }
    
    public void draw() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackground(Color.CYAN);
        }
        
        disableButtons();
        textField.setText("Draw!");
    }
    
    public void enableButtons() {
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(true);
        }
    }
    
    public void disableButtons() {
        for(int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }

    private void iniciarJugador() {
        //dades que ens donara el servidor determinarem quin jugador te de començar en casa
        // ens arriba iniciada partida el jugador  Primer és el contrari en canvi esparem listener
        player= new Jugador(true, true, -1, JUGADOR_FITXA);
         determinarFitxaJugador();
        //
        while (!player.isIniciadaPartida()) {            
            //Esperar el servidor el segon jugador
        }
        if (!player.isTornJugador()) {
            //logica en cas de que no siguis el primer el primer es el primer moviment el contrari
            buttons[player.getJugadaSeleccionada()].setText(CONTRARI_FITXA);
            //farem un check per fer el rol de canvis de torns
            check();
        }
       
    }

    private void determinarFitxaJugador() {
        //quan jugador ha de canviar de fitxa
        String aux ="";
        if (!player.getFitxa().equals(JUGADOR_FITXA)) {
            aux =JUGADOR_FITXA;
            JUGADOR_FITXA=player.getFitxa();
            CONTRARI_FITXA= aux;
            
            
        }
    }
}