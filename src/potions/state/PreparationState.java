package potions.state;

import potions.base.Potion;
import ingredients.Ingredient;
import equipment.Burner;

/**
 * Initial state where ingredients can be added but brewing hasn't started.
 * This state allows adding ingredients and transitioning to brewing state.
 */
public final class PreparationState implements PotionState {

    @Override
    public void addIngredient(Potion potion, Ingredient ingredient) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }

        // In preparation state, we can always add ingredients
        System.out.println("The " + ingredient + " has mixed with the potion being prepared.");
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
            if (level != Burner.HeatLevel.OFF) {
                if (potion.getIngredients().isEmpty()) {
                    throw new IllegalStateException("Cannot start brewing without ingredients");
                }
                System.out.println("Starting brewing process at " + level + " heat");
                potion.setState(new BrewingState());
            } else {
                throw new IllegalStateException("Cannot start brewing with heat off");
            }
        } catch (Exception e) {
            System.err.println("Failed to heat potion: " + e.getMessage());
            throw new IllegalStateException("Failed to heat potion", e);
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

        throw new IllegalStateException("Cannot stir in preparation state - heat the potion first");
    }

    @Override
    public String getStateName() {
        return "Preparation";
    }

    @Override
    public boolean canTransitionFrom(PotionState fromState) {
        if (fromState == null) {
            throw new IllegalArgumentException("From state cannot be null");
        }

        // Can only transition to Preparation from null (initial state)
        // or from another Preparation state (no-op transition)
        return fromState.getStateName().equals("Preparation") ||
               fromState.getStateName().equals("Initial");
    }
}
