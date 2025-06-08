package potions.base;

import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;

public interface Builder {
    public PotionBuilder addIngredient(Ingredient ingredient);
    public PotionBuilder setTemperature(Burner.HeatLevel level);
    public PotionBuilder setBrewingTime(int minutes);
    public PotionBuilder setColor(String color);
    public PotionBuilder setName(String name);
    public PotionBuilder setPotency(int potency);
    public PotionBuilder setState(PotionState state);
    public Potion build();
}
