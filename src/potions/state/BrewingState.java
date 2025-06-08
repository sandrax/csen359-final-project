package potions.state;

import potions.base.Potion;
import ingredients.Ingredient;
import equipment.Burner;

/**
 * Active brewing state where the potion is being heated and can be stirred.
 * This state allows heating adjustments and stirring but not adding ingredients.
 */
public final class BrewingState implements PotionState {
    private static final int MAX_STIR_COUNT = 10;

    @Override
    public void addIngredient(Potion potion, Ingredient ingredient) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }

        throw new IllegalStateException("Cannot add ingredients while brewing - must be in preparation state");
    }

    @Override
    public void heat(Potion potion, Burner.HeatLevel level) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (level == null) {
            throw new IllegalArgumentException("Heat level cannot be null");
        }

        try {
            if (level == Burner.HeatLevel.OFF) {
                System.out.println("Turning off heat - completing brewing process");
                potion.setStateTemperature(level);
                potion.setState(new CompletedState());
            } else {
                if (level == potion.getTemperature()) {
                    System.out.println("Heat level already at " + level);
                    return;
                }
                System.out.println("Adjusting heat to " + level);
                potion.setStateTemperature(level);
            }
        } catch (Exception e) {
            System.err.println("Failed to adjust heat: " + e.getMessage());
            throw new IllegalStateException("Failed to adjust heat", e);
        }
    }

    @Override
    public void stir(Potion potion, int duration) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Stirring duration must be positive");
        }

        try {
            if (potion.getTemperature() == Burner.HeatLevel.OFF) {
                throw new IllegalStateException("Cannot stir when heat is off");
            }

            if (potion.getStirCount() >= MAX_STIR_COUNT) {
                throw new IllegalStateException("Maximum stir count reached (" + MAX_STIR_COUNT + ")");
            }

            System.out.println("Stirring potion for " + duration + " seconds");
            potion.performStateStir(duration);
        } catch (Exception e) {
            System.err.println("Failed to stir potion: " + e.getMessage());
            throw new IllegalStateException("Failed to stir potion", e);
        }
    }

    @Override
    public String getStateName() {
        return "Brewing";
    }

    @Override
    public boolean canTransitionFrom(PotionState fromState) {
        if (fromState == null) {
            throw new IllegalArgumentException("From state cannot be null");
        }

        // Can only transition to Brewing from Preparation state
        return fromState.getStateName().equals("Preparation");
    }
}
