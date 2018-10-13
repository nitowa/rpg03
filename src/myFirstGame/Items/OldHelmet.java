package myFirstGame.Items;



import GameLogic.Inventory.Items.HelmArmor;
import GameLogic.State.UI.UIColors;

import java.awt.*;

public class OldHelmet extends HelmArmor {

    public OldHelmet() {
        super("Old Helmet", "A very old helmet indeed.", 1, UIColors.RARITY_SHITTY);
    }


}
