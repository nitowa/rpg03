package GameLogic.State.UI;

import java.awt.*;

import javax.swing.*;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.Unit;

public class BadWindowLog extends Log {

    private JTextPane jTextPane;
    private JFrame jFrame;


    public BadWindowLog(){

        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(1200, 800);

        JPanel jPanel = new JPanel(new BorderLayout());
        jTextPane = new JTextPane();

        JScrollPane jScrollPane = new JScrollPane(jTextPane);
        jScrollPane.setVisible(true);
        //scrollPane = new ScrollPane();

        jPanel.add(jScrollPane);
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.createBufferStrategy(3);
    }

    @Override
    public void unitSay(Unit u, String s) {

    }

    @Override
    public void unitSay(Unit u, String s, int delay) {

    }

    @Override
    public void print(String toPrint) {

    }

    @Override
    public void delayPrint(String toPrint, int delay) {

    }

    @Override
    public void delayPrintln(String toPrint, int delay) {

    }

    @Override
    public void println(String toPrint) {

    }

    @Override
    public void slowPrint(String toPrint) {

    }

    @Override
    public void slowPrintln(String toPrint) {

    }

    @Override
    public void slowerPrintln(String toPrint) {

    }

    @Override
    public void printColored(String toPrint, Color c) {

    }

    @Override
    public void printlnColored(String toPrint, Color c) {

    }

    @Override
    public void slowPrintColored(String toPrint, Color c) {

    }

    @Override
    public void slowerPrintColored(String toPrint, Color c) {

    }

    @Override
    public void printItemColored(Item i) {

    }

    @Override
    public void printlnItemColored(Item i) {

    }

    @Override
    public void slowPrintItemColored(Item i) {

    }

    @Override
    public void slowerPrintItemColored(Item i) {

    }

    @Override
    public String read() {
        return null;
    }
}
