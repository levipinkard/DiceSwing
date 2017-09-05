package dicegame;

import javax.swing.*;
import java.awt.*;

public class AboutWindow {
    //Instance variables for main frame and for about text
    private JLabel aboutText;
    private JFrame frame1;
    //Constructs about text and frame
    public AboutWindow() {
        aboutText = new JLabel();
        frame1 = new JFrame();
    }
    //Runs about GUI
    public void runGUI() {
        //Sets font size to 20
        aboutText.setFont(new Font(aboutText.getFont().getName(), Font.PLAIN, 20));
        //Sets preferred size to 300x150 to avoid compaction from pack
        frame1.setPreferredSize(new Dimension(300, 150));
        //Stops resizing, fixed content
        frame1.setResizable(false);
        //Sets all text for about window, aligns it in the middle. HTML is used for line breaks.
        aboutText.setText("<html>Written by Levi Pinkard<br>Written on 09/04/17<br>My First Swing Program!</html>");
        aboutText.setHorizontalAlignment(JTextField.CENTER);
        //Sets window title
        frame1.setTitle("About");
        //Puts about text in center of window, taking all space needed
        frame1.getContentPane().add(BorderLayout.CENTER, aboutText);
        //Packs window together, centers it
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        //Makes itself visible
        frame1.setVisible(true);
    }
}