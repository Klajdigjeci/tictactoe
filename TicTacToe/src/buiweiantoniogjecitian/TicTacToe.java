package buiweiantoniogjecitian;

/**
 * This program is a full GUI program that uses several extended JFrames and
 * several items to create an operating Tic-Tac-Toe program that persists data
 * from completed games and the options previously set by the user.
 *
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/11/2016
 */
public class TicTacToe {

    /**
     * This main method instantiates the first TicTacToeGUI object, which
     * extends the JFrame and acts as the main menu for the Tic-Tac-Toe program
     * that allows the user to access the other functions of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicTacToeGUI menu = new TicTacToeGUI("Tic-Tac-Toe!");
        menu.setLocation(400, 200);
        menu.setDefaultCloseOperation(menu.EXIT_ON_CLOSE);
        menu.pack();
        menu.setVisible(true);
    }

}
