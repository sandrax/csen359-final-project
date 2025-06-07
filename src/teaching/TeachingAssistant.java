package teaching;

import teaching.instructions.ComplexInstruction;
import teaching.instructions.HealingPotionInstructions;
import teaching.instructions.InvisibilityPotionInstructions;
import equipment.*;

// Facade class that handles lab setup and instruction reading for potion lessons. 
public class TeachingAssistant {
    private final Cauldron cauldron;
    private final Burner burner;
    private final StirringRod rod;
    private final Vial vial;

    public TeachingAssistant(Cauldron cauldron, Burner burner, StirringRod rod, Vial vial) {
        this.cauldron = cauldron;
        this.burner = burner;
        this.rod = rod;
        this.vial = vial;
    }

    public void readInstructions(LessonType lessonType) {
        System.out.println("Reading potion instructions...");
        ComplexInstruction instructions = switch (lessonType) {
            case INVISIBILITY -> new InvisibilityPotionInstructions();
            case HEALING -> new HealingPotionInstructions();
        };
        instructions.display();
        System.out.println();
    }

    public void setupLab() {
        System.out.println("Setting up lab equipment...");
        cauldron.clean();
        burner.ignite();
        rod.sanitize();
        vial.unseal();
        System.out.println("Lab setup complete.\n");
    }

    public void prepareLesson(LessonType lessonType) {
        readInstructions(lessonType);
        setupLab();
    }
}
