package potions.template;

import potions.base.Potion;
import equipment.Burner;
import ingredients.*;

/**
 * Concrete implementation of potion brewing template for Healing Potions.
 */
public class HealingPotionBrewing extends AbstractPotionBrewing {

    public HealingPotionBrewing(Potion potion) {
        super(potion);
    }

    @Override
    protected void addBaseIngredients() {
        System.out.println("Adding base healing ingredients...");
        // Create mandrake root and dice it
        BasicIngredient mandrake = new Mandrake();
        Ingredient dicedMandrake = new Diced(mandrake);
        addIngredient(dicedMandrake);

        // Create dragon blood with extract and purify decorators
        Ingredient dragonBlood = new DragonBlood(new Extract(), new Purify());
        addIngredient(dragonBlood);
    }

    @Override
    protected void addSpecialIngredients() {
        System.out.println("Adding special healing ingredients...");
        // Add powdered moonstone
        BasicIngredient moonstone = new Moonstone();
        Ingredient powderedMoonstone = new Powdered(moonstone);
        addIngredient(powderedMoonstone);

        // Add Hellebore syrup with liquid preparation
        LiquidPrep extract = new Extract();
        LiquidPrep purify = new Purify();
        Ingredient helleboreSyrup = new HelleboreSyrup(extract, purify);
        addIngredient(helleboreSyrup);
    }

    @Override
    protected int getStirCount() {
        return 5; // Healing potions need 5 stirs
    }

    @Override
    protected Burner.HeatLevel getBrewingTemperature() {
        return Burner.HeatLevel.MEDIUM; // Healing potions brew at medium heat
    }

    @Override
    protected void complete() {
        System.out.println("Adding final healing enchantments...");
        super.complete();
    }
}
