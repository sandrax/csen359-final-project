package equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import equipment.diagnostics.EquipmentVisitor;

public class Cauldron implements Equipment {
    private List<String> ingredients = new ArrayList<>(); // can change to Ingredients class later
    private boolean isHeated = false;
    private int magicalStability = 100;
    private int temperature = 20;

    private final Random random = new Random();

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
        magicalStability = Math.max(1, magicalStability - (10 + random.nextInt(5)));
        System.out.println("Added " + ingredient + " to the cauldron.");
    }

    public void heat(Burner.HeatLevel level) {
        isHeated = true;
        int tempIncrease;
        int stabilityDrop;

        switch (level) {
            case LOW -> {
                tempIncrease = 5;
                stabilityDrop = 5;
            }
            case MEDIUM -> {
                tempIncrease = 10;
                stabilityDrop = 10;
            }
            case HIGH -> {
                tempIncrease = 15;
                stabilityDrop = 15;
            }
            default -> {
                tempIncrease = 0;
                stabilityDrop = 0;
            }
        }

        temperature = Math.min(100, temperature + tempIncrease);
        magicalStability = Math.max(1, magicalStability - stabilityDrop);

        System.out.println("Cauldron heated to: " + temperature + "°C (via " + level + " heat)");
    }

    public void unheat() {
        temperature = 20;
        System.out.println("Cauldron is no longer heated.");
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean isHeated() {
        return isHeated;
    }

    public int getMagicalStability() {
        return magicalStability;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
        visitor.visit(this);
    }
}
