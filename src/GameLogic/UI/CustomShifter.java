package GameLogic.UI;

import java.awt.*;

import javax.swing.*;

public class CustomShifter extends Box.Filler {
    /**
     * Constructor to create shape with the given size ranges.
     *
     * @param min  Minimum size
     * @param pref Preferred size
     * @param max  Maximum size
     */
    public CustomShifter(Dimension min, Dimension pref, Dimension max) {
        super(min, pref, max);
    }
}
