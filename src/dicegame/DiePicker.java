package dicegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiePicker {
    //Instance Variables
    //Creates text field for number entry
    private JTextField dieEnter;
    private JTextField sideEnter;
   //Creates main window frame
    private JFrame frame1;
    //Created enter button
    private JButton enterButton;
    //Creates prompt to enter number of dice
    private JLabel diePrompt;
    private JLabel sidePrompt;
    private JScrollPane textScroll;
    private JScrollPane sideScroll;
    private JPanel buttonPanel;
    private JPanel textPanel;
    private int sideCount;
    private JCheckBox unicodeCheck;
    public DiePicker() {
        //Constructs all UI features
        dieEnter = new JTextField();
        frame1 = new JFrame();
        enterButton = new JButton();
        diePrompt = new JLabel();
        sidePrompt = new JLabel();
        buttonPanel = new JPanel();
        sideEnter = new JTextField();
        sideScroll = new JScrollPane(sideEnter);
        textScroll = new JScrollPane(dieEnter);
        textPanel = new JPanel(new WrapLayout());
        unicodeCheck = new JCheckBox("Enable Unicode",true);
        sideCount = 6;
    }
    //Second constructor for opening window with existing values
    public DiePicker(boolean persUni, int sideCount, int persDice ) {
        //Constructs all UI features
        dieEnter = new JTextField();
        frame1 = new JFrame();
        enterButton = new JButton();
        diePrompt = new JLabel();
        sidePrompt = new JLabel();
        buttonPanel = new JPanel();
        sideEnter = new JTextField();
        sideScroll = new JScrollPane(sideEnter);
        textScroll = new JScrollPane(dieEnter);
        textPanel = new JPanel(new WrapLayout());
        unicodeCheck = new JCheckBox("Enable Unicode",persUni);
        this.sideCount = sideCount;
        sideEnter.setText(Integer.toString(sideCount));
        dieEnter.setText(Integer.toString(persDice));
    }
    public void runGUI() {
        //Adds button to panel
        buttonPanel.add(enterButton);
        textPanel.add(diePrompt);
        textPanel.add(textScroll);
        textPanel.add(sidePrompt);
        textPanel.add(sideScroll);
        textPanel.add(unicodeCheck);
        //Stops frame from being resized
        frame1.setResizable(false);
        //Sets up layout, puts prompt on top, text field in middle (with scroll bar), and button panel on bottom
        frame1.getContentPane().add(BorderLayout.NORTH, textPanel);
        frame1.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
        //Sets preferred size, stops packing from removing space
        frame1.setPreferredSize(new Dimension(200, 220));
        buttonPanel.setPreferredSize(new Dimension(100,40));
        textScroll.setPreferredSize(new Dimension(190,35));
        sideScroll.setPreferredSize(new Dimension(190,35));
        //Aligns everything in the middle
        dieEnter.setHorizontalAlignment(JTextField.CENTER);
        diePrompt.setHorizontalAlignment(JLabel.CENTER);
        sidePrompt.setHorizontalAlignment(JLabel.CENTER);
        sideEnter.setHorizontalAlignment(JTextField.CENTER);
        //Stops program when window is closed
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Sets title to little dice unicode character, not long enough for full Dice Roll title
        frame1.setTitle("Die Roller");
        //Sets prompt text
        diePrompt.setText("How many dice?");
        sidePrompt.setText("How many sides?");
        enterButton.setText("Enter");
        //Packs frame together, centers it, and makes it visible
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        //Creates ActionListener for enter button, uses ButtonListener class
        enterButton.addActionListener(new DiePicker.ButtonListener());
    }
    //Creates ButtonListener class to run for each button
    class ButtonListener implements ActionListener {
        //Nothing required in constructor
        ButtonListener() {
        }
        //Passes any action event to this method
        public void actionPerformed(ActionEvent e) {
            //Sees if action command is from Enter button, not really required, adds flexibility
            if (e.getActionCommand().equals("Enter")) {
                //Checks if number/string is valid (above 0, integer)
                if ((("" + Integer.valueOf(dieEnter.getText())).equals(dieEnter.getText()) & Integer.valueOf(dieEnter.getText()) > 0) && (("" + Integer.valueOf(sideEnter.getText())).equals(sideEnter.getText()) & Integer.valueOf(sideEnter.getText()) > 0)) {
                    sideCount = Integer.valueOf(sideEnter.getText());
                    //Creates die array based on number supplied by user
                    Die dice[] = new Die[Integer.valueOf(dieEnter.getText())];
                    //Constructs each part of die array,
                    for (int x = 0; x <  dice.length; x++) {
                        //Gives each die 6 sides, supplies a seed offset to avoid the same number being generated
                        dice[x] = new Die(sideCount,x);
                    }
                    //Creates and constructs main die GUI
                    SwingGUI swingGUI = new SwingGUI(dice,sideCount, unicodeCheck.isSelected());
                    //Allows this frame to be thrown away and cleaned up
                    frame1.dispose();
                    //Runs main GUI
                    swingGUI.runGUI();
                }
                else {
                    //Clarifies prompt if input isn't clean
                    if (Integer.valueOf(dieEnter.getText()) < 1) {
                        diePrompt.setText("How many dice? AT LEAST ONE");
                    }
                    if (Integer.valueOf(sideEnter.getText()) < 3) {
                        sidePrompt.setText("How many sides? AT LEAST ONE");
                    }
                }
            }
        }
    }
}