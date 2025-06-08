package potions.template;

import potions.base.Potion;
import equipment.Burner;
import ingredients.Ingredient;

/**
 * Template pattern for potion brewing process.
 * Defines the skeleton of the brewing algorithm but lets subclasses
 * override specific steps.
 */
public abstract class AbstractPotionBrewing {
    protected final Potion potion;

    public AbstractPotionBrewing(Potion potion) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        this.potion = potion;
    }

    // Template method - defines the brewing algorithm
    public final void brew() {
        prepare();
        addBaseIngredients();
        addSpecialIngredients();
        heat();
        stir();
        complete();
    }

    // Required steps - must be implemented by subclasses
    protected abstract void addBaseIngredients();
    protected abstract void addSpecialIngredients();
    protected abstract int getStirCount();
    protected abstract Burner.HeatLevel getBrewingTemperature();

    // Common steps with default implementation
    protected void prepare() {
        System.out.println("Preparing cauldron and tools...");
    }

    protected void heat() {
        System.out.println("Heating potion to " + getBrewingTemperature());
        potion.heat(getBrewingTemperature());
    }

    protected void stir() {
        int stirCount = getStirCount();
        System.out.println("Stirring potion " + stirCount + " times");
        for (int i = 0; i < stirCount; i++) {
            potion.stir(2); // Stir for 2 seconds each time
        }
    }

    protected void complete() {
        System.out.println("Completing brewing process");
        potion.heat(Burner.HeatLevel.OFF);
    }

    // Helper method for subclasses
    protected final void addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }
        potion.addIngredient(ingredient);
    }
}
