package potions.state;

import potions.base.Potion;
import ingredients.Ingredient;
import equipment.Burner;

/**
 * Final state representing a completed potion that cannot be modified.
 * This state prevents any modifications to the potion as it is complete.
 */
public final class CompletedState implements PotionState {

    @Override
    public void addIngredient(Potion potion, Ingredient ingredient) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }

        throw new IllegalStateException("Cannot modify a completed potion - ingredients cannot be added");
    }

    @Override
    public void heat(Potion potion, Burner.HeatLevel level) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (level == null) {
            throw new IllegalArgumentException("Heat level cannot be null");
        }

        throw new IllegalStateException("Cannot modify a completed potion - heating not allowed");
    }

    @Override
    public void stir(Potion potion, int duration) {
        if (potion == null) {
            throw new IllegalArgumentException("Potion cannot be null");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Stirring duration must be positive");
        }

        throw new IllegalStateException("Cannot modify a completed potion - stirring not allowed");
    }

    @Override
    public String getStateName() {
        return "Completed";
    }

    @Override
    public boolean canTransitionFrom(PotionState fromState) {
        if (fromState == null) {
            throw new IllegalArgumentException("From state cannot be null");
        }

        // Can only transition to Completed from Brewing state
        return fromState.getStateName().equals("Brewing");
    }
}
