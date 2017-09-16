package dicegame;

import javax.swing.*;
import java.awt.*;

public class AboutWindow {
    //Instance variables for main frame and for about text
    private JLabel aboutText;
    private JFrame frame1;
    private int[] sideStat;
    private double[] calculatedStat;
    private int totalRolls;
    private JLabel[] statLabels;
    private JPanel stats;
    private JScrollPane statScroll;
    //Constructs about text and frame
    public AboutWindow(int[] sideStat, int totalRolls) {
        aboutText = new JLabel();
        frame1 = new JFrame();
        this.sideStat = sideStat;
        calculatedStat = new double[sideStat.length];
        this.totalRolls = totalRolls;
        statLabels = new JLabel[sideStat.length];
        stats = new JPanel(new WrapLayout());
        statScroll = new JScrollPane(stats);
    }
    //Runs about GUI
    public void runGUI() {
        //Runs through stat array
        for (int z = 0; z < sideStat.length; z++) {
            //Calculates percentages
            calculatedStat[z] = ((double)sideStat[z] / totalRolls) * 100;
            statLabels[z] = new JLabel();
            statLabels[z].setPreferredSize(new Dimension(150,10));
            statLabels[z].setText(Integer.toString(z + 1) + ". " + Double.toString(calculatedStat[z]) + "%");
            //Adds all stat labels
            stats.add(statLabels[z]);
        }
        //Sets font size to 20
        aboutText.setFont(new Font(aboutText.getFont().getName(), Font.PLAIN, 20));
        //Sets preferred size to 300x150 to avoid compaction from pack
        frame1.setPreferredSize(new Dimension(300, 400));
        statScroll.setPreferredSize(new Dimension(100, 250));
        //Stops resizing, fixed content
        frame1.setResizable(false);
        //Sets all text for about window, aligns it in the middle. HTML is used for line breaks.
        aboutText.setText("<html>Written by Levi Pinkard<br>Written on 09/04/17<br>My First Swing Program!<br>Side Stats:</html>");
        aboutText.setHorizontalAlignment(JTextField.CENTER);
        //Sets window title
        frame1.setTitle("About");
        //Puts about text in center of window, taking all space needed
        frame1.getContentPane().add(BorderLayout.CENTER, aboutText);
        frame1.getContentPane().add(BorderLayout.SOUTH, statScroll);
        //Packs window together, centers it
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        //Makes itself visible
        frame1.setVisible(true);
    }
}