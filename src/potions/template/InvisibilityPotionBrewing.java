package potions.template;

import potions.base.*;
import potions.state.*;
import ingredients.*;
import equipment.*;
import equipment.StirringRod.Direction;

/**
 * Concrete implementation of potion brewing for Invisibility Potions.
 * Specializes in creating potions that grant invisibility effects.
 */
public class InvisibilityPotionBrewing extends AbstractPotionBrewing {

    public InvisibilityPotionBrewing() {
        super();
    }

    @Override
    protected void brew() {
        this.potion = new PotionRecipes().createInvisibilityPotion(potionBuild);
        potionBuild.setState(new BrewingState());
    }

    @Override
    protected void stir() {
        System.out.println("Needs additional stirring to increase potency...");
        rod.stir(Direction.CLOCKWISE, 2);
        rod.stir(Direction.COUNTERCLOCKWISE, 2);
        this.potion.stir(2);
    }

    @Override
    protected void store() {
        System.out.println(this.potion.getName() + " must be stored warm.");
        System.out.println("Cast FOCILLO on the vial.");
        vial.fill(this.potion.getName());
        vial.seal();
    }
}
