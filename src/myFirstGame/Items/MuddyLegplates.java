package myFirstGame.Items;

import com.sun.xml.internal.ws.model.ReflectAnnotationReader;

import GameLogic.Inventory.Items.LegArmor;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class MuddyLegplates extends LegArmor {

    public MuddyLegplates() {
        super("Muddy Legplates", "A pair of very muddy legplates", 1, UIColors.RARITY_SHITTY);
    }
}
