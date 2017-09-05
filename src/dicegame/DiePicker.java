package dicegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiePicker {
    //Instance Variables
    //Creates text field for number entry
    private JTextField dieEnter;
   //Creates main window frame
    private JFrame frame1;
    //Created enter button
    private JButton enterButton;
    //Creates prompt to enter number of dice
    private JLabel diePrompt;

    public DiePicker() {
        //Constructs all UI features
        dieEnter = new JTextField();
        frame1 = new JFrame();
        enterButton = new JButton();
        diePrompt = new JLabel();
    }

    public void runGUI() {
        //Stops frame from being resized
        frame1.setResizable(false);
        //Sets up layout, puts prompt on top, text field in middle, and enter on bottom
        frame1.getContentPane().add(BorderLayout.NORTH,diePrompt);
        frame1.getContentPane().add(BorderLayout.CENTER, dieEnter);
        frame1.getContentPane().add(BorderLayout.SOUTH, enterButton);
        //Sets preferred size, stops packing from removing space
        frame1.setPreferredSize(new Dimension(200, 120));
        dieEnter.setHorizontalAlignment(JTextField.CENTER);
        //Stops program when window is closed
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Sets title to little dice unicode character, not long enough for full Dice Roll title
        frame1.setTitle(new String(Character.toChars(127922)));
        //Sets prompt text
        diePrompt.setText("How many dice?");
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
                if (("" + Integer.valueOf(dieEnter.getText())).equals(dieEnter.getText()) & Integer.valueOf(dieEnter.getText()) > 0) {
                    //Creates die array based on number supplied by user
                    Die dice[] = new Die[Integer.valueOf(dieEnter.getText())];
                    //Constructs each part of die array,
                    for (int x = 0; x <  dice.length; x++) {
                        //Gives each die 6 sides, supplies a seed offset to avoid the same number being generated
                        dice[x] = new Die(6,x);
                    }
                    //Creates and constructs main die GUI
                    SwingGUI swingGUI = new SwingGUI(dice);
                    //Allows this frame to be thrown away and cleaned up
                    frame1.dispose();
                    //Runs main GUI
                    swingGUI.runGUI();
                }
                else {
                    //Clarifies prompt if input isn't clean
                    diePrompt.setText("How many dice? AT LEAST ONE");
                }
            }
        }
    }
}