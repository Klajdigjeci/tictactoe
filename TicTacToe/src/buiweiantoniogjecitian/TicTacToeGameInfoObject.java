package buiweiantoniogjecitian;

import java.io.Serializable;

/**
 * This class stores game records to an array list and writes/reads to and from
 * a flat file.
 *
 * @author Armando Antonio
 * @version 6/11/16
 */
public class TicTacToeGameInfoObject implements Serializable {

    private TicTacToeGame game; //Mostly used for getting the result
    private TicTacToeOptionsObject options;

    /**
     * Default constructor for TicTacToeGameInfoObject.
     */
    public TicTacToeGameInfoObject() {
    }

    /**
     * Gets the games object from TicTacToeGame.
     *
     * @return game The instance of the Tic-Tac-Toe game
     */
    public TicTacToeGame getGame() {
        return game;
    }

    /**
     * Sets the tic tac toe game object received to the field.
     *
     * @param game The instance of the Tic-Tac-Toe game
     */
    public void setGame(TicTacToeGame game) {
        this.game = game;
    }

    /**
     * Gets the options object from TicTacToeOptions.
     *
     * @return options The instance of the options object
     */
    public TicTacToeOptionsObject getOptions() {
        return options;
    }

    /**
     * Sets the options object received to the field.
     *
     * @param options The instance of the options object.
     */
    public void setOptions(TicTacToeOptionsObject options) {
        this.options = options;
    }

}
