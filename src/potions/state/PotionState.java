package potions.state;

import potions.base.Potion;
import ingredients.Ingredient;
import equipment.Burner;

/**
 * Interface defining the contract for different potion states.
 * Each state handles brewing operations differently and enforces state-specific rules.
 * Implementations should be immutable and thread-safe.
 */
public interface PotionState {
    /**
     * Handle adding ingredients based on current state.
     *
     * @param potion The potion to add ingredients to
     * @param ingredient The ingredient to add
     * @throws IllegalArgumentException if potion or ingredient is null
     * @throws IllegalStateException if operation is not allowed in current state
     */
    void addIngredient(Potion potion, Ingredient ingredient);

    /**
     * Handle heating operations based on current state.
     *
     * @param potion The potion to heat
     * @param level The heat level to apply
     * @throws IllegalArgumentException if potion or level is null
     * @throws IllegalStateException if operation is not allowed in current state
     */
    void heat(Potion potion, Burner.HeatLevel level);

    /**
     * Handle stirring operations based on current state.
     *
     * @param potion The potion to stir
     * @param duration The duration to stir for
     * @throws IllegalArgumentException if potion is null or duration is invalid
     * @throws IllegalStateException if operation is not allowed in current state
     */
    void stir(Potion potion, int duration);

    /**
     * Get the name of the current state.
     * Must be implemented to return a non-null, non-empty string.
     *
     * @return The state name
     */
    String getStateName();

    /**
     * Validate if a transition to this state is allowed.
     *
     * @param fromState The current state transitioning from
     * @return true if transition is allowed, false otherwise
     * @throws IllegalArgumentException if fromState is null
     */
    default boolean canTransitionFrom(PotionState fromState) {
        if (fromState == null) {
            throw new IllegalArgumentException("From state cannot be null");
        }

        String currentState = fromState.getStateName();
        String targetState = this.getStateName();

        // Validate state transitions
        if ("Completed".equals(currentState)) {
            return false; // Cannot transition from completed state
        }
        if ("Preparation".equals(currentState) && "Completed".equals(targetState)) {
            return false; // Cannot skip brewing state
        }
        if ("Brewing".equals(currentState) && "Preparation".equals(targetState)) {
            return false; // Cannot go back to preparation
        }

        return true;
    }
}
