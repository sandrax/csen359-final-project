package teaching;

import java.util.List;

import equipment.Burner;
import equipment.Cauldron;
import equipment.StirringRod;
import equipment.Vial;
import teaching.instructions.ComplexInstruction;
import teaching.instructions.HealingPotionInstructions;
import teaching.instructions.InvisibilityPotionInstructions;

// Facade class that handles lab setup and instruction reading for potion lessons for the user (student). 
public class Professor {
    private String name;

    // lab equipment
    private Burner burner;
    private Cauldron cauldron;
    private StirringRod rod;
    private Vial vial;

    public Professor(String name, Burner burner, Cauldron cauldron, StirringRod rod, Vial vial) {
        this.name = name;
        this.burner = burner;
        this.cauldron = cauldron;
        this.rod = rod;
        this.vial = vial;
    }

    public void conductLesson(LessonType lessonType) {
        System.out.println("Professor " + name + " is starting the lesson.\n");
        readInstructions(lessonType);
        setupLab();
        // can change this print statement to lesson completed or something if it makes more sense for the demo
        System.out.println("Lesson ready. Professor " + name + " begins teaching."); 
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
    
    public FullStudentCollection createRoster(List<Student> students) {
        FullStudentCollection roster = new FullStudentCollection();

        for (Student s : students) {
            if (s instanceof BasicStudent bs) {
                roster.addBasicStudent(bs);
            } else if (s instanceof AdvancedStudent as) {
                roster.addAdvancedStudent(as);
            }
        }
        return roster;
    }

    public void addStudent(Student s, FullStudentCollection roster) {
        if (s instanceof BasicStudent bs) {
            roster.addBasicStudent(bs);
        } else if (s instanceof AdvancedStudent as) {
            roster.addAdvancedStudent(as);
        }
        System.out.println("Added " + s.getName() + " to the roster.");
    }
}
