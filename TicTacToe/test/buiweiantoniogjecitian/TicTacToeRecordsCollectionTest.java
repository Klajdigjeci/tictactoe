package buiweiantoniogjecitian;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the test class for the records collection.
 *
 * @author Armando Antonio
 * @author Matthew Bui
 * @author Andrew Wei
 * @author Max Tian
 * @author Klajdi Gjeci
 * @version 06/14/2016
 */
public class TicTacToeRecordsCollectionTest {

    //Fields
    TicTacToeRecordsCollection r;

    /**
     * Sets up the records collection.
     */
    @Before
    public void setUp() {
        r = new TicTacToeRecordsCollection();
    }

    /**
     * Test the add method of the TicTacToeRecordsCollection class.
     */
    @Test
    public void testAdd() {
        System.out.println("Testing add...");
        int currentSize = r.getRecord().size();
        TicTacToeGame game3 = new TicTacToeGame();
        game3.receiveInput(1, 0, 0);
        game3.receiveInput(2, 0, 1);
        game3.receiveInput(1, 0, 2);
        game3.receiveInput(1, 1, 0);
        game3.receiveInput(2, 1, 1);
        game3.receiveInput(1, 1, 2);
        game3.receiveInput(2, 2, 0);
        game3.receiveInput(1, 2, 1);
        game3.receiveInput(2, 2, 2);
        TicTacToeGameInfoObject gio3 = new TicTacToeGameInfoObject();
        gio3.setGame(game3);
        gio3.setOptions(new TicTacToeOptionsObject());
        r.add(gio3);
        assertEquals(r.getRecord().size(), currentSize + 1);
        r.delete(gio3); //So it isn't saved to the serializable file
        System.out.println("add passed");
    }

    /**
     * Test the delete method of the TicTacToeRecordsCollection class.
     */
    public void testDelete() {
        System.out.println("Testing delete...");
        int currentSize = r.getRecord().size();
        TicTacToeGame game3 = new TicTacToeGame();
        game3.receiveInput(1, 0, 0);
        game3.receiveInput(2, 0, 1);
        game3.receiveInput(1, 0, 2);
        game3.receiveInput(1, 1, 0);
        game3.receiveInput(2, 1, 1);
        game3.receiveInput(1, 1, 2);
        game3.receiveInput(2, 2, 0);
        game3.receiveInput(1, 2, 1);
        game3.receiveInput(2, 2, 2);
        TicTacToeGameInfoObject gio3 = new TicTacToeGameInfoObject();
        gio3.setGame(game3);
        gio3.setOptions(new TicTacToeOptionsObject());
        r.add(gio3);
        assertTrue(r.getRecord().size() == currentSize + 1);
        r.delete(gio3);
        assertTrue(r.getRecord().size() == currentSize);
        System.out.println("delete passed");
    }

    /**
     * Test the calculateWinRatioP1 method of the TicTacToeRecordsCollection
     * class. Added games in case the records were empty.
     */
    @Test
    public void testCalculateWinRatioP1() {
        System.out.println("Testing calculateWinRatioP1...");
        TicTacToeGame game1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game1.receiveInput(1, 0, i);
        }
        TicTacToeGameInfoObject gio1 = new TicTacToeGameInfoObject();
        gio1.setGame(game1);
        gio1.setOptions(new TicTacToeOptionsObject());
        r.add(gio1);
        TicTacToeGame game2 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game2.receiveInput(2, 0, i);
        }
        TicTacToeGameInfoObject gio2 = new TicTacToeGameInfoObject();
        gio2.setGame(game2);
        gio2.setOptions(new TicTacToeOptionsObject());
        r.add(gio2);
        double winRatio = ((double) r.getPlayerOneWins()) / r.getGamesPlayed() * 100; //For the percentage
        assertTrue(String.format("%.2f", winRatio).equals(String.format("%.2f", r.calculateWinRatioP1())));
        r.delete(gio1);
        r.delete(gio2);
        System.out.println("calculateWinRatioP1 passed");
    }

    /**
     * Test the calculateWinRatioP2 method of the TicTacToeRecordsCollection
     * class. Added games in case the records were empty.
     */
    @Test
    public void testCalculateWinRatioP2() {
        System.out.println("Testing calculateWinRatioP2...");
        TicTacToeGame game1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game1.receiveInput(1, 0, i);
        }
        TicTacToeGameInfoObject gio1 = new TicTacToeGameInfoObject();
        gio1.setGame(game1);
        gio1.setOptions(new TicTacToeOptionsObject());
        r.add(gio1);
        TicTacToeGame game2 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game2.receiveInput(2, 0, i);
        }
        TicTacToeGameInfoObject gio2 = new TicTacToeGameInfoObject();
        gio2.setGame(game2);
        gio2.setOptions(new TicTacToeOptionsObject());
        r.add(gio2);
        double winRatio = ((double) r.getPlayerTwoWins()) / r.getGamesPlayed() * 100; //For the percentage
        assertTrue(String.format("%.2f", winRatio).equals(String.format("%.2f", r.calculateWinRatioP2())));
        r.delete(gio1);
        r.delete(gio2);
        System.out.println("calculateWinRatioP2 passed");
    }

    /**
     * Test the calculateTieRatio method of the TicTacToeRecordsCollection
     * class. Added games in case the records were empty.
     */
    @Test
    public void testCalculateTieRatio() {
        System.out.println("Testing calculateTieRatio...");
        TicTacToeGame game1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game1.receiveInput(1, 0, i);
        }
        TicTacToeGameInfoObject gio1 = new TicTacToeGameInfoObject();
        gio1.setGame(game1);
        gio1.setOptions(new TicTacToeOptionsObject());
        r.add(gio1);
        TicTacToeGame game2 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            game2.receiveInput(2, 0, i);
        }
        TicTacToeGameInfoObject gio2 = new TicTacToeGameInfoObject();
        gio2.setGame(game2);
        gio2.setOptions(new TicTacToeOptionsObject());
        r.add(gio2);
        TicTacToeGame game3 = new TicTacToeGame();
        game3.receiveInput(1, 0, 0);
        game3.receiveInput(2, 0, 1);
        game3.receiveInput(1, 0, 2);
        game3.receiveInput(1, 1, 0);
        game3.receiveInput(2, 1, 1);
        game3.receiveInput(1, 1, 2);
        game3.receiveInput(2, 2, 0);
        game3.receiveInput(1, 2, 1);
        game3.receiveInput(2, 2, 2);
        TicTacToeGameInfoObject gio3 = new TicTacToeGameInfoObject();
        gio3.setGame(game3);
        gio3.setOptions(new TicTacToeOptionsObject());
        r.add(gio3);
        double tieRatio = ((double) r.getGamesPlayed() - r.getPlayerTwoWins() - r.getPlayerOneWins()) / r.getGamesPlayed() * 100; //For the percentage
        double calculatedTieRatio = r.calculateTieRatio();
        assertTrue(String.format("%.2f", tieRatio).equals(String.format("%.2f", calculatedTieRatio)));
        r.delete(gio1);
        r.delete(gio2);
        r.delete(gio3);
        System.out.println("calculateTieRatio passed");
    }

    /**
     * Tests the deleteGame method of the TicTacToeRecordsCollection class.
     */
    @Test
    public void testDeleteGame() {
        System.out.println("Testing deleteGame...");
        r.deleteGame();
        assertTrue(r.getRecord().size() - 1 == r.getGamesPlayed());
        System.out.println("deleteGame passed");
    }

    /**
     * Tests the deleteP1Win method of the TicTacToeRecordsCollection class.
     */
    @Test
    public void testDeleteP1Win() {
        System.out.println("Testing deleteP1Win...");
        int testCount = r.getPlayerOneWins();
        r.deleteP1Win();
        assertTrue(r.getRecord().size() - 1 == r.getGamesPlayed());
        assertTrue(r.getPlayerOneWins() == testCount - 1);
        System.out.println("deleteP1Win passed");
    }

    /**
     * Tests the deleteP2Win method of the TicTacToeRecordsCollection class.
     */
    @Test
    public void testDeleteP2Win() {
        System.out.println("Testing deleteP2Win...");
        int testCount = r.getPlayerTwoWins();
        r.deleteP2Win();
        assertTrue(r.getRecord().size() - 1 == r.getGamesPlayed());
        assertTrue(testCount - 1 == r.getPlayerTwoWins());
        System.out.println("deleteP2Win passed");
    }

    /**
     * Test the write method of the TicTacToeRecordsCollection class. The
     * JUnitTest.ser is to make sure that the file gets created.
     */
    @Test
    public void testWriteCollection() {
        System.out.println("Testing writeCollection...");
        assertTrue(r.writeCollections());
        assertTrue(r.writeCollections("JUnitTest"));
        File file = new File("JUnitTest.ser");
        assertTrue(file.exists());
        System.out.println("writeCollection passed");
    }

    /**
     * Test the read method of the TicTacToeRecordsCollection class. Tests to
     * see if a file exists after being read in the set up.
     */
    @Test
    public void testReadCollection() {
        System.out.println("Testing readCollection...");
        File file = new File("currentRecords.ser");
        assertTrue(file.exists());
        File file2 = new File("Test2.ser");
        r.readCollection(file2, file2.getName());
        System.out.println("readCollection passed");
    }
}
