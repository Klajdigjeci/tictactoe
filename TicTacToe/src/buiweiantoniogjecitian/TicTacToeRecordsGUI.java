package buiweiantoniogjecitian;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class extends the JFrame and contains the TicTacToeRecordsGUI in which
 * the user may review their previous games, delete them, and import/export
 * files.
 *
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/11/2016 References:
 * - https://docs.oracle.com/javase/tutorial/uiswing/components/scrollpane.html
 * - https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
 * - https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html
 * - http://stackoverflow.com/questions/20061200/jscrollpane-not-appearing
 */
public class TicTacToeRecordsGUI extends JFrame {

    //Fields
    private TicTacToeOptionsObject currentSettings;
    private JButton btnImport;
    private JButton btnExport;
    private ArrayList<JButton> btnDeleteEntry;
    private ArrayList<JButton> btnConvertEntry;
    private TicTacToeRecordsCollection recordedGames;
    private JPanel pnlRecord;
    private JButton[][] btnMarks;
    private JButton btnMark;
    private ArrayList<JPanel> pnlGameList;
    private ArrayList<JPanel> pnlMarks;
    private ArrayList<JLabel> result;
    private JLabel lblPlayerOneStats;
    private JLabel lblPlayerTwoStats;
    private JLabel lblGeneralStats;
    private JScrollPane scpScroll;

    /**
     * This method constructs the TicTacToeRecordsGUI object, which holds an
     * instance of the recorded games and accesses different methods that adds
     * the components and sets up the functions of the program.
     *
     * @param title The name of the JFrame
     * @param currentSettings The current settings of the application
     * @param recordedGames The collection class that keeps track of all of the
     * games played
     */
    public TicTacToeRecordsGUI(String title, TicTacToeOptionsObject currentSettings, TicTacToeRecordsCollection recordedGames) {
        super(title);
        this.recordedGames = recordedGames;
        addComponents();
        addEventHandlers();
        addRepeatedEventHandlers();
        this.currentSettings = currentSettings;
        initializeDisplay();
    }

    /**
     * This method gets the records collection currently presented on the
     * records GUI.
     *
     * @return The Tic Tac Toe records collection
     */
    public TicTacToeRecordsCollection getRecordedGames() {
        return recordedGames;
    }

    /**
     * This method gets the current settings.
     *
     * @return The current settings
     */
    public TicTacToeOptionsObject getCurrentSettings() {
        return currentSettings;
    }

    /**
     * This method adds all of the components to the TicTacToeRecordsGUI JFrame.
     */
    private void addComponents() {
        //Panel for the import and export buttons
        JPanel pnlTopRecordOptions = new JPanel();
        pnlTopRecordOptions.setSize(400, 400);
        //Export button
        btnExport = new JButton("Export record");
        btnExport.setHorizontalAlignment(JButton.CENTER);
        btnExport.setSize(90, 75);
        pnlTopRecordOptions.add(btnExport, BorderLayout.NORTH);
        //Import button
        btnImport = new JButton("Import record");
        btnImport.setHorizontalAlignment(JButton.CENTER);
        btnImport.setSize(90, 75);
        pnlTopRecordOptions.add(btnImport, BorderLayout.NORTH);
        add(pnlTopRecordOptions, BorderLayout.NORTH);
        //Records panel, holds all of the games
        setUpRecords();
        scpScroll = new JScrollPane(pnlRecord); //Allows a scroller to appear when needed
        add(scpScroll);
        //Labels for game stats
        lblPlayerOneStats = new JLabel("Games played: " + recordedGames.getGamesPlayed() + "        Player 1: " + recordedGames.getPlayerOneWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP1()) + "%        ");
        lblPlayerTwoStats = new JLabel("Player 2: " + recordedGames.getPlayerTwoWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP2()) + "%        ");
        lblGeneralStats = new JLabel("Ties: " + (recordedGames.getGamesPlayed() - recordedGames.getPlayerOneWins() - recordedGames.getPlayerTwoWins()) + "/" + String.format("%.2f", (recordedGames.calculateTieRatio())) + "%");
        JPanel pnlStats = new JPanel();
        pnlStats.add(lblPlayerOneStats);
        pnlStats.add(lblPlayerTwoStats);
        pnlStats.add(lblGeneralStats);
        add(pnlStats, BorderLayout.SOUTH);
    }

    /**
     * This method initializes the display so that the background color is
     * correctly set upon starting the program.
     */
    private void initializeDisplay() {
        if (recordedGames.getRecord().size() > 0) {
            if (currentSettings.getBackgroundColor().equals("Default")) {
                pnlRecord.setBackground(null);
            } else if (currentSettings.getBackgroundColor().equals("Orange")) {
                pnlRecord.setBackground(Color.ORANGE);
            } else if (currentSettings.equals("Green")) {
                pnlRecord.setBackground(Color.GREEN);
            } else if (currentSettings.getBackgroundColor().equals("Blue")) {
                pnlRecord.setBackground(Color.BLUE);
            } else if (currentSettings.getBackgroundColor().equals("Pink")) {
                pnlRecord.setBackground(Color.PINK);
            } else if (currentSettings.getBackgroundColor().equals("Grey")) {
                pnlRecord.setBackground(Color.GRAY);
            } else if (currentSettings.getBackgroundColor().equals("White")) {
                pnlRecord.setBackground(Color.WHITE);
            }
        }
    }

    /**
     * This method sets up the records panel that displays previously played
     * games.
     */
    private void setUpRecords() {
        pnlRecord = new JPanel();
        final int GAMES_DISPLAYED_PER_ROW = 4;
        final int OFFSET = 1;
        pnlRecord.setLayout(new GridLayout(recordedGames.getGamesPlayed() / GAMES_DISPLAYED_PER_ROW + OFFSET, GAMES_DISPLAYED_PER_ROW, 5, 5));
        pnlGameList = new ArrayList<>();
        btnDeleteEntry = new ArrayList<>();
        btnConvertEntry = new ArrayList<>();
        pnlMarks = new ArrayList();
        result = new ArrayList();
        int winner = 0;
        if (recordedGames.getRecord().size() > 0) { //Occurs when there's at least one object in the recordedGames collection
            for (int i = 0; i < recordedGames.getGamesPlayed(); i++) {
                pnlGameList.add(new JPanel());
                pnlGameList.get(i).setLayout(new BoxLayout(pnlGameList.get(i), BoxLayout.Y_AXIS));
                pnlGameList.get(i).setBorder(BorderFactory.createLineBorder(Color.GRAY));
                winner = recordedGames.getRecord().get(i).getGame().getResult();
                result.add(new JLabel());
                if (winner == -1) { //Occurs when the game is tied
                    result.get(i).setText("Tie!");
                    result.get(i).setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    pnlGameList.get(i).add(result.get(i));
                } else if (winner == 1) { //Occurs when Player 1 wins
                    result.get(i).setText(recordedGames.getRecord().get(i).getOptions().getPlayerOne() + " won!");
                    result.get(i).setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    pnlGameList.get(i).add(result.get(i));
                } else if (winner == 2) { //Occurs when Player 2 wins
                    result.get(i).setText(recordedGames.getRecord().get(i).getOptions().getPlayerTwo() + " won!");
                    result.get(i).setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    pnlGameList.get(i).add(result.get(i));
                }
                pnlMarks.add(new JPanel());
                pnlMarks.get(i).setLayout(new GridLayout(3, 3));
                btnMarks = new JButton[3][3];
                for (int j = 0; j < 3; j++) { //Copies the game state
                    for (int k = 0; k < 3; k++) {
                        btnMark = new JButton();
                        btnMark.setFont(new Font("Arial", Font.BOLD, 12));
                        pnlMarks.get(i).add(btnMark);
                        btnMarks[j][k] = btnMark;
                    }
                }
                for (int j = 0; j < 3; j++) { //Changes the game state into into their representative icons and colors
                    for (int k = 0; k < 3; k++) {
                        int playerMark = recordedGames.getRecord().get(i).getGame().getCell(j, k);
                        if (playerMark == 1) {
                            btnMarks[j][k].setText(recordedGames.getRecord().get(i).getOptions().getIconOne());
                            btnMarks[j][k].setBackground(Color.BLUE);
                        } else if (playerMark == 2) {
                            btnMarks[j][k].setText(recordedGames.getRecord().get(i).getOptions().getIconTwo());
                            btnMarks[j][k].setBackground(Color.RED);
                        }
                        btnMarks[j][k].setEnabled(false);
                    }
                }
                pnlGameList.get(i).add(pnlMarks.get(i));
                //Button to delete the entry
                btnDeleteEntry.add(new JButton("Delete"));
                //Button to convert the entry
                btnConvertEntry.add(new JButton("Convert"));
                JPanel pnlConvertDelete = new JPanel();
                pnlConvertDelete.add(btnDeleteEntry.get(i));
                pnlConvertDelete.add(btnConvertEntry.get(i));
                pnlGameList.get(i).add(pnlConvertDelete);
                pnlRecord.add(pnlGameList.get(i));
            }
        }
    }

    /**
     * This method adds the repeated event handlers (delete and convert) that
     * are present on each instance of a previous game.
     */
    private void addRepeatedEventHandlers() {
        //Delete buttons
        for (int i = 0; i < btnDeleteEntry.size(); i++) { //Implements the event handler for each delete button
            final int INDEX = i;
            btnDeleteEntry.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int reply = JOptionPane.showConfirmDialog(
                            null, "Are you sure you want to delete this?",
                            "Tic-Tac-Toe",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) { //Deletes the entry and subtracts one from the related counts
                        final int PLAYER_ONE_WON = 1;
                        final int PLAYER_TWO_WON = 2;
                        final int TIE = -1;
                        int winner = recordedGames.getRecord().get(INDEX).getGame().getResult();
                        recordedGames.delete(recordedGames.getRecord().get(INDEX));
                        if (winner == PLAYER_ONE_WON) {
                            recordedGames.deleteP1Win();
                        } else if (winner == PLAYER_TWO_WON) {
                            recordedGames.deleteP2Win();
                        } else if (winner == TIE) {
                            recordedGames.deleteGame();
                        }
                        remove(scpScroll);
                        setUpRecords();
                        scpScroll = new JScrollPane(pnlRecord); //Allows a scroller to appear when needed
                        add(scpScroll);
                        addRepeatedEventHandlers();
                        initializeDisplay();
                        revalidate();
                        lblPlayerOneStats.setText("Games played: " + recordedGames.getGamesPlayed() + "        Player 1: " + recordedGames.getPlayerOneWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP1()) + "%        ");
                        lblPlayerTwoStats.setText("Player 2: " + recordedGames.getPlayerTwoWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP2()) + "%        ");
                        lblGeneralStats.setText("Ties: " + (recordedGames.getGamesPlayed() - recordedGames.getPlayerOneWins() - recordedGames.getPlayerTwoWins()) + "/" + String.format("%.2f", (recordedGames.calculateTieRatio())) + "%");
                    }
                }
            });
        }
        //Convert buttons
        for (int i = 0; i < btnConvertEntry.size(); i++) { //Implements the event handler for each convert button
            final int INDEX = i;
            btnConvertEntry.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int reply = JOptionPane.showConfirmDialog(
                            null, "Are you sure you want to convert this game to the last used setting?",
                            "Tic-Tac-Toe",
                            JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) { //Deletes the entry and subtracts one from the related counts
                        recordedGames.getRecord().get(INDEX).setOptions(currentSettings);
                        recordedGames.writeCollections();
                        remove(scpScroll);
                        setUpRecords();
                        scpScroll = new JScrollPane(pnlRecord);
                        add(scpScroll);
                        addRepeatedEventHandlers();
                        initializeDisplay();
                        revalidate(); //Necessary since an internal component isn't being added or deleted
                    }
                }
            });
        }
    }

    /**
     * This method adds all of the non-repeated event handlers associated with
     * the TicTacToeRecordsGUI JFrame.
     */
    private void addEventHandlers() {
        //Export event handler
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog("What is the name of the file you want the data to be saved as?");
                if (!(fileName.equals(null))) {
                    recordedGames.writeCollections(fileName);
                } else {
                    JOptionPane.showMessageDialog(getParent(), "Operation canceled");
                }
            }
        });
        //Import event handler
        btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(
                        null, "Importing data will overwrite current data and the settings\nfor the last game played will overwrite the current settings. \nAre you sure you want to do this?",
                        "Tic-Tac-Toe",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) { //Deletes the entry and subtracts one from the related counts
                    JFileChooser fileOpener = new JFileChooser();
                    if (e.getSource() == btnImport) {
                        int returnVal = fileOpener.showOpenDialog(TicTacToeRecordsGUI.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fileOpener.getSelectedFile();
                            recordedGames.readCollection(file, file.getName());
                            recordedGames = new TicTacToeRecordsCollection();
                            remove(scpScroll); //Nothing will appear if the serializable file is invalid
                            setUpRecords();
                            scpScroll = new JScrollPane(pnlRecord);
                            add(scpScroll);
                            addRepeatedEventHandlers();
                            revalidate();
                            currentSettings = recordedGames.getRecord().get(recordedGames.getRecord().size() - 1).getOptions();
                            initializeDisplay();
                            lblPlayerOneStats.setText("Games played: " + recordedGames.getGamesPlayed() + "        Player 1: " + recordedGames.getPlayerOneWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP1()) + "%        ");
                            lblPlayerTwoStats.setText("Player 2: " + recordedGames.getPlayerTwoWins() + " wins/" + String.format("%.2f", recordedGames.calculateWinRatioP2()) + "%        ");
                            lblGeneralStats.setText("Ties: " + (recordedGames.getGamesPlayed() - recordedGames.getPlayerOneWins() - recordedGames.getPlayerTwoWins()) + "/" + String.format("%.2f", (recordedGames.calculateTieRatio())) + "%");
                        } else {
                            JOptionPane.showMessageDialog(getParent(), "Operation canceled");
                        }
                    }
                }
            }
        }
        );
    }
}
