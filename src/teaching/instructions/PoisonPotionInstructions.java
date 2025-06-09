package teaching.instructions;

import equipment.Burner;
import equipment.StirringRod;

public class PoisonPotionInstructions extends ComplexInstruction {

    public PoisonPotionInstructions() {
        super("Poison Potion Brewing Instructions");

        ComplexInstruction basePrep = new ComplexInstruction("Prepare Base Ingredients");
        basePrep.addStep(new SimpleInstruction("Collect aconite root"));
        basePrep.addStep(new SimpleInstruction("Grind aconite root into powder"));
        this.addStep(basePrep);

        ComplexInstruction specialIngredients = new ComplexInstruction("Add Special Ingredients");
        specialIngredients.addStep(new SimpleInstruction("Add powdered aconite to cauldron"));
        specialIngredients.addStep(new SimpleInstruction("Add dragon blood (extract + purify) to cauldron"));
        this.addStep(specialIngredients);

        ComplexInstruction stirring = new ComplexInstruction("Stir Potion");
        int stirCount = 4;
        for (int i = 1; i <= stirCount; i++) {
            StirringRod.Direction direction = (i % 2 == 1)
                ? StirringRod.Direction.COUNTERCLOCKWISE
                : StirringRod.Direction.CLOCKWISE;
            String step = "Stir #" + i + " " + direction.name().toLowerCase() + " for 3 seconds";
            stirring.addStep(new SimpleInstruction(step));
        }
        this.addStep(stirring);

        this.addStep(new SimpleInstruction("Set burner to " + Burner.HeatLevel.LOW + " temperature"));
        this.addStep(new SimpleInstruction("Brew for designated poison brewing time"));
        this.addStep(new SimpleInstruction("Observe potion turning designated poison color"));

        ComplexInstruction storageSteps = new ComplexInstruction("Store the Potion");
        storageSteps.addStep(new SimpleInstruction("Cast INFRIGIDO to store potion cold"));
        storageSteps.addStep(new SimpleInstruction("Seal the vial containing the poison potion"));
        this.addStep(storageSteps);
    }
}
