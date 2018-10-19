package GameLogic.UI;

import java.awt.*;
import java.util.Scanner;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.Unit;

public class ConsoleLog extends Log {

    //print speeds
    private int slow = 20;
    private int slower = 50;

    @Override
    public void printSize(String s, int fontSize) {
        print(s);
    }

    @Override
    public void unitSay(Unit u, String s) {
        print(u.getName()+": ");
        delayPrintln(s, 0);
    }

    @Override
    public void unitSay(Unit u, String s, int delay) {
        print(u.getName()+": ");
        delayPrintln(s, delay);
    }

    @Override
    public void print(String toPrint) {
        System.out.print(toPrint);
    }

    @Override
    public void delayPrint(String toPrint, int delay) {
        for(int i = 0; i < toPrint.length(); i++) {
            System.out.print(toPrint.charAt(i));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delayPrintln(String toPrint, int delay) {
        delayPrint(toPrint+"\n", delay);
    }

    @Override
    public void println(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public void slowPrint(String toPrint) {
        delayPrint(toPrint, slow);
    }

    @Override
    public void slowPrintln(String toPrint) {
        slowPrint(toPrint+"\n");
    }

    @Override
    public void slowerPrintln(String toPrint) {
        delayPrint(toPrint+"\n", slower);
    }

    @Override
    public void printColored(String toPrint, Color c) {
        print(toPrint);
    }

    @Override
    public void printlnColored(String toPrint, Color c) {
        println(toPrint);
    }

    @Override
    public void slowPrintColored(String toPrint, Color c) {
        slowPrint(toPrint);
    }

    @Override
    public void slowerPrintColored(String toPrint, Color c) {
        slowerPrintln(toPrint);
    }

    @Override
    public void slowPrintlnColored(String toPrint, Color c) {
        slowPrintln(toPrint);
    }

    @Override
    public void printItemColored(Item i) {
        print(i.toString());
    }

    @Override
    public void slowPrintlnItemColored(Item i) {
        slowPrintln(i.toString());
    }

    @Override
    public void slowerPrintlnItemColored(Item i) {
        slowerPrintln(i.toString());
    }

    @Override
    public void printlnItemColored(Item i) {
        println(i.toString());
    }

    @Override
    public void colorLastInput(Color c) {
        return;
    }

    @Override
    public String read() {
        return new Scanner(System.in).nextLine();
    }
}
