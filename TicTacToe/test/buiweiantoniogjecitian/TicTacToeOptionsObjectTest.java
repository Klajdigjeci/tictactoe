package buiweiantoniogjecitian;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the test class for the options object.
 *
 * @author Armando Antonio
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/14/2016
 */
public class TicTacToeOptionsObjectTest {

    //Fields
    TicTacToeOptionsObject oo1;

    //Set up before each test
    @Before
    public void setUp() {
        oo1 = new TicTacToeOptionsObject();
    }

    /**
     * Test of getPlayerOne method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testGetPlayerOne() {
        System.out.println("testing getPlayerOne...");
        assertTrue(oo1.getPlayerOne().equals("Player 1"));
        System.out.println("getPlayerOne passed");
    }

    /**
     * Test of getPlayerTwo method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testGetPlayerTwo() {
        System.out.println("testing getPlayerTwo...");
        assertTrue(oo1.getPlayerTwo().equals("Player 2"));        
        System.out.println("getPlayerTwo passed");
    }

    /**
     * Test of getBackgroundColor method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testGetBackgroundColor() {
        System.out.println("testing getBackgroundColor...");
        assertTrue(oo1.getBackgroundColor().equals("Default"));
        System.out.println("getBackgroundColor passed");
    }

    /**
     * Test of getIconOne method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testGetIconOne() {
        System.out.println("testing getIconOne...");
        assertTrue(oo1.getIconOne().equals("O"));
        System.out.println("getIconOne passed");
    }

    /**
     * Test of getIconTwo method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testGetIconTwo() {
        System.out.println("testing getIconTwo...");
        assertTrue(oo1.getIconTwo().equals("X"));
        System.out.println("getIconTwo passed");
    }

    /**
     * Test of setPlayerOne method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testSetPlayerOne() {
        System.out.println("testing setPlayerOne...");
        oo1.setPlayerOne("P1");
        assertTrue(oo1.getPlayerOne().equals("P1"));
        System.out.println("setPlayerOne passed");
    }

    /**
     * Test of setPlayerTwo method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testSetPlayerTwo() {
        System.out.println("testing setPlayerTwo...");
        oo1.setPlayerTwo("P2");
        assertTrue(oo1.getPlayerTwo().equals("P2"));
        System.out.println("setPlayerTwo passed");
    }

    /**
     * Test of setBackgroundColor method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testSetBackgroundColor() {
        System.out.println("testing setBackgroundColor...");
        oo1.setBackgroundColor("Black");
        assertTrue(oo1.getBackgroundColor().equals("Black"));
        System.out.println("setBackgroundColor passed");
    }

    /**
     * Test of setIconOne method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testSetIconOne() {
        System.out.println("testing setIconOne...");
        oo1.setIconOne("V");
        assertTrue(oo1.getIconOne().equals("V"));
        System.out.println("setIconOne passed");
    }

    /**
     * Test of setIconTwo method, of class TicTacToeOptionsObject.
     */
    @Test
    public void testSetIconTwo() {
        System.out.println("testing setIconTwo...");
        oo1.setIconTwo("T");
        assertTrue(oo1.getIconTwo().equals("T"));
        System.out.println("setIconTwo passed");
    }

}
