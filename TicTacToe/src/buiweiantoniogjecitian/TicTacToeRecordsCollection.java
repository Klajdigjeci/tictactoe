package buiweiantoniogjecitian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class writes and reads from a flat file the number of wins by each
 * player, amount of games played and each player win ratio.
 *
 * @author Max Tian
 * @author Klajdi Gjeci
 * @version 06/11/16
 */
public class TicTacToeRecordsCollection {

    //Fields
    private ArrayList<TicTacToeGameInfoObject> record;
    private int gamesPlayed;
    private int playerOneWins;
    private int playerTwoWins;

    /**
     * Creates a new array list and checks how many times each player has won
     * and the number of ties from the records.
     */
    public TicTacToeRecordsCollection() {
        record = new ArrayList<>();
        readCollection();
        int result;
        final int PLAYER_ONE_WON = 1;
        final int PLAYER_TWO_WON = 2;
        final int TIE = -1;
        for (int i = 0; i < record.size(); i++) { //Adds each result to keep track of total games won, tied, and played
            result = record.get(i).getGame().getResult();
            if (result == PLAYER_ONE_WON) {
                gamesPlayed += 1;
                playerOneWins += 1;
            } else if (result == PLAYER_TWO_WON) {
                gamesPlayed += 1;
                playerTwoWins += 1;
            } else if (result == TIE) {
                gamesPlayed += 1;
            }
        }
    }

    /**
     * Adds an instance of a tic-tac-toe game to the records.
     *
     * @param currentGame The game being added
     */
    public void add(TicTacToeGameInfoObject currentGame) {
        record.add(currentGame);
        writeCollections();
    }

    /**
     * Gets the amount of games player 1 has won.
     *
     * @return The amount of games player 1 has won.
     */
    public int getPlayerOneWins() {
        return playerOneWins;
    }

    /**
     * Gets the amount of games player 2 has won.
     *
     * @return The amount of games player 2 has won.
     */
    public int getPlayerTwoWins() {
        return playerTwoWins;
    }

    /**
     * Deletes a game from the record list.
     *
     * @param game The game being deleted
     */
    public void delete(TicTacToeGameInfoObject game) {
        record.remove(game);
        writeCollections();
    }

    /**
     * Calculates the win ratio of player 1.
     *
     * @return p1WinRatio, the win ratio as a percent
     */
    public double calculateWinRatioP1() {
        double p1WinRatio = ((double) playerOneWins / (double) gamesPlayed);
        return p1WinRatio * 100;
    }

    /**
     * Calculates the win ratio for player 2.
     *
     * @return p2WinRatio, the win ratio as a percent
     */
    public double calculateWinRatioP2() {
        double p2WinRatio = ((double) playerTwoWins / (double) gamesPlayed);
        return p2WinRatio * 100;
    }

    /**
     * This method calculates the tie ratio for the games played.
     *
     * @return The tie ratio as a percent
     */
    public double calculateTieRatio() {
        double tieRatio = ((double) gamesPlayed - playerTwoWins - playerOneWins) / gamesPlayed;
        return tieRatio * 100;
    }

    /**
     * Gets the total number of games played.
     *
     * @return total amount of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Decrements the games played count.
     */
    public void deleteGame() {
        gamesPlayed -= 1;
    }

    /**
     * Decrements the player 1 win and the games played counts.
     */
    public void deleteP1Win() {
        playerOneWins -= 1;
        gamesPlayed -= 1;
    }

    /**
     * Decrements the player 2 win and the games played counts.
     */
    public void deleteP2Win() {
        playerTwoWins -= 1;
        gamesPlayed -= 1;
    }

    /**
     * Gets the records from the TicTacToeGameInfoObject class.
     *
     * @return records which hold wins, ties, and games played
     */
    public ArrayList<TicTacToeGameInfoObject> getRecord() {
        return record;
    }

    /**
     * Writes the record list to a flat file and stores it.
     *
     * @return Whether the process was successful
     */
    public boolean writeCollections() {
        boolean success = true;
        try (FileOutputStream fos = new FileOutputStream("currentRecords.ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(record);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n"
                    + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Reads flat file to see if a record list already exists.
     *
     * @return Whether the process was successful.
     */
    public boolean readCollection() {
        boolean success = true;
        File ser = new File("currentRecords.ser");
        if (ser.exists()) {
            try (FileInputStream fis = new FileInputStream("currentRecords.ser");
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                record = (ArrayList<TicTacToeGameInfoObject>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:/n"
                        + ex.getMessage());
                success = false;
            }
        }
        return success;
    }

    /**
     * This method reads the file and replaces the current record with the
     * imported record.
     *
     * @param importedData File from which the different records are being
     * imported
     * @param name The name of the file
     * @return Whether the operation was successful
     */
    public boolean readCollection(File importedData, String name) {
        boolean success = true;
        if (importedData.exists()) {
            try (FileInputStream fis = new FileInputStream(name);
                    ObjectInputStream input = new ObjectInputStream(fis)) {
                record = (ArrayList<TicTacToeGameInfoObject>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:/n"
                        + ex.getMessage());
                success = false;
            }
        }
        writeCollections();
        return success;
    }

    /**
     * This method creates a file of the current records with the given name.
     *
     * @param name The name of the file to be created
     * @return Whether the operation was successful
     */
    public boolean writeCollections(String name) {
        boolean success = true;
        try (FileOutputStream fos = new FileOutputStream(name + ".ser");
                ObjectOutputStream output = new ObjectOutputStream(fos)) {
            output.writeObject(record);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n"
                    + ex.getMessage());
            success = false;
        }
        return success;
    }
}
