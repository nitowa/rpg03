package GameLogic.UI;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.Unit;

public abstract class Log {
    protected List<String> log = new LinkedList<>();
    protected List<String> inputLog = new ArrayList<>();

    public void printAscii(String file){
        String asAscii = Img2Ascii.asAscii(file);
        printSize(asAscii, 2);
    }

    public abstract void printSize(String s, int fontSize);
    public abstract void unitSay(Unit u, String s);
    public abstract void unitSay(Unit u, String s, int delay);
    public abstract void print(String toPrint);
    public abstract void delayPrint(String toPrint, int delay);
    public abstract void delayPrintln(String toPrint, int delay);
    public abstract void println(String toPrint);
    public abstract void slowPrint(String toPrint);
    public abstract void slowPrintln(String toPrint);
    public abstract void slowerPrintln(String toPrint);
    public abstract void printColored(String toPrint, Color c);
    public abstract void printlnColored(String toPrint, Color c);
    public abstract void slowPrintColored(String toPrint, Color c);
    public abstract void slowerPrintColored(String toPrint, Color c);
    public abstract void slowPrintlnColored(String toPrint, Color c);
    public abstract void printItemColored(Item i);
    public abstract void slowPrintlnItemColored(Item i);
    public abstract void slowerPrintlnItemColored(Item i);
    public abstract void printlnItemColored(Item i);
    public abstract void colorLastInput(Color c);
    public abstract String read();
}
