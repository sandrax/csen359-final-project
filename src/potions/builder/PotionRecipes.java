package potions.builder;

import equipment.Burner;
import ingredients.*;
import potions.base.Potion;
import potions.base.PotionBuilder;
import potions.base.NullPotion;
import potions.state.PreparationState;

/**
 * Factory class providing predefined recipes for common potions.
 * Uses the Builder pattern to create potions with specific configurations.
 */
public class PotionRecipes {
    // Constants for standard potion configurations
    private static final int HEALING_BREW_TIME = 30;
    private static final int HEALING_POTENCY = 5;
    private static final String HEALING_COLOR = "red";

    private static final int INVISIBILITY_BREW_TIME = 45;
    private static final int INVISIBILITY_POTENCY = 8;
    private static final String INVISIBILITY_COLOR = "clear";

    private static final int POISON_BREW_TIME = 60;
    private static final int POISON_POTENCY = 10;
    private static final String POISON_COLOR = "purple";

    /**
     * Creates a standard healing potion with moderate potency.
     * @return A new healing potion instance or NullPotion if creation fails
     */
    public static Potion createHealingPotion() {
        try {
            // Validate ingredients before creation
            BasicIngredient mandrake = new Mandrake();
            if (!(mandrake instanceof Mandrake)) {
                throw new IllegalStateException("Invalid mandrake ingredient");
            }

            Ingredient dicedMandrake = new Diced(mandrake);
            Ingredient dragonBlood = new DragonBlood(new Extract(), new Purify());

            return new PotionBuilder()
                .setName("Healing Potion")
                .addIngredient(dicedMandrake)
                .addIngredient(dragonBlood)
                .setTemperature(Burner.HeatLevel.MEDIUM)
                .setBrewingTime(HEALING_BREW_TIME)
                .setColor(HEALING_COLOR)
                .setPotency(HEALING_POTENCY)
                .setState(new PreparationState())
                .build();
        } catch (Exception e) {
            System.out.println("Error creating healing potion: " + e.getMessage());
            return NullPotion.getInstance();
        }
    }

    /**
     * Creates a standard invisibility potion with high potency.
     * @return A new invisibility potion instance or NullPotion if creation fails
     */
    public static Potion createInvisibilityPotion() {
        try {
            // Validate ingredients before creation
            BasicIngredient moonstone = new Moonstone();
            if (!(moonstone instanceof Moonstone)) {
                throw new IllegalStateException("Invalid moonstone ingredient");
            }

            Ingredient powderedMoonstone = new Powdered(moonstone);
            Ingredient helleboreSyrup = new HelleboreSyrup(new Extract(), new Purify());

            return new PotionBuilder()
                .setName("Invisibility Potion")
                .addIngredient(powderedMoonstone)
                .addIngredient(helleboreSyrup)
                .setTemperature(Burner.HeatLevel.HIGH)
                .setBrewingTime(INVISIBILITY_BREW_TIME)
                .setColor(INVISIBILITY_COLOR)
                .setPotency(INVISIBILITY_POTENCY)
                .setState(new PreparationState())
                .build();
        } catch (Exception e) {
            System.out.println("Error creating invisibility potion: " + e.getMessage());
            return NullPotion.getInstance();
        }
    }

    /**
     * Creates a standard poison potion with maximum potency.
     * @return A new poison potion instance or NullPotion if creation fails
     */
    public static Potion createPoisonPotion() {
        try {
            // Validate ingredients before creation
            BasicIngredient aconite = new Aconite("root");
            if (!(aconite instanceof Aconite)) {
                throw new IllegalStateException("Invalid aconite ingredient");
            }

            Ingredient powderedAconite = new Powdered(aconite);
            Ingredient dragonBlood = new DragonBlood(new Extract(), new Purify());

            return new PotionBuilder()
                .setName("Poison Potion")
                .addIngredient(powderedAconite)
                .addIngredient(dragonBlood)
                .setTemperature(Burner.HeatLevel.LOW)
                .setBrewingTime(POISON_BREW_TIME)
                .setColor(POISON_COLOR)
                .setPotency(POISON_POTENCY)
                .setState(new PreparationState())
                .build();
        } catch (Exception e) {
            System.out.println("Error creating poison potion: " + e.getMessage());
            return NullPotion.getInstance();
        }
    }

    /**
     * Creates a custom potion with specified parameters.
     *
     * @param name The name of the potion
     * @param ingredients Array of ingredients to add
     * @param temp The temperature level for brewing
     * @param time The brewing time in minutes
     * @param color The color of the potion
     * @param potency The potency level
     * @return A new custom potion instance or NullPotion if creation fails
     * @throws IllegalArgumentException if any parameters are invalid
     */
    public static Potion createCustomPotion(String name, Ingredient[] ingredients,
                                          Burner.HeatLevel temp, int time,
                                          String color, int potency) {
        // Validate input parameters
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Potion name cannot be null or empty");
        }
        if (ingredients == null || ingredients.length == 0) {
            throw new IllegalArgumentException("Ingredients array cannot be null or empty");
        }
        if (temp == null) {
            throw new IllegalArgumentException("Temperature level cannot be null");
        }
        if (time <= 0) {
            throw new IllegalArgumentException("Brewing time must be positive");
        }
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }
        if (potency < 0) {
            throw new IllegalArgumentException("Potency cannot be negative");
        }

        try {
            // Validate each ingredient before creation
            for (Ingredient ingredient : ingredients) {
                if (ingredient == null) {
                    throw new IllegalArgumentException("Ingredient cannot be null");
                }
                // Validate ingredient decorators
                if (ingredient instanceof Extract || ingredient instanceof Purify) {
                    throw new IllegalArgumentException("Extract and Purify must be applied to base ingredients");
                }
            }

            PotionBuilder builder = new PotionBuilder()
                .setName(name.trim())
                .setTemperature(temp)
                .setBrewingTime(time)
                .setColor(color.trim())
                .setPotency(potency)
                .setState(new PreparationState());

            // Add ingredients
            for (Ingredient ingredient : ingredients) {
                builder.addIngredient(ingredient);
            }

            return builder.build();
        } catch (Exception e) {
            System.out.println("Error creating custom potion: " + e.getMessage());
            return NullPotion.getInstance();
        }
    }
}
