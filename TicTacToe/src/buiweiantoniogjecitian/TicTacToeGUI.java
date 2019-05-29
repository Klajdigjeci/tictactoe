package buiweiantoniogjecitian;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class extends the JFrame class and contains a menu that grants the user
 * access to three JFrames; one to play the game, one to review the records of
 * the game, and one that allows the user to change some settings.
 *
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/11/2016 References: -
 * https://examples.javacodegeeks.com/desktop-java/awt/event/windowlistener-example/
 */
public class TicTacToeGUI extends JFrame {

    //Fields
    private TicTacToeOptionsObject currentSettings;
    private TicTacToeRecordsCollection recordsCollection;
    private JPanel pnlControls;
    private JButton btnPlay;
    private JButton btnOptions;
    private JButton btnRecord;
    private JButton btnQuit;
    private TicTacToeRecordsGUI record;
    private TicTacToeOptionsGUI options;
    private TicTacToeGameGUI gameBoard;
    private ActionListener closeGame;
    private ActionListener closeRecords;
    private ActionListener closeOptions;

    /**
     * This is the constructor for the TicTacToeGUI object and uses different
     * methods to add components and instantiate necessary variables that will
     * be used in later parts of the program.
     *
     * @param title The name of the JFrame
     */
    public TicTacToeGUI(String title) {
        super(title);
        recordsCollection = new TicTacToeRecordsCollection(); //Creates a variable that reads from the serializable file
        if (recordsCollection.getRecord().size() > 0) { //Sets the current settings to the last settings applied by the user
            currentSettings = recordsCollection.getRecord().get(recordsCollection.getRecord().size() - 1).getOptions();
        } else { //Sets the current settings to the default settings 
            currentSettings = new TicTacToeOptionsObject();
        }
        addComponents();
        addEventHandlers();
        initializeDisplay();
    }

    /**
     * This method adds all of the components of the menu, including a panel
     * with a logo, and 4 buttons.
     */
    public void addComponents() {
        //Main panel
        pnlControls = new JPanel();
        pnlControls.setOpaque(true);
        pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
        //Used for spacing purposes
        JLabel lblSpace0 = new JLabel(" ");
        JLabel lblSpace1 = new JLabel(" ");
        JLabel lblSpace2 = new JLabel(" ");
        JLabel lblSpace3 = new JLabel(" ");
        JLabel lblSpace4 = new JLabel(" ");
        //JPanel to display the logo, an image
        JPanel pnlLogo = new JPanel();
        ImageIcon picture = new ImageIcon(this.getClass().getResource("/buiweiantoniogjecitian/Images/TicTacToe.png"));
        JLabel lblLogo = new JLabel();
        lblLogo.setIcon(picture);
        pnlLogo.add(lblLogo, BorderLayout.CENTER);
        pnlControls.add(pnlLogo);
        lblLogo.setHorizontalAlignment(JLabel.CENTER);
        pnlControls.add(lblSpace0);
        //Play button
        btnPlay = new JButton("Play");
        btnPlay.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pnlControls.add(btnPlay);
        pnlControls.add(lblSpace1);
        //Records button
        btnRecord = new JButton("Records");
        btnRecord.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pnlControls.add(btnRecord);
        pnlControls.add(lblSpace2);
        //Options button
        btnOptions = new JButton("Options");
        btnOptions.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pnlControls.add(btnOptions);
        pnlControls.add(lblSpace3);
        //Quit button
        btnQuit = new JButton("Quit");
        btnQuit.setAlignmentX(JButton.CENTER_ALIGNMENT);
        pnlControls.add(btnQuit);
        pnlControls.add(lblSpace4);
        add(pnlControls, BorderLayout.SOUTH);
    }

    /**
     * This method initializes the display so that the background color is
     * correctly set upon starting the program.
     */
    private void initializeDisplay() {
        if (recordsCollection.getRecord().size() > 0) {
            if (currentSettings.getBackgroundColor().equals("Default")) {
                pnlControls.setBackground(null);
            } else if (currentSettings.getBackgroundColor().equals("Orange")) {
                pnlControls.setBackground(Color.ORANGE);
            } else if (currentSettings.getBackgroundColor().equals("Green")) {
                pnlControls.setBackground(Color.GREEN);
            } else if (currentSettings.getBackgroundColor().equals("Blue")) {
                pnlControls.setBackground(Color.BLUE);
            } else if (currentSettings.getBackgroundColor().equals("Pink")) {
                pnlControls.setBackground(Color.PINK);
            } else if (currentSettings.getBackgroundColor().equals("Grey")) {
                pnlControls.setBackground(Color.GRAY);
            } else if (currentSettings.getBackgroundColor().equals("White")) {
                pnlControls.setBackground(Color.WHITE);
            }
        }
    }

    /**
     * This method adds all of the event handlers for the different possible
     * events that could occur in the GUI.
     */
    private void addEventHandlers() {
        //Play event handler
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameBoard = new TicTacToeGameGUI("Tic-Tac-Toe!", currentSettings, recordsCollection);
                gameBoard.setLocation(100, 100);
                gameBoard.setSize(600, 600);
                gameBoard.setVisible(true);
                gameBoard.addWindowListener(checkGame);
                setVisible(false);
            }
        });
        //Records event handler
        btnRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                record = new TicTacToeRecordsGUI("Record", currentSettings, recordsCollection);
                record.setLocation(100, 50);
                record.setSize(800, 600);
                record.setVisible(true);
                record.addWindowListener(checkRecords);
                setVisible(false);
            }
        });
        //Options event handler
        btnOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                options = new TicTacToeOptionsGUI("Tic-Tac-Toe options", currentSettings);
                options.setLocation(100, 100);
                options.setSize(400, 400);
                options.setVisible(true);
                options.addWindowListener(checkOptions);
                setVisible(false);
            }
        });
        //Quit event handler
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    /**
     * This method checks to see whether the game has closed and if it has, it
     * saves any and all completed games played between when it was opened and
     * when it was closed.
     */
    WindowListener checkGame = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            closeGame = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    recordsCollection = new TicTacToeRecordsCollection(); //Saves the games played
                    setVisible(true);
                    gameBoard.dispose();
                }
            };
            //Timer to check whether the game has been closed or not
            Timer timer = new Timer(500, closeGame); //fire every half second
            timer.setRepeats(false);
            timer.start();
        }
    ;
    };
    
    /**
     * Checks to see if the records have been closed and if it has, it 
     * overwrites the current record to make sure that the records are up to 
     * date with any changes made within the JFrame.
     */
    WindowListener checkRecords = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            closeRecords = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    recordsCollection = new TicTacToeRecordsCollection(); //Saves if the collection changes
                    currentSettings = record.getCurrentSettings();
                    initializeDisplay();
                    record.dispose();
                }
            };
            //Timer to check whether the records have been closed
            Timer timer = new Timer(500, closeRecords); //fire every half second
            timer.setRepeats(false);
            timer.start();
        }
    ;
    };
    
    /**
     * This method checks to see whether the options have been closed or not. 
     * If they've been closed, any options that the user applied will be saved 
     * to the currentSettings object in this class.
     */
    WindowListener checkOptions = new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            closeOptions = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                    currentSettings = options.getCurrentSettings(); //Saves the object if the settings were applied
                    initializeDisplay();
                    options.dispose();
                }
            };
            //Checks to see if the options have been closed
            Timer timer = new Timer(500, closeOptions); //fire every half second
            timer.setRepeats(false);
            timer.start();
        }
    ;
};
}
