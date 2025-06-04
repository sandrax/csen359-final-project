package commands;

import equipment.Cauldron;

public class AddIngredientCommand implements Command {
    private Cauldron cauldron;
    private String ingredient; // Can be replaced with Ingredient object later

    public AddIngredientCommand(Cauldron cauldron, String ingredient) {
        this.cauldron = cauldron;
        this.ingredient = ingredient;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting add ingredient command...");
        cauldron.addIngredient(ingredient);
    }
}