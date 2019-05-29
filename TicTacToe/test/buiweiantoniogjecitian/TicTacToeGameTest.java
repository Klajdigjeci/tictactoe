package buiweiantoniogjecitian;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is the test class for the tic-tac-toe game object.
 *
 * @author Armando Antonio
 * @author Klajdi Gjeci
 * @author Andrew Wei
 * @version 06/14/2016
 */
public class TicTacToeGameTest {

    //Fields
    TicTacToeGame g1;

    //Set up before each test
    @Before
    public void setUp() {
        g1 = new TicTacToeGame();
    }

    /**
     * Test of checkWin method, of class TicTacToeGame.
     */
    @Test
    public void testCheckWin() {
        System.out.println("Testing checkWin...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g1.receiveInput(1, i, j);
            }
            assertTrue(g1.getResult() == 1);
            g1 = new TicTacToeGame();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g1.receiveInput(1, j, i);
            }
            assertTrue(g1.getResult() == 1);
            g1 = new TicTacToeGame();
        }
        for (int i = 0; i < 3; i++) {
            g1.receiveInput(1, i, i);
        }
        assertTrue(g1.getResult() == 1);
        g1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            g1.receiveInput(1, i, 2 - i);
        }
        assertTrue(g1.getResult() == 1);
        g1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g1.receiveInput(2, i, j);
            }
            assertTrue(g1.getResult() == 2);
            g1 = new TicTacToeGame();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g1.receiveInput(2, j, i);
            }
            assertTrue(g1.getResult() == 2);
            g1 = new TicTacToeGame();
        }
        for (int i = 0; i < 3; i++) {
            g1.receiveInput(2, i, i);
        }
        assertTrue(g1.getResult() == 2);
        g1 = new TicTacToeGame();
        for (int i = 0; i < 3; i++) {
            g1.receiveInput(2, i, 2 - i);
        }
        assertTrue(g1.getResult() == 2);
        g1 = new TicTacToeGame();
        //Checks the private checkIfTie method
        g1.receiveInput(1, 0, 0);
        g1.receiveInput(2, 0, 1);
        g1.receiveInput(1, 0, 2);
        g1.receiveInput(1, 1, 0);
        g1.receiveInput(2, 1, 1);
        g1.receiveInput(1, 1, 2);
        g1.receiveInput(2, 2, 0);
        g1.receiveInput(1, 2, 1);
        g1.receiveInput(2, 2, 2);
        assertTrue(g1.getResult() == -1);
        System.out.println("checkWin passed");
    }

    /**
     * Test of receiveInput method, of class TicTacToeGame.
     */
    @Test
    public void testReceiveInput() {
        System.out.println("Testing receiveInput...");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g1.receiveInput(1, i, j);
                assertTrue(g1.getCell(i, j) == 1);
                g1 = new TicTacToeGame();
                g1.receiveInput(2, i, j);
                assertTrue(g1.getCell(i, j) == 2);
            }
        }
        System.out.println("receiveInput passed");
    }
}
