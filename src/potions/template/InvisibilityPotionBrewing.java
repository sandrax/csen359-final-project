package potions.template;

import potions.base.Potion;
import ingredients.*;
import equipment.Burner;

/**
 * Concrete implementation of potion brewing for Invisibility Potions.
 * Specializes in creating potions that grant invisibility effects.
 */
public class InvisibilityPotionBrewing extends AbstractPotionBrewing {

    public IvisibilityPotionBrewing(Potion potion) {
        super(potion);
    }

    @Override
    protected void addBaseIngredients() {
        System.out.println("Adding base invisibility ingredients...");
        // Create powdered moonstone
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
    protected void addSpecialIngredients() {
        System.out.println("Adding special invisibility ingredients...");
        // Add additional moonstone for enhanced invisibility effect
        BasicIngredient moonstone = new Moonstone();
        Ingredient powderedMoonstone = new Powdered(moonstone);
        addIngredient(powderedMoonstone);

        // Add dragon blood for stability
        Ingredient dragonBlood = new DragonBlood(new Extract(), new Purify());
        addIngredient(dragonBlood);
    }

    @Override
    protected int getStirCount() {
        return 4; // Each stir is counted twice, so 4 * 2 = 8 total stirs
    }

    @Override
    protected Burner.HeatLevel getBrewingTemperature() {
        return Burner.HeatLevel.HIGH; // High heat needed for transparency effect
    }

    @Override
    protected void complete() {
        System.out.println("Adding final invisibility enchantments...");
        super.complete();
    }
}
