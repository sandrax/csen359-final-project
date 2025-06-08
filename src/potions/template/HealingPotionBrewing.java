package potions.template;

import potions.base.*;
import equipment.Burner;
import ingredients.*;

/**
 * Concrete implementation of potion brewing template for Healing Potions.
 */
public class HealingPotionBrewing extends AbstractPotionBrewing {

    public HealingPotionBrewing() {
        super();
    }

    @Override
    protected void brew() {
        this.potion = new PotionRecipes().createHealingPotion(potionBuild);
    }

    @Override
    protected void stir() {
    }

    @Override
    protected void store() {
        vial.fill(this.potion.getName());
        vial.seal();
    }
}
