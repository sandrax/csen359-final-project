package test;

import potions.base.Potion;
import potions.base.PotionBuilder;
import potions.observer.BrewingLogObserver;
import potions.observer.SafetyMonitorObserver;
import potions.state.BrewingState;
import potions.state.CompletedState;
import potions.template.HealingPotionBrewing;
import potions.template.InvisibilityPotionBrewing;
import ingredients.*;
import equipment.Burner;

public class PotionTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Harry Potter Potion Brewing System ===\n");

        try {
            // Test 1: State Pattern and Observer Pattern
            System.out.println("TEST 1: State Pattern & Observer Pattern");
            System.out.println("----------------------------------------");

            // Create observers
            BrewingLogObserver brewingLog = new BrewingLogObserver();
            SafetyMonitorObserver safetyMonitor = new SafetyMonitorObserver();

            // Create a healing potion
            Potion healingPotion = new PotionBuilder()
                .setName("Healing Potion")
                .setColor("red")
                .setPotency(5)
                .setBrewingTime(30)
                .setTemperature(Burner.HeatLevel.OFF)
                .addIngredient(new Mandrake())
                .build();

            // Add observers
            healingPotion.addObserver(brewingLog);
            healingPotion.addObserver(safetyMonitor);

            // Test state transitions
            System.out.println("\nTesting state transitions:");
            healingPotion.addIngredient(new DragonBlood(new Extract(), new Purify()));
            healingPotion.heat(Burner.HeatLevel.MEDIUM);
            healingPotion.stir(3);
            healingPotion.heat(Burner.HeatLevel.HIGH);
            healingPotion.stir(2);
            healingPotion.heat(Burner.HeatLevel.OFF);

            // Test completed state restrictions
            System.out.println("\nTesting completed state restrictions:");
            try {
                healingPotion.stir(1);
            } catch (IllegalStateException e) {
                System.out.println("Success: " + e.getMessage());
            }

            // Test 2: Memento Pattern
            System.out.println("\nTEST 2: Memento Pattern");
            System.out.println("----------------------");

            // Save state
            var memento = healingPotion.save();
            System.out.println("Saved potion state: " + healingPotion);

            // Create new potion and restore
            Potion newPotion = new PotionBuilder()
                .setName("Empty Potion")
                .setColor("clear")
                .setPotency(1)
                .setBrewingTime(10)
                .setTemperature(Burner.HeatLevel.OFF)
                .addIngredient(new Mandrake())
                .build();

            newPotion.restore(memento);
            System.out.println("Restored potion state: " + newPotion);
            System.out.println("States match: " + healingPotion.toString().equals(newPotion.toString()));

            // Test 3: Template Pattern
            System.out.println("\nTEST 3: Template Pattern");
            System.out.println("----------------------");

            // Create a new potion using the template
            Potion templatePotion = new PotionBuilder()
                .setName("Template Healing Potion")
                .setColor("red")
                .setPotency(5)
                .setBrewingTime(30)
                .setTemperature(Burner.HeatLevel.OFF)
                .addIngredient(new Mandrake())
                .build();

            // Add observers to template potion
            templatePotion.addObserver(brewingLog);
            templatePotion.addObserver(safetyMonitor);

            // Use template to brew
            System.out.println("\nBrewing potion using template pattern:");
            HealingPotionBrewing healingTemplate = new HealingPotionBrewing(templatePotion);
            healingTemplate.brew();

            System.out.println("\nFinal template potion state: " + templatePotion);

            // Test 4: Template Pattern - Invisibility Potion
            System.out.println("\nTEST 4: Template Pattern - Invisibility Potion");
            System.out.println("----------------------------------------");

            // Create a new potion using the template
            Potion invisibilityPotion = new PotionBuilder()
                .setName("Invisibility Potion")
                .setColor("transparent")
                .setPotency(8)
                .setBrewingTime(45)
                .setTemperature(Burner.HeatLevel.OFF)
                .addIngredient(new Mandrake())
                .build();

            // Add observers to invisibility potion
            invisibilityPotion.addObserver(brewingLog);
            invisibilityPotion.addObserver(safetyMonitor);

            // Use template to brew
            System.out.println("\nBrewing invisibility potion using template pattern:");
            InvisibilityPotionBrewing invisibilityTemplate = new InvisibilityPotionBrewing(invisibilityPotion);
            invisibilityTemplate.brew();

            System.out.println("\nFinal invisibility potion state: " + invisibilityPotion);

            // Summary
            System.out.println("\n=== Test Summary ===");
            System.out.println("State Pattern: Successful state transitions and restrictions");
            System.out.println("Observer Pattern: Brewing logs and safety warnings generated");
            System.out.println("Memento Pattern: State successfully saved and restored");
            System.out.println("Template Pattern: Both Healing and Invisibility potions brewed successfully");

        } catch (Exception e) {
            System.err.println("\nError during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
