package potions.base;

import ingredients.Ingredient;
import equipment.Burner;
import potions.state.PotionState;
import potions.observer.PotionObserver;
import potions.memento.PotionMemento;
import java.util.List;

public interface Potion {
    public void addIngredient(Ingredient ingredient);
    public void heat(Burner.HeatLevel level);
    public void stir(int duration);
    public void performStateStir(int duration);

    // getters
    public String getName();
    public List<Ingredient> getIngredients();
    public Burner.HeatLevel getTemperature();
    public int getBrewingTime();
    public String getColor();
    public int getPotency();
    public PotionState getState();
    public int getStirCount();

    // setters
    public void setName(String name);
    public void setTemperature(Burner.HeatLevel level);
    public void setBrewingTime(int brewingTime);
    public void setColor(String color);
    public void setPotency(int potency);
    public void setState(PotionState newState);

    // observer methods
    public void addObserver(PotionObserver observer);
    public void removeObserver(PotionObserver observer);
    public void clearObservers();
    public int getObserverCount();
    public void notifyObservers();

    // memento methods
    public PotionMemento save();
    public void restore(PotionMemento memento);
    public String toString();
    public void setStateTemperature(Burner.HeatLevel level);
}
