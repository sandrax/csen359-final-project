package potions.template;

import potions.base.*;
import potions.state.*;
import ingredients.*;

/**
 * Concrete implementation of potion brewing template for Poison Potions.
 */
public class PoisonPotionBrewing extends AbstractPotionBrewing {

    public PoisonPotionBrewing() {
        super();
    }

    @Override
    protected void brew() {
        this.potion = new PotionRecipes().createPoisonPotion(potionBuild);
        this.potion.setState(new BrewingState());
    }

    @Override
    protected void stir() {
    }

    @Override
    protected void store() {
        this.potion.setState(new CompletedState());
        System.out.println(this.potion.getName() + " must be stored cold.");
        System.out.println("Cast INFRIGIDO on the vial and cauldron.");
        vial.fill(this.potion.getName());
        vial.seal();
    }
}
