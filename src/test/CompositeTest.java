package test;

import teaching.instructions.*;

public class CompositeTest {
    public static void main(String[] args) {
        ComplexInstruction healingPotionInstructions = new HealingPotionInstructions();
        healingPotionInstructions.display();

        ComplexInstruction customInvisibilityInstructions = new InvisibilityPotionInstructions();
        ComplexInstruction finalPackaging = new ComplexInstruction("Final Packaging Instructions");
        finalPackaging.addStep(new SimpleInstruction("Cool the potion for 10 minutes"));
        finalPackaging.addStep(new SimpleInstruction("Pour potion into vial"));
        customInvisibilityInstructions.addStep(finalPackaging);
        customInvisibilityInstructions.display();

        new SimpleInstruction("Test something standalone").display();
    }
}
