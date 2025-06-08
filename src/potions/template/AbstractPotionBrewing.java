package potions.template;

import potions.base.*;
import equipment.*;
import ingredients.Ingredient;

/**
 * Template pattern for potion brewing process.
 * Defines the skeleton of the brewing algorithm but lets subclasses
 * override specific steps.
 */
public abstract class AbstractPotionBrewing {
    protected Builder potionBuild;
    protected Cauldron cauldron;
    protected Burner burner;
    protected StirringRod rod;
    protected Vial vial;
    protected Potion potion;

    public AbstractPotionBrewing() {
        this.potionBuild = new PotionBuilder();
        this.cauldron = new Cauldron();
        this.burner = new Burner();
        this.rod = new StirringRod();
        this.vial = new Vial();
        this.potion = NullPotion.getInstance();
    }

    // template method: defines the complete potion-making process
    public Potion makePotion() {
        prepare();
        brew();
        examine();
        stir();
        store();
        cleanup();
        present();
        return this.potion;
    }

    // abstract methods must be implemented by subclasses
    protected abstract void brew();
    protected abstract void stir();
    protected abstract void store();

    // common methods
    protected void prepare() {
        burner.ignite();
        cauldron.heat(Burner.HeatLevel.LOW);
    }

    protected void examine() {
        System.out.println("The potion has turned " + potion.getColor());
    }

    protected void cleanup() {
        burner.setHeatLevel(Burner.HeatLevel.OFF);
        rod.sanitize();
        cauldron.clean();
    }

    protected void present() {
        System.out.println(potion);
    }
}
