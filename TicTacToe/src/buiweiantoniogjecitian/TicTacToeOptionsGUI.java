package buiweiantoniogjecitian;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * This class extends JFrame that shows the options and allows the user to
 * change an options object.
 *
 * @author Matthew Bui
 * @author Andrew Wei
 * @version 06/10/2016 
 * References:
 *  - http://stackoverflow.com/questions/4308401/change-the-size-of-a-jtextfield-inside-a-jborderlayout
 *  - https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
 */
public class TicTacToeOptionsGUI extends JFrame {

    //Fields
    private TicTacToeOptionsObject currentSettings;
    private JPanel pnlAll;
    private JButton btnApply;
    private JButton btnIcon1;
    private JButton btnIcon2;
    private JButton btnIcon3;
    private JButton btnIcon4;
    private JButton btnIcon5;
    private JButton btnIcon6;
    private JButton btnIcon7;
    private JButton btnIcon8;
    private String currentlySelectedIconOne;
    private String currentlySelectedIconTwo;
    private JTextField txtPlayerOneName;
    private JTextField txtPlayerTwoName;
    private ArrayList<JRadioButton> rdbColors;

    /**
     * This method creates a new JFrame and calls methods to set up the options
     * Frame and allow interactions.
     *
     * @param title the title of the JFrame
     * @param currentSettings the settings of the program.
     */
    public TicTacToeOptionsGUI(String title, TicTacToeOptionsObject currentSettings) {
        super(title);
        this.currentSettings = currentSettings;
        currentlySelectedIconOne = this.currentSettings.getIconOne();
        currentlySelectedIconTwo = this.currentSettings.getIconTwo();
        addComponents();
        addEventHandlers();
        initializeDisplay();
    }

    /**
     * This method adds various components to the JFrame.
     */
    private void addComponents() {
        //Create new panel
        JPanel pnlControls = new JPanel();
        pnlControls.setOpaque(true);
        pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
        JPanel pnlPlayerOne = new JPanel();
        pnlPlayerOne.setOpaque(true);
        JLabel lblPlayerOne = new JLabel("       First player: ");
        //Ask for names
        txtPlayerOneName = new JTextField();
        txtPlayerOneName.setPreferredSize(new Dimension(200, 24));
        pnlPlayerOne.add(lblPlayerOne);
        pnlPlayerOne.add(txtPlayerOneName);
        pnlControls.add(pnlPlayerOne);
        JPanel pnlPlayerTwo = new JPanel();
        pnlPlayerOne.setOpaque(true);
        JLabel lblPlayerTwo = new JLabel("Second player: ");
        txtPlayerTwoName = new JTextField();
        txtPlayerTwoName.setPreferredSize(new Dimension(200, 24));
        pnlPlayerTwo.add(lblPlayerTwo);
        pnlPlayerTwo.add(txtPlayerTwoName);
        pnlControls.add(pnlPlayerTwo);
        //Allow the user to pick a icon for each player
        JPanel pnlIconPlayer1 = new JPanel();
        pnlIconPlayer1.setOpaque(true);
        pnlIconPlayer1.setLayout(new GridLayout(2, 2));
        JPanel pnlIconPlayer2 = new JPanel();
        pnlIconPlayer2.setLayout(new GridLayout(2, 2));
        pnlIconPlayer2.setOpaque(true);
        btnIcon1 = new JButton("O");
        btnIcon2 = new JButton("X");
        btnIcon3 = new JButton("Δ");
        btnIcon4 = new JButton("Ω");
        final int PLAYER_ONE = 1;
        checkSelected(PLAYER_ONE);
        JLabel lblPlayer1 = new JLabel("Choose an Icon for Player 1");
        pnlIconPlayer1.add(btnIcon1);
        pnlIconPlayer1.add(btnIcon2);
        pnlIconPlayer1.add(btnIcon3);
        pnlIconPlayer1.add(btnIcon4);
        btnIcon5 = new JButton("O");
        btnIcon6 = new JButton("X");
        btnIcon7 = new JButton("Δ");
        btnIcon8 = new JButton("Ω");
        final int PLAYER_TWO = 2;
        checkSelected(PLAYER_TWO);
        btnApply = new JButton("Apply");
        btnApply.setAlignmentX(CENTER_ALIGNMENT);
        JLabel lblPlayer2 = new JLabel("Choose an Icon for Player 2");
        pnlIconPlayer2.add(btnIcon5);
        pnlIconPlayer2.add(btnIcon6);
        pnlIconPlayer2.add(btnIcon7);
        pnlIconPlayer2.add(btnIcon8);
        //Allow the user to change the color of the background
        JRadioButton rdbColor1 = new JRadioButton("Default");
        JRadioButton rdbColor2 = new JRadioButton("Orange");
        JRadioButton rdbColor3 = new JRadioButton("Green");
        JRadioButton rdbColor4 = new JRadioButton("Blue");
        JRadioButton rdbColor5 = new JRadioButton("Pink");
        JRadioButton rdbColor6 = new JRadioButton("Grey");
        JRadioButton rdbColor7 = new JRadioButton("White");
        JLabel lblcolor = new JLabel();
        lblcolor.setText("Set Color");
        JPanel pnlColor = new JPanel();
        pnlColor.setOpaque(true);
        pnlColor.setLayout(new FlowLayout());
        pnlColor.add(lblcolor);
        rdbColors = new ArrayList<>();
        rdbColors.add(rdbColor1);
        rdbColors.add(rdbColor2);
        rdbColors.add(rdbColor3);
        rdbColors.add(rdbColor4);
        rdbColors.add(rdbColor5);
        rdbColors.add(rdbColor6);
        rdbColors.add(rdbColor7);
        ButtonGroup grpRadio = new ButtonGroup();
        //Add it all to a group so only one radio button can be selected
        for (int i = 0; i < 7; i++) {
            pnlColor.add(rdbColors.get(i));
            grpRadio.add(rdbColors.get(i));
        }
        //Add all the conponents into the frame
        pnlAll = new JPanel();
        pnlAll.setOpaque(true);
        pnlAll.setLayout(new BoxLayout(pnlAll, BoxLayout.Y_AXIS));
        pnlAll.add(pnlControls);
        pnlAll.add(lblPlayer1);
        pnlAll.add(pnlIconPlayer1);
        pnlAll.add(lblPlayer2);
        pnlAll.add(pnlIconPlayer2);
        pnlAll.add(pnlColor);
        pnlAll.add(btnApply);
        add(pnlAll);
    }

    /**
     * This method allows the user to get the changed settings as a settings
     * object.
     *
     * @return The new settings
     */
    public TicTacToeOptionsObject getCurrentSettings() {
        return currentSettings;
    }

    /**
     * This method checks to see whether the selected icon matches the currently
     * selected icon and if so, disables it so that the user can no longer click
     * it.
     *
     * @param player Whether it's for player 1 or player 2
     */
    public void checkSelected(int player) {
        if (player == 1) {
            if (btnIcon1.getText().equals(currentlySelectedIconOne)) {
                btnIcon1.setEnabled(false);
            }
            if (btnIcon2.getText().equals(currentlySelectedIconOne)) {
                btnIcon2.setEnabled(false);
            }
            if (btnIcon3.getText().equals(currentlySelectedIconOne)) {
                btnIcon3.setEnabled(false);
            }
            if (btnIcon4.getText().equals(currentlySelectedIconOne)) {
                btnIcon4.setEnabled(false);
            }
        } else if (player == 2) {
            if (btnIcon5.getText().equals(currentlySelectedIconTwo)) {
                btnIcon5.setEnabled(false);
            }
            if (btnIcon6.getText().equals(currentlySelectedIconTwo)) {
                btnIcon6.setEnabled(false);
            }
            if (btnIcon7.getText().equals(currentlySelectedIconTwo)) {
                btnIcon7.setEnabled(false);
            }
            if (btnIcon8.getText().equals(currentlySelectedIconTwo)) {
                btnIcon8.setEnabled(false);
            }
        }
    }

    /**
     * This method sets all of the event handlers in the program when a button
     * is pressed.
     */
    private void addEventHandlers() {
        final int PLAYER_ONE = 1;
        final int PLAYER_TWO = 2;
        //Changes the icon for player 1 to an "O"
        btnIcon1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon2.setEnabled(true);
                btnIcon3.setEnabled(true);
                btnIcon4.setEnabled(true);
                if (btnIcon1.getText().equals(currentlySelectedIconTwo)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon is already selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_ONE);
                } else {
                    currentlySelectedIconOne = "O";
                    btnIcon1.setEnabled(false);
                }
            }
        });
        //Changes the icon for player 1 to an "X"
        btnIcon2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon1.setEnabled(true);
                btnIcon3.setEnabled(true);
                btnIcon4.setEnabled(true);
                if (btnIcon2.getText().equals(currentlySelectedIconTwo)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon is already selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_ONE);
                } else {
                    btnIcon2.setEnabled(false);
                    currentlySelectedIconOne = "X";
                }
            }
        });
        //Changes the icon for player 1 to an "Δ"
        btnIcon3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon1.setEnabled(true);
                btnIcon2.setEnabled(true);
                btnIcon4.setEnabled(true);

                if (btnIcon3.getText().equals(currentlySelectedIconTwo)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon has already been selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_ONE);
                } else {
                    btnIcon3.setEnabled(false);
                    currentlySelectedIconOne = "Δ";
                }
            }
        });
        //Changes the icon for player 1 to an "Ω"
        btnIcon4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon1.setEnabled(true);
                btnIcon2.setEnabled(true);
                btnIcon3.setEnabled(true);
                if (btnIcon4.getText().equals(currentlySelectedIconTwo)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon is already selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_ONE);
                } else {
                    btnIcon4.setEnabled(false);
                    currentlySelectedIconOne = "Ω";
                }
            }
        });
        //Changes the icon for player 2 to an "O"
        btnIcon5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon6.setEnabled(true);
                btnIcon7.setEnabled(true);
                btnIcon8.setEnabled(true);
                if (btnIcon5.getText().equals(currentlySelectedIconOne)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon is already selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_TWO);
                } else {
                    btnIcon5.setEnabled(false);
                    currentlySelectedIconTwo = "O";
                }
            }
        });
        //Changes the icon for player 2 to an "X"
        btnIcon6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon5.setEnabled(true);
                btnIcon7.setEnabled(true);
                btnIcon8.setEnabled(true);
                if (btnIcon6.getText().equals(currentlySelectedIconOne)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon has already been selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_TWO);
                } else {
                    btnIcon6.setEnabled(false);
                    currentlySelectedIconTwo = "X";
                }
            }
        });
        //Changes the icon for player 2 to an "Δ"
        btnIcon7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon5.setEnabled(true);
                btnIcon6.setEnabled(true);
                btnIcon8.setEnabled(true);
                if (btnIcon7.getText().equals(currentlySelectedIconOne)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon has already been selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_TWO);
                } else {
                    btnIcon7.setEnabled(false);
                    currentlySelectedIconTwo = "Δ";
                }
            }
        });
        //Changes the icon for player 2 to an "Ω"
        btnIcon8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIcon5.setEnabled(true);
                btnIcon6.setEnabled(true);
                btnIcon7.setEnabled(true);
                if (btnIcon8.getText().equals(currentlySelectedIconOne)) { //Occurs when the player selects the icon already selected by the other player
                    JOptionPane.showMessageDialog(TicTacToeOptionsGUI.this, "This icon has already been selected by the other player and cannot be selected!");
                    checkSelected(PLAYER_TWO);
                } else {
                    btnIcon8.setEnabled(false);
                    currentlySelectedIconTwo = "Ω";
                }
            }
        });
        //Saves all of the current settings to the options object.
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedColor = "";
                for (int i = 0; i < 7; i++) { //Saves the selected background color
                    if (rdbColors.get(i).isSelected()) {
                        selectedColor = rdbColors.get(i).getText();
                    }
                }
                currentSettings.setBackgroundColor(selectedColor);
                if (selectedColor.equals("Default")) { //Sets the background to display the saved color
                    pnlAll.setBackground(null);
                } else if (selectedColor.equals("Orange")) {
                    pnlAll.setBackground(Color.ORANGE);
                } else if (selectedColor.equals("Green")) {
                    pnlAll.setBackground(Color.GREEN);
                } else if (selectedColor.equals("Blue")) {
                    pnlAll.setBackground(Color.BLUE);
                } else if (selectedColor.equals("Pink")) {
                    pnlAll.setBackground(Color.PINK);
                } else if (selectedColor.equals("Grey")) {
                    pnlAll.setBackground(Color.GRAY);
                } else if (selectedColor.equals("White")) {
                    pnlAll.setBackground(Color.WHITE);
                }
                //Saves the names of each player
                currentSettings.setPlayerOne(txtPlayerOneName.getText());
                currentSettings.setPlayerTwo(txtPlayerTwoName.getText());
                //Saves the icons selected by each player
                currentSettings.setIconOne(currentlySelectedIconOne);
                currentSettings.setIconTwo(currentlySelectedIconTwo);
                JOptionPane.showMessageDialog(getParent(), "Warning: Settings are only saved after playing a game!");
            }
        }
        );
    }

    /**
     * This method changes the frame to initialize a display.
     */
    private void initializeDisplay() {
        try {
            txtPlayerOneName.setText(currentSettings.getPlayerOne());
        } catch (NullPointerException npe) {
            txtPlayerOneName.setText("Player 1");
        }
        try {
            txtPlayerTwoName.setText(currentSettings.getPlayerTwo());
        } catch (NullPointerException npe) {
            txtPlayerTwoName.setText("Player 2");
        }
        for (int i = 0; i < rdbColors.size(); i++) {
            if (rdbColors.get(i).getText().equals(currentSettings.getBackgroundColor())) {
                rdbColors.get(i).setSelected(true);
            }
            if (currentSettings.getBackgroundColor().equals("Default")) {
                pnlAll.setBackground(null);
            } else if (currentSettings.getBackgroundColor().equals("Orange")) {
                pnlAll.setBackground(Color.ORANGE);
            } else if (currentSettings.getBackgroundColor().equals("Green")) {
                pnlAll.setBackground(Color.GREEN);
            } else if (currentSettings.getBackgroundColor().equals("Blue")) {
                pnlAll.setBackground(Color.BLUE);
            } else if (currentSettings.getBackgroundColor().equals("Pink")) {
                pnlAll.setBackground(Color.PINK);
            } else if (currentSettings.getBackgroundColor().equals("Grey")) {
                pnlAll.setBackground(Color.GRAY);
            } else if (currentSettings.getBackgroundColor().equals("White")) {
                pnlAll.setBackground(Color.WHITE);
            }
        }
    }
}
