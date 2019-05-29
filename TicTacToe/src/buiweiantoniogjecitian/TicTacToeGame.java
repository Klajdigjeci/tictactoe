package buiweiantoniogjecitian;

import java.io.Serializable;

/**
 * This class receives a 2d array and the number of wins for each player.
 * Through the 2d array it's determined if a player has won by getting 3 in a
 * row.
 *
 * @author Armando Antonio
 * @version 6/11/2016 
 * References:
 *  - http://stackoverflow.com/questions/27165006/local-variables-refered-from-inner-class-must-be-final-or-effectively-final
 */
public class TicTacToeGame implements Serializable {

    //Fields
    private int[][] game;
    private int result;

    /**
     * This is the TicTacToeGame constructor that sets up the rows and columns
     * of the Tic-Tac-Toe object that will be used to check whether a player has
     * won or not.
     */
    public TicTacToeGame() {
        final int COLUMN = 3;
        final int ROW = 3;
        game = new int[ROW][COLUMN];
        for (int i = 0; i < ROW; i++) { //Sets all array elements to zero
            for (int j = 0; j < COLUMN; j++) {
                game[i][j] = 0;
            }
        }
    }

    /**
     * This method gets the result of the game, either -1 for a tie, 0 for an
     * unfinished game (which isn't saved), 1 for player 1 winning, and 2 for
     * player 2 winning.
     *
     * @return Whether a user has won, tied, or the game hasn't been completed
     */
    public int getResult() {
        return result;
    }

    /**
     * Checks the game state for winners, ties, or if the game is still going.
     */
    public void checkWin() {
        //1 = clicked button for player 1, 0 = unclicked button
        int oneCount = 0;
        int oneWon = 0;
        //checks if player 1 has won from left to right
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 1) {
                    oneCount += 1;
                }
            }
            if (oneCount == 3) {
                oneWon += 1;
            }
            oneCount = 0;
        }
        //checks if player 1 has won from up and down
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (game[i][j] == 1) {
                    oneCount += 1;
                }
            }
            if (oneCount == 3) {
                oneWon += 1;
            }
            oneCount = 0;
        }
        //checks if player 1 has won diagnoally
        for (int i = 0; i < 3; i++) {
            if (game[i][i] == 1) {
                oneCount += 1;
            }
        }
        if (oneCount == 3) {
            oneWon += 1;
        }
        oneCount = 0;
        //checks if player 1 has won diagonally
        for (int i = 0; i < 3; i++) {
            if (game[i][2 - i] == 1) {
                oneCount += 1;
            }
        }
        if (oneCount == 3) {
            oneWon += 1;
        }

        //2 = clicked button for player 2, 0 = unclicked button
        int twoCount = 0;
        int twoWon = 0;
        //checks if player 2 has won from left to right
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 2) {
                    twoCount += 1;
                }
                if (twoCount == 3) {
                    twoWon += 1;
                }
            }
            twoCount = 0;
        }
        //checks if player 2 has from up and down
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (game[i][j] == 2) {
                    twoCount += 1;
                }
                if (twoCount == 3) {
                    twoWon += 1;
                }
            }
            twoCount = 0;
        }
        //checks if player 2 has won diagnoally
        for (int i = 0; i < 3; i++) {
            if (game[i][i] == 2) {
                twoCount += 1;
            }
        }
        if (twoCount == 3) {
            twoWon += 1;
        }
        twoCount = 0;
        //checks if player 2 has won diagonally
        for (int i = 0; i < 3; i++) {
            if (game[i][2 - i] == 2) {
                twoCount += 1;
            }
        }
        if (twoCount == 3) {
            twoWon += 1;
        }

        //Checks game state
        boolean stillPlaying = checkIfTie();
        final int PLAYER_ONE_WON = 1;
        final int PLAYER_TWO_WON = 2;
        final int TIE = -1;
        final int GAME_NOT_DONE = 0;
        if (oneWon > 0) {
            result = PLAYER_ONE_WON;
        } else if (twoWon > 0) {
            result = PLAYER_TWO_WON;
        } else if (!stillPlaying) {
            result = TIE;
        } else {
            result = GAME_NOT_DONE;
        }
    }

    /**
     * Receives the 2d array for the current state of the board after each turn
     * and marks the cell.
     *
     * @param player Which player's turn it is
     * @param row Which row the player clicked on in the GUI
     * @param column Which column the player clicked on in the GUI
     */
    public void receiveInput(int player, int row, int column) {
        game[row][column] = player;
        checkWin();
    }

    /**
     * This method gets the value at a specified element, either 0 for unfilled,
     * 1 for player 1, or 2 for player 2.
     *
     * @param row the specified row
     * @param column the specified column
     * @return which player filled the element or if the element is unfilled
     */
    public int getCell(int row, int column) {
        return game[row][column];
    }

    /**
     * This method checks to see if the game has been tied by checking to see if
     * there's any leftover cells and if there aren't and no one has won, the
     * game is tied.
     *
     * @return Whether the game is tied or not
     */
    private boolean checkIfTie() {
        int unfilledCount = 0;
        for (int i = 0; i < 3; i++) { //Checks each row and column to see if there's an element set to zero
            for (int j = 0; j < 3; j++) {
                if (game[i][j] == 0) {
                    unfilledCount += 1;
                }
            }
        }
        if (unfilledCount > 0) {
            return true;
        } else {
            return false;
        }
    }
}
