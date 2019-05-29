package buiweiantoniogjecitian;

import java.io.Serializable;

/**
 * This class handles the getters and setters for what options were chosen for
 * the players name, icon and background color as well as a default constructor
 * that has default settings.
 *
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/11/2016
 */
public class TicTacToeOptionsObject implements Serializable {

    //Fields
    private String playerOne;
    private String playerTwo;
    private String backgroundColor;
    private String iconOne;
    private String iconTwo;

    /**
     * Default constructor for options object, which saves the default settings
     * for each of the manipulable settings.
     */
    public TicTacToeOptionsObject() {
        playerOne = "Player 1";
        playerTwo = "Player 2";
        backgroundColor = "Default";
        iconOne = "O";
        iconTwo = "X";
    }

    /**
     * Gets the player name chosen by player 1.
     *
     * @return player 1's name
     */
    public String getPlayerOne() {
        return playerOne;
    }

    /**
     * Gets the payer name chosen for player 2.
     *
     * @return player 2's name
     */
    public String getPlayerTwo() {
        return playerTwo;
    }

    /**
     * Gets the background chosen for the application.
     *
     * @return the background color
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Gets icon chosen by player 1.
     *
     * @return player 1's icon
     */
    public String getIconOne() {
        return iconOne;
    }

    /**
     * Gets icon chosen by player 2.
     *
     * @return player 2's icon
     */
    public String getIconTwo() {
        return iconTwo;
    }

    /**
     * Saves the first player's name.
     *
     * @param playerOne player 1's name
     */
    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    /**
     * Saves the second player's name.
     *
     * @param playerTwo player 2's name
     */
    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    /**
     * Sets background color that was chosen.
     *
     * @param backgroundColor The chosen background color
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Sets icon that was chosen for player 1.
     *
     * @param iconOne The chosen icon for player 1
     */
    public void setIconOne(String iconOne) {
        this.iconOne = iconOne;
    }

    /**
     * Sets icon that was chosen for player 2.
     *
     * @param iconTwo The chosen icon for player 2
     */
    public void setIconTwo(String iconTwo) {
        this.iconTwo = iconTwo;
    }

}
