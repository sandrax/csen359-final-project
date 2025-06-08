package potions.base;

import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;
import potions.observer.PotionObserver;
import potions.memento.PotionMemento;
import java.util.Collections;
import java.util.List;

/**
 * Null Object pattern implementation for Potion.
 * Represents an invalid or incomplete potion state.
 * This class is thread-safe and immutable.
 */
public final class NullPotion extends Potion {
    private static final NullPotion instance = new NullPotion();
    private static final String ERROR_MESSAGE = "Operation not allowed on invalid potion";

    private NullPotion() {
        super();
    }

    public static NullPotion getInstance() {
        return instance;
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void heat(Burner.HeatLevel level) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void stir(int duration) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String getName() {
        return "Invalid Potion";
    }

    @Override
    public List<Ingredient> getIngredients() {
        return Collections.emptyList();
    }

    @Override
    public Burner.HeatLevel getTemperature() {
        return Burner.HeatLevel.OFF;
    }

    @Override
    public int getBrewingTime() {
        return 0;
    }

    @Override
    public String getColor() {
        return "none";
    }

    @Override
    public int getPotency() {
        return 0;
    }

    @Override
    public int getStirCount() {
        return 0;
    }

    @Override
    public PotionState getState() {
        return null;
    }

    @Override
    public void setState(PotionState state) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void addObserver(PotionObserver observer) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void removeObserver(PotionObserver observer) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void clearObservers() {
        // No-op for null object
    }

    @Override
    public int getObserverCount() {
        return 0;
    }

    @Override
    public PotionMemento save() {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public void restore(PotionMemento memento) {
        throw new UnsupportedOperationException(ERROR_MESSAGE);
    }

    @Override
    public String toString() {
        return "NullPotion{Invalid or incomplete potion}";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NullPotion;
    }

    @Override
    public int hashCode() {
        return NullPotion.class.hashCode();
    }
}
