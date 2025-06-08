package potions.base;

import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;

public class PotionBuilder implements Builder {
    private final Potion potion;

    public PotionBuilder() {
        this.potion = new Potion();
    }

    // Copy constructor
    public PotionBuilder(Potion source) {
        this.potion = new Potion();
        if (source != null) {
            potion.setName(source.getName());
            for (Ingredient ingredient : source.getIngredients()) {
                potion.addIngredient(ingredient);
            }
            potion.setTemperature(source.getTemperature());
            potion.setBrewingTime(source.getBrewingTime());
            potion.setColor(source.getColor());
            potion.setPotency(source.getPotency());
            potion.setState(source.getState());
        }
    }

    public PotionBuilder setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        potion.setName(name);
        return this;
    }

    public PotionBuilder addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient cannot be null");
        }
        potion.addIngredient(ingredient);
        return this;
    }

    public PotionBuilder setTemperature(Burner.HeatLevel level) {
        if (level == null) {
            throw new IllegalArgumentException("Temperature level cannot be null");
        }
        potion.setTemperature(level);
        return this;
    }

    public PotionBuilder setBrewingTime(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("Brewing time must be positive");
        }
        potion.setBrewingTime(minutes);
        return this;
    }

    public PotionBuilder setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        potion.setColor(color);
        return this;
    }

    public PotionBuilder setPotency(int potency) {
        if (potency < 0) {
            throw new IllegalArgumentException("Potency cannot be negative");
        }
        potion.setPotency(potency);
        return this;
    }

    public PotionBuilder setState(PotionState state) {
        if (state == null) {
            throw new IllegalArgumentException("State cannot be null");
        }
        potion.setState(state);
        return this;
    }

    public Potion build() {
        try {
            validatePotion();
            return potion;
        } catch (IllegalStateException e) {
            System.out.println("Failed to create potion: " + e.getMessage());
            return NullPotion.getInstance();
        }
    }

    private void validatePotion() {
        StringBuilder errors = new StringBuilder();

        if (potion.getName() == null || potion.getName().trim().isEmpty()) {
            errors.append("Potion must have a name. ");
        }
        if (potion.getIngredients().isEmpty()) {
            errors.append("Potion must have at least one ingredient. ");
        }
        if (potion.getTemperature() == null) {
            errors.append("Potion must have a temperature setting. ");
        }
        if (potion.getBrewingTime() <= 0) {
            errors.append("Potion must have a positive brewing time. ");
        }
        if (potion.getColor() == null || potion.getColor().trim().isEmpty()) {
            errors.append("Potion must have a color. ");
        }
        if (potion.getPotency() < 0) {
            errors.append("Potion must have a non-negative potency. ");
        }
        if (potion.getState() == null) {
            errors.append("Potion must have a state. ");
        }

        if (errors.length() > 0) {
            throw new IllegalStateException(errors.toString().trim());
        }
    }
}
