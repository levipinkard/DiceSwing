package dicegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SwingGUI {
    //Creates instance variable for die array
    private Die[] dice;
    //Creates instance variables for preferred window height and width
    private int width;
    private int height;
    //Creates instance variables for window Components
    private JLabel[] dieDraw;
    private JFrame frame1;
    private JPanel diePanel;
    private JButton rollButton;
    private JScrollPane scrollPane;
    private JButton aboutButt;
    private JPanel buttons;
    private JButton backButt;
    //Constructor, is passed Die array from DiePicker class
    public SwingGUI(Die[] dieArray) {
        //Sets default values for width and height
        width = 600;
        height  = 400;
        //Sets this classes die object to the one given
        dice = dieArray;
        //Constructs all components
        frame1 = new JFrame();
        //Uses WrapLayout to help with vertical scrolling
        diePanel = new JPanel(new WrapLayout());
        rollButton = new JButton();
        //Creates a JLabel array the same size as the Die array
        dieDraw = new JLabel[dice.length];
        //Creates scrollPane with diePanel component
        scrollPane = new JScrollPane(diePanel);
        buttons = new JPanel();
        aboutButt = new JButton();
        backButt = new JButton();
    }
    //Method to actually run GUI
    public void runGUI() {
        //Sets Component and frame preferred sizes before pack() method is ran, to preserve space
        rollButton.setPreferredSize(new Dimension(100,50));
        frame1.setPreferredSize(new Dimension(width, height));
        //Stops program when window is closed
        frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Sets title to Dice Roll and Dice emoji/Unicode
        frame1.setTitle("Dice Roll " + new String(Character.toChars(127922)));
        //Sets button text
        rollButton.setText("Roll");
        aboutButt.setText("About");
        backButt.setText("Back");
        //Adds buttons to button panel in order
        buttons.add(backButt);
        buttons.add(rollButton);
        buttons.add(aboutButt);
        //Adds buttons frame to bottom of frame
        frame1.add(BorderLayout.SOUTH, buttons);
        for (int y = 0; y < dieDraw.length; y++) {
            //Constructs all of dieDraw arrays
            dieDraw[y] = new JLabel();
            //Sets font size
            dieDraw[y].setFont(new Font(dieDraw[y].getFont().getName(), Font.PLAIN, 100));
            //Adds dieDraw label to diePanel
            diePanel.add(dieDraw[y]);
        }
        //Adds scrollPane to main frame
        frame1.getContentPane().add(BorderLayout.CENTER, scrollPane);
        //Packs frame, centers it, and makes it visible
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        //Makes action listeners for all bottom buttons
        rollButton.addActionListener(new ButtonListener());
        aboutButt.addActionListener(new ButtonListener());
        backButt.addActionListener(new ButtonListener());
    }
    //Creates ButtonListener class to run for each button
    class ButtonListener implements ActionListener {
        //Nothing required in constructor
        ButtonListener() {
        }
        //Passes any action event to this method
        public void actionPerformed(ActionEvent e) {
            //Sees what button action command is from
            if (e.getActionCommand().equals("Roll")) {
                //Runs through every die
                for (int x = 0; x < dice.length; x++){
                    //Rolls die
                    dice[x].roll(x);
                    //Sets JLabel to Unicode character for current face of die
                    int codePoint = 9855 + dice[x].getCurrentSide();
                    dieDraw[x].setText(new String(Character.toChars(codePoint)));
                }
            }
            //Launches about dialog, keeps this window open
            else if(e.getActionCommand().equals("About")){
                AboutWindow aboutGUI = new AboutWindow();
                aboutGUI.runGUI();
            }
            //If back button is hit, it creates an instance of the first dialog (DiePicker, in this case), runs it, then disposes of this window, deleting all contents
            else if(e.getActionCommand().equals("Back")){
                DiePicker pickGUI = new DiePicker();
                pickGUI.runGUI();
                frame1.dispose();
            }
        }
    }
}