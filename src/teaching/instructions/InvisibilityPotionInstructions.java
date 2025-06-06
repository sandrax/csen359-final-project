package teaching.instructions;

import equipment.Burner;
import equipment.StirringRod;

public class InvisibilityPotionInstructions extends ComplexInstruction {

    public InvisibilityPotionInstructions() {
        super("Invisibility Potion Brewing Instructions");

        ComplexInstruction baseIngredients = new ComplexInstruction("Add Base Ingredients");
        baseIngredients.addStep(new SimpleInstruction("Create powdered moonstone"));
        baseIngredients.addStep(new SimpleInstruction("Prepare hellebore syrup (extract + purify)"));
        this.addStep(baseIngredients);

        ComplexInstruction specialIngredients = new ComplexInstruction("Add Special Ingredients");
        specialIngredients.addStep(new SimpleInstruction("Add additional powdered moonstone"));
        specialIngredients.addStep(new SimpleInstruction("Add dragon blood (extract + purify)"));
        this.addStep(specialIngredients);

        ComplexInstruction stirring = new ComplexInstruction("Stir Potion");
        int stirCount = 4;
        for (int i = 1; i <= stirCount * 2; i++) { // each stir is counted twice
            StirringRod.Direction direction = (i % 2 == 1) ? StirringRod.Direction.CLOCKWISE : StirringRod.Direction.COUNTERCLOCKWISE;
            String step = "Stir #" + i + " " + direction.name().toLowerCase() + " for 5 seconds";
            stirring.addStep(new SimpleInstruction(step));
        }
        this.addStep(stirring);

        this.addStep(new SimpleInstruction("Heat potion to " + Burner.HeatLevel.HIGH + " temperature"));

        this.addStep(new SimpleInstruction("Add final invisibility enchantments"));
    }
}
