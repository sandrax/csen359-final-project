package potions.template;

import potions.base.*;
import potions.state.*;
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
        this.potion.setState(new BrewingState());
    }

    @Override
    protected void stir() {
    }

    @Override
    protected void store() {
        this.potion.setState(new CompletedState());
        vial.fill(this.potion.getName());
        vial.seal();
    }
}
