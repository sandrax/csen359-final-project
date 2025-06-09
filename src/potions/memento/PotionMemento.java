package potions.memento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;
import potions.state.PreparationState;

/**
 * Memento class that stores the state of a Potion.
 * This class is immutable to ensure state integrity.
 */
public final class PotionMemento {
    private final String name;
    private final List<Ingredient> ingredients;
    private final Burner.HeatLevel temperature;
    private final int brewingTime;
    private final String color;
    private final int potency;
    private final PotionState state;
    private final int stirCount;
    private final String timestamp;

    /**
     * Creates a new PotionMemento with the given state.
     *
     * @param name The name of the potion
     * @param ingredients List of ingredients in the potion
     * @param temperature Current temperature level
     * @param brewingTime Brewing time in minutes
     * @param color Color of the potion
     * @param potency Potency level
     * @param state Current state of the potion
     * @param stirCount Number of times the potion has been stirred
     * @param timestamp When this snapshot was taken
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public PotionMemento(String name, List<Ingredient> ingredients, Burner.HeatLevel temperature,
                 int brewingTime, String color, int potency, PotionState state,
                 int stirCount, String timestamp) {
        // Validate input parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (ingredients == null) {
            throw new IllegalArgumentException("Ingredients list cannot be null");
        }
        if (temperature == null) {
            throw new IllegalArgumentException("Temperature cannot be null");
        }
        if (brewingTime < 0) {
            throw new IllegalArgumentException("Brewing time cannot be negative");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        if (potency < 0) {
            throw new IllegalArgumentException("Potency cannot be negative");
        }
        if (stirCount < 0) {
            throw new IllegalArgumentException("Stir count cannot be negative");
        }
        if (timestamp == null || timestamp.trim().isEmpty()) {
            throw new IllegalArgumentException("Timestamp cannot be null or empty");
        }

        this.name = name.trim();
        // Create defensive copy of ingredients list
        this.ingredients = Collections.unmodifiableList(new ArrayList<>(ingredients));
        this.temperature = temperature;
        this.brewingTime = brewingTime;
        this.color = color.trim();
        this.potency = potency;
        // Create new state instance to ensure immutability
        this.state = state != null ? state : new PreparationState();
        this.stirCount = stirCount;
        this.timestamp = timestamp.trim();
    }

    /**
     * Gets the potion name.
     * @return The name of the potion
     */
    public String getName() {
        return name;
    }

    /**
     * Gets an unmodifiable list of ingredients.
     * @return Unmodifiable list of ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;  // Already unmodifiable from constructor
    }

    /**
     * Gets the temperature level.
     * @return The temperature level
     */
    public Burner.HeatLevel getTemperature() {
        return temperature;
    }

    /**
     * Gets the brewing time.
     * @return The brewing time in minutes
     */
    public int getBrewingTime() {
        return brewingTime;
    }

    /**
     * Gets the potion color.
     * @return The color of the potion
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the potency level.
     * @return The potency level
     */
    public int getPotency() {
        return potency;
    }

    /**
     * Gets the potion state.
     * @return The current state of the potion
     */
    public PotionState getState() {
        return state;
    }

    /**
     * Gets the stir count.
     * @return Number of times the potion has been stirred
     */
    public int getStirCount() {
        return stirCount;
    }

    /**
     * Gets the timestamp.
     * @return When this snapshot was taken
     */
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Snapshot [%s] %s - State: %s, Ingredients: %d, Stirs: %d, Color: %s, Potency: %d",
            timestamp, name, state.getStateName(), ingredients.size(), stirCount, color, potency);
    }
}
