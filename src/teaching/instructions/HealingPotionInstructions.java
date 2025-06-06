package teaching.instructions;

import equipment.Burner;
import equipment.StirringRod;

public class HealingPotionInstructions extends ComplexInstruction {

    public HealingPotionInstructions() {
        super("Healing Potion Brewing Instructions");

        ComplexInstruction baseIngredients = new ComplexInstruction("Add Base Ingredients");
        baseIngredients.addStep(new SimpleInstruction("Dice mandrake root"));
        baseIngredients.addStep(new SimpleInstruction("Add dragon blood (extract + purify)"));
        this.addStep(baseIngredients);

        ComplexInstruction specialIngredients = new ComplexInstruction("Add Special Ingredients");
        specialIngredients.addStep(new SimpleInstruction("Add powdered moonstone"));
        specialIngredients.addStep(new SimpleInstruction("Add hellebore syrup (extract + purify)"));
        this.addStep(specialIngredients);

        ComplexInstruction stirring = new ComplexInstruction("Stir Potion");
        int stirCount = 5;
        for (int i = 1; i <= stirCount; i++) { // each stir is counted twice
            StirringRod.Direction direction = (i % 2 == 1) ? StirringRod.Direction.CLOCKWISE : StirringRod.Direction.COUNTERCLOCKWISE;
            String step = "Stir #" + i + " " + direction.name().toLowerCase() + " for 5 seconds";
            stirring.addStep(new SimpleInstruction(step));
        }
        this.addStep(stirring);

        this.addStep(new SimpleInstruction("Heat potion to " + Burner.HeatLevel.MEDIUM + " temperature"));

        this.addStep(new SimpleInstruction("Add final healing enchantments"));
    }
}
