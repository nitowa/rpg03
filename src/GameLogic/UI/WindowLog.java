package GameLogic.UI;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;
import java.util.concurrent.Semaphore;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.NavigationFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import GameLogic.Inventory.Items.Item;
import GameLogic.State.Unit;


public class WindowLog extends Log {

    private int shifterSize = 250;
    private int shiftDistance = 3;

    //Edit font here
    private String fontPath = "/Mono.ttf";
    private int fontSize = 18;

    //print speeds
    private int slow = 20;
    private int slower = 50;

    private JTextPane textArea;
    private JScrollPane scp;
    private Semaphore sem = new Semaphore(0);
    private int last = 0;
    private Color fontColor;
    private boolean swallowInputs = false;
    private Caret caret;
    private JFrame win;
    private int backIndex = 0;
    private JPanel panel;
    private JPanel shifter_left;
    private JPanel shifter_right;
    private int shifts = 0;
    private boolean doBlip = false;
    private boolean doScroll = true;
    private String fontName;

    public void setScroll(){
        if(textArea.getCaret().getMark() != textArea.getDocument().getLength()) {
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
        //if(scp.getHAdjustable().getValue() < scp.getHAdjustable().getMaximum())
        //    scp.setScrollPosition(0, scp.getVAdjustable().getMaximum());

    }

    public WindowLog(int maxLength, Color background, Color font){

        GraphicsEnvironment ge =
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            Font f = Font.createFont(Font.TRUETYPE_FONT, WindowLog.class.getResourceAsStream(fontPath));
            this.fontName = f.getFamily();
            System.out.println(f);
            ge.registerFont(f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        this.fontColor = font;
        win = new JFrame();
        win.getContentPane().setBackground( background );
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.setResizable(false);
        win.setSize(1900,800);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setDoubleBuffered(true);
        //panel.setSize(win.getSize());

        textArea = new JTextPane();
        textArea.setDoubleBuffered(true);
        textArea.setCursor(Cursor.getDefaultCursor());
        textArea.setBackground(background);
        textArea.setEditable(false);
        textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {}
            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(doScroll)
                    setScroll();
            }
        });
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(swallowInputs) {
                    e.consume();
                    return;
                }

                if(e.getExtendedKeyCode() == KeyEvent.VK_ENTER){
                    sem.release(1);
                    doScroll = true;
                    setScroll();
                    JukeBox.playMP3(JukeBox.TYPEWRITER_BELL);
                    unshift();
                    return;
                }

                if(e.getExtendedKeyCode() == KeyEvent.VK_UP) {
                    if (backIndex < inputLog.size()) {
                        textArea.select(last, textArea.getDocument().getLength());
                        textArea.replaceSelection("");

                        StyleContext sc = StyleContext.getDefaultStyleContext();
                        AttributeSet
                            aset =
                            sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                                            UIColors.DEFAULT_TEXT_COLOR);

                        aset = sc.addAttribute(aset, StyleConstants.FontFamily, fontName);
                        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
                        aset = sc.addAttribute(aset, StyleConstants.Size, fontSize);
                        try {
                            textArea.getDocument()
                                .insertString(textArea.getDocument().getLength(), inputLog.get(inputLog.size()-1-backIndex), aset);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        backIndex++;
                        return;
                    }
                }
                if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN){
                    if(backIndex == 0){
                        textArea.select(last, textArea.getDocument().getLength());
                        textArea.replaceSelection("");
                        return;
                    }
                    if (backIndex > 1) {
                        backIndex--;
                        textArea.select(last, textArea.getDocument().getLength());
                        textArea.replaceSelection("");

                        StyleContext sc = StyleContext.getDefaultStyleContext();
                        AttributeSet
                            aset =
                            sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground,
                                            UIColors.DEFAULT_TEXT_COLOR);

                        aset = sc.addAttribute(aset, StyleConstants.FontFamily, fontName);
                        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
                        aset = sc.addAttribute(aset, StyleConstants.Size, fontSize);
                        try {
                            textArea.getDocument()
                                .insertString(textArea.getDocument().getLength(), inputLog.get(inputLog.size()-1-backIndex), aset);
                        } catch (BadLocationException e1) {
                            e1.printStackTrace();
                        }
                        return;
                    }
                }
                backIndex = 0;
                if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                    doScroll = false;
                }

                JukeBox.playMP3(JukeBox.TYPEWRITER_TACK);
                shift();

                if(doScroll)
                    setScroll();
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        textArea.setCaretColor(UIColors.DEFAULT_TEXT_COLOR);
        this.caret = textArea.getCaret();
        setCaretVisible(false);

        scp = new ModernScrollPane(textArea);
        scp.setPreferredSize(new Dimension(1000, win.getHeight()));
        scp.setMinimumSize(new Dimension(1000, win.getHeight()));
        scp.setMaximumSize(new Dimension(1000, win.getHeight()));
        scp.setBackground(UIColors.DEFAULT_BACKGROUND_COLOR);

        shifter_left = new JPanel();
        shifter_left.setMinimumSize(new Dimension(0, win.getHeight()));
        shifter_left.setMaximumSize(new Dimension(shifterSize, win.getHeight()));
        shifter_left.setSize(new Dimension(shifterSize, win.getHeight()));
        shifter_left.setOpaque(true);
        shifter_left.setBackground(Color.black);

        shifter_right = new JPanel();
        shifter_right.setMinimumSize(new Dimension(shifterSize, win.getHeight()));
        shifter_right.setMaximumSize(new Dimension(shifterSize, win.getHeight()));
        shifter_right.setPreferredSize(new Dimension(shifterSize, win.getHeight()));
        shifter_right.setOpaque(true);
        shifter_right.setBackground(Color.black);


        panel.add(shifter_left);
        panel.add(scp);
        panel.add(shifter_right);

        win.add(panel);
        win.setVisible(true);
        win.createBufferStrategy(3);
    }

    private void resetFont(){
        appendToPane("", fontColor);
    }

    @Override
    public void printColored(String toPrint, Color c) {
        log.add(toPrint);
        appendToPane(toPrint, c);
        resetFont();
    }

    public void printlnColored(String toPrint, Color c){
        log.add(toPrint);
        appendToPane(toPrint+"\n", c);
        resetFont();
    }

    @Override
    public void slowerPrintColored(String toPrint, Color c) {
        log.add(toPrint);
        delayPrintColored(toPrint , c,slower);
        resetFont();
    }

    @Override
    public void slowPrintColored(String toPrint, Color c) {
        log.add(toPrint);
        delayPrintColored(toPrint , c,slow);
        resetFont();
    }

    @Override
    public void slowPrintlnColored(String toPrint, Color c) {
        log.add(toPrint);
        delayPrintColored(toPrint + "\n", c,slow);
        resetFont();
    }

    public void slowerPrintlnColored(String toPrint, Color c) {
        log.add(toPrint);
        delayPrintColored(toPrint + "\n", c, slower);
        resetFont();
    }

    @Override
    public void printItemColored(Item i) {
        printColored(""+i, i.getColor());
    }

    @Override
    public void printlnItemColored(Item i) {
        printlnColored(""+i,i.getColor());
    }

    @Override
    public void colorLastInput(Color c) {
        try {
            SwingUtilities.invokeAndWait(()->{
                setScroll();
                String last = inputLog.get(inputLog.size()-1);
                NavigationFilter nf = textArea.getNavigationFilter();
                textArea.setNavigationFilter(null);
                textArea.setEditable(true);

                int offset = 0;
                try {
                    offset =
                        textArea.getStyledDocument().getText(0, textArea.getStyledDocument().getLength()).lastIndexOf(last);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
                textArea.select(offset, offset+last.length());
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
                textArea.setCharacterAttributes(aset, false);
                textArea.replaceSelection("");
                sc = StyleContext.getDefaultStyleContext();
                aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, UIColors.DEFAULT_TEXT_COLOR);
                textArea.setCharacterAttributes(aset, false);

                textArea.setNavigationFilter(nf);
                textArea.setEditable(false);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        resetFont();

    }

    @Override
    public void slowPrintlnItemColored(Item i) {
        slowPrintlnColored(""+i, i.getColor());
    }

    @Override
    public void slowerPrintlnItemColored(Item i) {
        slowerPrintlnColored("" + i+"\n", i.getColor());
    }

    private void blip(char c){
        if(c == '\n') {
            JukeBox.playMP3(JukeBox.TYPEWRITER_BELL);
            unshift();
            return;
        }
        double r = (Math.random()*2);
        if(r >= 1.2 && doBlip){
            JukeBox.playMP3(JukeBox.TYPEWRITER_TACK);
            shift();
        }
        doBlip = !doBlip;

    }

    public void delayPrintColored(String toPrint, Color c, int delay){
        log.add(toPrint);
        for(int i = 0; i < toPrint.length(); i++){
            appendToPane(""+toPrint.charAt(i), c);

            blip(toPrint.charAt(i));

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

            }
        }
    }

    private void shift(){

        System.out.println(shifter_left.getSize());
        Dimension s_lSize = shifter_left.getSize();
        Dimension afterShiftLeft = new Dimension((int)(s_lSize.getWidth()-shiftDistance), (int)(s_lSize.getHeight()));
        shifter_left.setSize(afterShiftLeft);
        shifter_left.setPreferredSize(afterShiftLeft);

        Dimension s_rSize = shifter_right.getSize();
        Dimension afterShiftRight = new Dimension((int)(s_rSize.getWidth()+shiftDistance), (int)(s_rSize.getHeight()));
        shifter_right.setSize(afterShiftRight);
        shifter_right.setPreferredSize(afterShiftRight);
        shifts++;
        win.pack();
    }

    private void unshift(){
            Dimension afterShiftLeft = new Dimension((int)(shifter_left.getWidth() + (shifts*shiftDistance)), (int)(shifter_left.getHeight()));
            shifter_left.setSize(afterShiftLeft);
            shifter_left.setPreferredSize(afterShiftLeft);

            Dimension afterShiftRight = new Dimension((int)(shifter_left.getWidth() - (shifts*shiftDistance)), (int)(shifter_left.getHeight()));
            shifter_right.setSize(afterShiftRight);
            shifter_right.setPreferredSize(afterShiftRight);

            shifts = 0;
            win.pack();

    }

    private void unshift(int n){

        if(shifts - n >= 0) {
            Dimension
                afterShiftLeft =
                new Dimension((int) (shifter_left.getWidth() + (shiftDistance * n)), (int) (shifter_left.getHeight()));

            shifter_left.setSize(afterShiftLeft);
            shifter_left.setPreferredSize(afterShiftLeft);
            shifts -= n;
        }
    }


    @Override
    public void printSize(String msg, int fontSize) {

        textArea.setEditable(true);
        swallowInputs = false;

        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, UIColors.DEFAULT_TEXT_COLOR);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, fontName);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        aset = sc.addAttribute(aset, StyleConstants.Size, fontSize);

        int len = textArea.getDocument().getLength();
        textArea.setCaretPosition(len); //move cursor to end
        textArea.setCharacterAttributes(aset, false); //set color
        textArea.replaceSelection(msg); //insert text
        setScroll();
        textArea.setEditable(false);
        swallowInputs = true;

        len += msg.length();
        last = len;
        textArea.setNavigationFilter( new NavigationFilterPrefixWithBackspace(last, textArea) );
        resetFont();

    }

    @Override
    public void unitSay(Unit u, String s) {
        unitSay(u, s, slower);
    }

    @Override
    public void unitSay(Unit u, String s, int delay) {
        delay = delay*2;
        try {
            SwingUtilities.invokeAndWait(()->{
                setCaretVisible(false);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        s=s.replace("\n", "");
        StringTokenizer tokenizer = new StringTokenizer(s);
        int maxwid = 45;
        int height = 1;
        int currentLineWidth = 0;

        StringBuilder content = new StringBuilder();

        while (tokenizer.hasMoreElements()){
            String word = tokenizer.nextToken()+" ";
            if(word.length() > maxwid) { //the word can never fit
                maxwid = word.length();
                content.append("\n").append(word).append("\n");
                height++;
                currentLineWidth = 0;
                continue;
            }

            if(word.length()+currentLineWidth > maxwid){ //the word does not fit
                height++;
                content.append("\n").append(word);
                currentLineWidth = word.length();
            }else{ //word fits, just append)
                content.append(word);
                currentLineWidth += word.length();
            }
        }

        //print box
        String header = "+- "+u.getName()+" "+charMultiply('-', maxwid-1-u.getName().length())+"+\n";
        String box = header;
        for(int i = 0; i < height+2; i++) {
            box += ("|" + charMultiply(' ', maxwid + 2) + "|\n");
        }
        box+=("+"+charMultiply('-', maxwid+2)+"+\n");

        println(box);

        int boxWritableStartOffset = header.length()*2+2; //2 full rows + "|;
        int boxLen = box.length();

        String[] lines = content.toString().split("\n");
        try {
            SwingUtilities.invokeAndWait(()->{
                textArea.setNavigationFilter(null);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        textArea.setEditable(false);

        for(int i = 0; i < lines.length; i++){

            int lineStart = textArea.getDocument().getLength() - boxLen + boxWritableStartOffset + i*header.length() -1;

            for(int j = 0; j < lines[i].length(); j++){
                try {
                    SwingUtilities.invokeAndWait(()->{
                        textArea.setEditable(true);
                    });
                } catch (InterruptedException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                char c = lines[i].charAt(j);
                blip(c);
                textArea.select(lineStart, lineStart+1);
                textArea.replaceSelection(""+lines[i].charAt(j));
                try {
                    SwingUtilities.invokeAndWait(()->{
                        textArea.setEditable(false);
                    });
                } catch (InterruptedException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                lineStart++;
                try {
                    int speakDelay = (int)((Math.random()*delay));
                    Thread.sleep(delay-speakDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        textArea.setEditable(false);


        //last = textArea.getDocument().getLength()-1;
        try {
            SwingUtilities.invokeAndWait(()->{
                textArea.setNavigationFilter( new NavigationFilterPrefixWithBackspace(last, textArea) );
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private String charMultiply(char c, int number){
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < number; i++){
            ret.append(c);
        }
        return ret.toString();
    }

    @Override
    public void print(String toPrint) {
        log.add(toPrint);
        appendToPane(toPrint, UIColors.DEFAULT_TEXT_COLOR);
    }

    public void println(String toPrint){
        log.add(toPrint);
        appendToPane(toPrint+"\n", UIColors.DEFAULT_TEXT_COLOR);
    }

    @Override
    public void slowPrint(String toPrint) {
        log.add(toPrint);
        delayPrint(toPrint , slow);
    }

    @Override
    public void slowPrintln(String toPrint) {
        log.add(toPrint);
        delayPrint(toPrint + "\n", slow);
    }

    @Override
    public void slowerPrintln(String toPrint) {
        log.add(toPrint);
        delayPrint(toPrint + "\n", slower);
    }

    @Override
    public void delayPrintln(String toPrint, int delay){
        delayPrint(toPrint+"\n", delay);
    }

    @Override
    public void delayPrint(String toPrint, int delay){
        delayPrintColored(toPrint, UIColors.DEFAULT_TEXT_COLOR, delay);
    }

    private void appendToPane(String msg, Color c)
    {
        try {
            SwingUtilities.invokeAndWait(() -> {
                textArea.setEditable(true);
                swallowInputs = false;

                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

                aset = sc.addAttribute(aset, StyleConstants.FontFamily, fontName);
                aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
                aset = sc.addAttribute(aset, StyleConstants.Size, fontSize);

                int len = textArea.getDocument().getLength();
                textArea.setCaretPosition(len); //move cursor to end
                textArea.setCharacterAttributes(aset, false); //set color
                textArea.replaceSelection(msg); //insert text
                setScroll();
                textArea.setEditable(false);
                swallowInputs = true;

                len += msg.length();
                last = len;
                textArea.setNavigationFilter( new NavigationFilterPrefixWithBackspace(last, textArea) );

            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        try {
            SwingUtilities.invokeAndWait(()->{
                textArea.setEditable(true);
                setCaretVisible(true);
                swallowInputs = false;
                last = textArea.getDocument().getLength();
                textArea.setNavigationFilter( new NavigationFilterPrefixWithBackspace(last, textArea) );
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            sem.acquire(1);
        } catch (InterruptedException e) {
        }
        sem.drainPermits();
        String ret = null;
        try {
            SwingUtilities.invokeAndWait(()->{
                textArea.setNavigationFilter( null );
                swallowInputs = true;
                setCaretVisible(false);
                setScroll();
                textArea.setEditable(false);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            ret = textArea.getDocument().getText(0, textArea.getDocument().getLength()).substring(last).trim();
            if(!ret.equals(""))
                this.inputLog.add(ret);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        log.add(ret);
        return ret;
    }

    private void setCaretVisible(boolean val){
        caret.setVisible(val);
        caret.setSelectionVisible(val);
    }
}
