package myFirstGame.Items;

import GameLogic.Inventory.Items.HelmArmor;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class NiglusGreatHelm extends HelmArmor {
    public NiglusGreatHelm() {
        super("Niglu's Great Helm", "The great helm of the mythical Niglu The Explorer.", 40, UIColors.RARITY_EPIC);
    }
}
