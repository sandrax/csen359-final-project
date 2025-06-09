package potions.observer;

import potions.base.Potion;
import equipment.Burner;

/**
 * Observer that monitors safety conditions during potion brewing.
 * Thread-safe implementation for concurrent brewing operations.
 */
public class SafetyMonitorObserver implements PotionObserver {
    private Potion potion;

    public SafetyMonitorObserver(Potion potion) {
        this.potion = potion;
        potion.addObserver(this);
    }

    @Override
    public void onPotionStateChange() {
        // Check temperature safety
        if (potion.getTemperature() == Burner.HeatLevel.HIGH) {
            System.out.println("!!!SAFETY WARNING!!!: High temperature detected!");
        }

        // Check ingredient count
        int ingredientCount = potion.getIngredients().size();
        if (ingredientCount > 5) {
            System.out.println("️!!!SAFETY WARNING!!!: High number of ingredients (" + ingredientCount + ")");
        }

        // Check potency
        if (potion.getPotency() > 8) {
            System.out.println("!!!SAFETY WARNING!!!: High potency level detected!");
        }
    }
}
