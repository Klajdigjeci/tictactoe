package buiweiantoniogjecitian;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the test class for the game info object.
 * 
 * @author Max Tian
 * @author Matthew Bui
 * @version 06/14/2016
 */
public class TicTacToeGameInfoObjectTest {

    //Fields
    TicTacToeGameInfoObject gio1;

    //Set up before each class
    @Before
    public void setUp() {
        gio1 = new TicTacToeGameInfoObject();
        TicTacToeGame gameTest = new TicTacToeGame();
        gameTest.receiveInput(1, 1, 1);
        gio1.setGame(gameTest);
        TicTacToeOptionsObject optionsTest = new TicTacToeOptionsObject();
        gio1.setOptions(optionsTest);
    }

    /**
     * Test of getGame method, of class TicTacToeGameInfoObject.
     */
    @Test
    public void testGetGame() {
        System.out.println("Testing getGame...");
        TicTacToeGame gameTest2 = new TicTacToeGame();
        gameTest2.receiveInput(1, 1, 1);
        for (int i = 0; i < 3; i++) { 
            for (int j = 0; j < 3; j++) {
                assertEquals(gameTest2.getCell(i, j), gio1.getGame().getCell(i, j));
            }
        }
        System.out.println("getGame passed");
    }

    /**
     * Test of setGame method, of class TicTacToeGameInfoObject.
     */
    @Test
    public void testSetGame() {
        System.out.println("Testing setGame...");
        TicTacToeGameInfoObject gio2 = new TicTacToeGameInfoObject();
        gio2.setGame(gio1.getGame());
        assertEquals(gio2.getGame(), gio1.getGame());
        System.out.println("setGamePassed");
    }

    /**
     * Test of getOptions method, of class TicTacToeGameInfoObject.
     */
    @Test
    public void testGetOptions() {
        System.out.println("Testing getOptions...");
        //Using the default settings for the created options object to test whether it exists
        assertTrue(gio1.getOptions().getBackgroundColor().equals("Default"));
        assertTrue(gio1.getOptions().getIconOne().equals("O"));
        assertTrue(gio1.getOptions().getIconTwo().equals("X"));
        assertTrue(gio1.getOptions().getPlayerOne().equals("Player 1"));
        assertTrue(gio1.getOptions().getPlayerTwo().equals("Player 2"));
        System.out.println("getOptions passed");
    }

    /**
     * Test of setOptions method, of class TicTacToeGameInfoObject.
     */
    @Test
    public void testSetOptions() {
        System.out.println("Testing setOptions...");
        TicTacToeGameInfoObject gio2 = new TicTacToeGameInfoObject();
        gio2.setOptions(gio1.getOptions());
        assertEquals(gio2.getOptions(), gio1.getOptions());
        System.out.println("setOptions passed");
    }

}
