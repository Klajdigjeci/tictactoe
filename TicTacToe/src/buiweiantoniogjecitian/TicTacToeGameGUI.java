package buiweiantoniogjecitian;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class sets up the games gui, keeps track of the wins, and handles the
 * games buttons to change according which player's turn it is.
 *
 * @author Max Tian
 * @author Klajdi Gjeci
 * @version 06/11/2016 
 * References:
 *  - https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html
 */
class TicTacToeGameGUI extends JFrame {

    //Fields
    private TicTacToeRecordsCollection record;
    private TicTacToeGameInfoObject currentGame;
    private TicTacToeOptionsObject currentSettings;
    private TicTacToeGame game;
    private JButton btnMark;
    private JPanel pnlMarks;
    private JPanel pnlPlayer;
    private JLabel lblScore1;
    private JLabel lblScore2;
    private int score1;
    private int score2;
    private JButton[][] btnMarks;
    private int step;
    private int winner;
    private JLabel turn;

    /**
     * Creates the constructor and calls the methods to set up the games gui.
     *
     * @param title The name of the JFrame
     */
    public TicTacToeGameGUI(String title, TicTacToeOptionsObject currentSettings, TicTacToeRecordsCollection record) {
        super(title);
        this.currentSettings = currentSettings;
        this.record = record;
        score1 = this.record.getPlayerOneWins();
        score2 = this.record.getPlayerTwoWins();
        if (record.getPlayerTwoWins() < record.getPlayerOneWins()) { //Gives the player that's behind the first move
            step = 2;
            turn = new JLabel("It's currently " + currentSettings.getPlayerTwo() + "'s turn");
        } else {
            step = 1;
            turn = new JLabel("It's currently " + currentSettings.getPlayerOne() + "'s turn");
        };
        addComponents();
        inititailzeDisplay();
        addEventHandlers();
        game = new TicTacToeGame();
    }

    /**
     * Sets up the gui for the tic tac toe game.
     */
    public void addComponents() {
        pnlMarks = new JPanel();
        pnlMarks.setOpaque(true);
        pnlPlayer = new JPanel();
        pnlPlayer.setOpaque(true);
        pnlPlayer.setLayout(new BorderLayout());
        JLabel lblPlayer1 = new JLabel(currentSettings.getPlayerOne() + ": ");
        lblPlayer1.setForeground(Color.BLUE);
        pnlPlayer.add(lblPlayer1, BorderLayout.WEST);
        JLabel lblPlayer2 = new JLabel(currentSettings.getPlayerTwo() + ": ");
        lblPlayer2.setForeground(Color.RED);
        pnlPlayer.add(lblPlayer2, BorderLayout.EAST);
        pnlPlayer.add(turn, BorderLayout.CENTER);
        turn.setHorizontalAlignment(JLabel.CENTER);
        pnlMarks.setLayout(new GridLayout(3, 3));
        btnMarks = new JButton[3][3];
        for (int i = 0; i < 3; i++) { //Fills up the marks panel with buttons
            for (int j = 0; j < 3; j++) {
                btnMark = new JButton();
                btnMark.setFont(new Font("Arial", Font.BOLD, 100));
                pnlMarks.add(btnMark);
                btnMarks[i][j] = btnMark;
            }
        }
        add(pnlMarks);
        add(pnlPlayer, BorderLayout.NORTH);
        lblScore1 = new JLabel("Score: " + score1);
        lblScore1.setOpaque(true);
        lblScore1.setVerticalAlignment(JLabel.NORTH);
        add(lblScore1, BorderLayout.WEST);
        lblScore2 = new JLabel("Score: " + score2);
        lblScore2.setOpaque(true);
        lblScore2.setVerticalAlignment(JLabel.NORTH);
        add(lblScore2, BorderLayout.EAST);
        JPanel quit = new JPanel();
        quit.setLayout(new BorderLayout());
        add(quit, BorderLayout.SOUTH);
    }

    /**
     * Keeps track of the number of wins each player makes or if the game
     * resulted in a tie. Also asks if they wish to play again.
     */
    public void updateWins() {
        final int NOT_ZERO_OR_ONE = 5;
        int reply = NOT_ZERO_OR_ONE;
        final int PLAYER_ONE_WINS = 1;
        final int PLAYER_TWO_WINS = 2;
        final int TIE = -1;
        if (winner == PLAYER_ONE_WINS || winner == PLAYER_TWO_WINS || winner == TIE) { //Saves the game once the game is completed
            currentGame = new TicTacToeGameInfoObject();
            currentGame.setOptions(currentSettings);
            currentGame.setGame(game);
            record.add(currentGame);
        }
        if (winner == PLAYER_ONE_WINS) { //Prompts the user if they want to play again after the first player wins
            score1 += 1;
            lblScore1.setText("Score: " + score1);
            reply = JOptionPane.showConfirmDialog(
                    null,
                    currentSettings.getPlayerOne() + " won! Do you want to play another game?",
                    "Tic-Tac-Toe",
                    JOptionPane.YES_NO_OPTION);
            step = 2;
            turn.setText("It's currently " + currentSettings.getPlayerTwo() + "'s turn");
        } else if (winner == PLAYER_TWO_WINS) { //Prompts the user if they want to play again after the second player wins
            score2 += 1;
            lblScore2.setText("Score: " + score2);
            reply = JOptionPane.showConfirmDialog(
                    null,
                    currentSettings.getPlayerTwo() + " won! Do you want to play another game?",
                    "Tic-Tac-Toe",
                    JOptionPane.YES_NO_OPTION);
            step = 1;
            turn.setText("It's currently " + currentSettings.getPlayerOne() + "'s turn");
        } else if (winner == TIE) { //Prompts the user if they want to play again after tying
            reply = JOptionPane.showConfirmDialog(
                    null,
                    "It's a tie! Do you want to play another game?",
                    "Tic-Tac-Toe",
                    JOptionPane.YES_NO_OPTION);
        }
        if (reply == JOptionPane.YES_OPTION) { //Resets the game board if they said yes
            pnlMarks.removeAll();
            for (int i = 0; i < 3; i++) { //Recreates the buttons
                for (int j = 0; j < 3; j++) {
                    btnMark = new JButton();
                    btnMark.setFont(new Font("Arial", Font.BOLD, 100));
                    pnlMarks.add(btnMark);
                    btnMarks[i][j] = btnMark;
                }
            }
            pnlMarks.updateUI();
            addEventHandlers();
            game = new TicTacToeGame();
        } else if (reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION) { //The buttons become disabled and the user has to close the window to continue
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    btnMarks[i][j].setEnabled(false);
                }
            }
        }
        winner = 0;
    }

    /**
     * This method handles the buttons and changes the the image based on which
     * players turn it is. It also displays who was the winner and clears the
     * buttons for a potential next game.
     */
    public void addEventHandlers() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int TRACK_ROW = i;
                final int TRACK_COLUMN = j;
                JButton btn = btnMarks[i][j];
                btnMarks[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int trackRow = TRACK_ROW;
                        int trackColumn = TRACK_COLUMN;
                        if (step % 2 != 0) { //Sets the background and text to the mark for player 1
                            btn.setBackground(Color.BLUE);
                            btn.setText(currentSettings.getIconOne());
                            game.receiveInput(1, trackRow, trackColumn); //Mark for player 1
                            turn.setText("It's currently " + currentSettings.getPlayerTwo() + "'s turn");
                        } else { //Sets the background and text to the mark for player 2
                            btn.setBackground(Color.RED);
                            btn.setText(currentSettings.getIconTwo());
                            game.receiveInput(2, trackRow, trackColumn); //Mark for player 2
                            turn.setText("It's currently " + currentSettings.getPlayerOne() + "'s turn");
                        }
                        final int PLAYER_ONE_WINS = 1;
                        final int PLAYER_TWO_WINS = 2;
                        final int TIE = -1;
                        if (game.getResult() == PLAYER_ONE_WINS) { //If player 1 wins
                            winner = 1;
                        } else if (game.getResult() == PLAYER_TWO_WINS) { //If player 2 wins
                            winner = 2;
                        } else if (game.getResult() == TIE) { //If the game is a tie
                            winner = -1;
                        };
                        btn.setEnabled(false);
                        step++;
                        updateWins();
                    }
                });
            }
        }
    }

    /**
     * This method initializes the display so that the background color is
     * correctly set upon starting the program.
     */
    private void inititailzeDisplay() {
        if (currentSettings.getBackgroundColor().equals("Default")) {
            lblScore1.setBackground(null);
            lblScore2.setBackground(null);
            pnlPlayer.setBackground(null);
            pnlMarks.setBackground(null);
        } else if (currentSettings.getBackgroundColor().equals("Orange")) {
            lblScore1.setBackground(Color.ORANGE);
            lblScore2.setBackground(Color.ORANGE);
            pnlPlayer.setBackground(Color.ORANGE);
            pnlMarks.setBackground(Color.ORANGE);
        } else if (currentSettings.getBackgroundColor().equals("Green")) {
            lblScore1.setBackground(Color.GREEN);
            lblScore2.setBackground(Color.GREEN);
            pnlPlayer.setBackground(Color.GREEN);
            pnlMarks.setBackground(Color.GREEN);
        } else if (currentSettings.getBackgroundColor().equals("Blue")) {
            lblScore1.setBackground(Color.BLUE);
            lblScore2.setBackground(Color.BLUE);
            pnlPlayer.setBackground(Color.BLUE);
            pnlMarks.setBackground(Color.BLUE);
        } else if (currentSettings.getBackgroundColor().equals("Pink")) {
            lblScore1.setBackground(Color.PINK);
            lblScore2.setBackground(Color.PINK);
            pnlPlayer.setBackground(Color.PINK);
            pnlMarks.setBackground(Color.PINK);
        } else if (currentSettings.getBackgroundColor().equals("Grey")) {
            lblScore1.setBackground(Color.GRAY);
            lblScore2.setBackground(Color.GRAY);
            pnlPlayer.setBackground(Color.GRAY);
            pnlMarks.setBackground(Color.GRAY);
        } else if (currentSettings.getBackgroundColor().equals("White")) {
            lblScore1.setBackground(Color.WHITE);
            lblScore2.setBackground(Color.WHITE);
            pnlPlayer.setBackground(Color.WHITE);
            pnlMarks.setBackground(Color.WHITE);
        }
    }
}
