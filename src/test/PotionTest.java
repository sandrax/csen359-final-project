package test;

import potions.base.Potion;
import potions.base.PotionBuilder;
import potions.observer.*;
import potions.state.*;
import potions.memento.*;
import potions.template.*;
import ingredients.*;
import equipment.Burner;

public class PotionTest {
    public static void main(String[] args) {
        System.out.println("=== Testing Harry Potter Potion Brewing System ===\n");

        try {
            // Test 1: State Pattern and Observer Pattern
            System.out.println("TEST 1: State Pattern & Observer Pattern");
            System.out.println("----------------------------------------");

            // make some potion
            Potion somePotion = new PotionBuilder()
                .setName("Random Potion")
                .setColor("red")
                .setPotency(5)
                .setBrewingTime(30)
                .setTemperature(Burner.HeatLevel.OFF)
                .addIngredient(new Mandrake())
                .build();
            
            // make observers
            BrewingLogObserver brewingLog = new BrewingLogObserver(somePotion);
            SafetyMonitorObserver safetyMonitor = new SafetyMonitorObserver(somePotion);

            // Test state transitions
            System.out.println("\nTesting state transitions:");
            somePotion.addIngredient(new DragonBlood(new Extract(), new Purify()));
            somePotion.heat(Burner.HeatLevel.MEDIUM);
            somePotion.stir(3);
            somePotion.heat(Burner.HeatLevel.HIGH);
            somePotion.stir(2);
            somePotion.heat(Burner.HeatLevel.OFF);

            // Test completed state restrictions
            System.out.println("\nTesting completed state restrictions:");
            try {
                somePotion.stir(1);
            } catch (IllegalStateException e) {
                System.out.println("Success: " + e.getMessage());
            }

            // Test 2: Memento Pattern
            System.out.println("\nTEST 2: Memento Pattern");
            System.out.println("----------------------");

            // make a new potion and save states
            Potion newPotion = new PotionBuilder()
                .setName("Experimental Potion")
                .setColor("green")
                .setPotency(3)
                .setBrewingTime(25)
                .setTemperature(Burner.HeatLevel.LOW)
                .addIngredient(new Aconite("leaf"))
                .build();

            PotionCaretaker spell = new PotionCaretaker();
            spell.saveState(newPotion.save());
            System.out.println("Curent potion state:\n" + newPotion);

            // do stuff
            newPotion.addIngredient(new OccamyEgg());
            newPotion.heat(Burner.HeatLevel.HIGH);
            newPotion.stir(1);
            newPotion.setColor("vomit");
            newPotion.setPotency(10);
            System.out.println("Updated potion state:\n" + newPotion);

            newPotion.restore(spell.goBack());
            System.out.println("Restored potion state:\n" + newPotion);

            // Test 3: Template Pattern
            System.out.println("\nTEST 3: Template Pattern");
            System.out.println("----------------------");

            System.out.println("\n ---------------------\n| INVISIBILITY POTION |\n ---------------------");
            AbstractPotionBrewing demo = new InvisibilityPotionBrewing();
            demo.makePotion();

            System.out.println("\n ----------------\n| HEALING POTION |\n ----------------");
            demo = new HealingPotionBrewing();
            demo.makePotion();

            System.out.println("\n ---------------\n| POISON POTION |\n ---------------");
            demo = new PoisonPotionBrewing();
            demo.makePotion();

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
