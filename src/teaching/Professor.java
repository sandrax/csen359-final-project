package teaching;

import java.util.List;

import equipment.Burner;
import equipment.Cauldron;
import equipment.StirringRod;
import equipment.Vial;
import potions.template.AbstractPotionBrewing;
import potions.template.HealingPotionBrewing;
import potions.template.InvisibilityPotionBrewing;
import potions.template.PoisonPotionBrewing;
import teaching.grades.GradingDatabase;
import teaching.grades.RealGradingDatabase;
import teaching.grades.StaffPortal;
import teaching.instructions.ComplexInstruction;
import teaching.instructions.HealingPotionInstructions;
import teaching.instructions.InvisibilityPotionInstructions;
import teaching.instructions.PoisonPotionInstructions;

// Facade class that handles lab setup and instruction reading for potion lessons for the user (student). 
public class Professor {
    private String name;

    // lab equipment
    private Burner burner;
    private Cauldron cauldron;
    private StirringRod rod;
    private Vial vial;

    private final GradingDatabase gradingPortal ;

    public Professor(String name, Burner burner, Cauldron cauldron, StirringRod rod, Vial vial) {
        this.name = name;
        this.burner = burner;
        this.cauldron = cauldron;
        this.rod = rod;
        this.vial = vial;
        this.gradingPortal = new StaffPortal(new RealGradingDatabase());
    }

    public void conductLesson(LessonType lessonType) {
        System.out.println("Professor " + name + " is starting the lesson.\n");
        readInstructions(lessonType);
        setupLab();
        // can change this print statement to lesson completed or something if it makes
        // more sense for the demo
        System.out.println("Lesson ready. Professor " + name + " begins teaching.");
        demoPotion(lessonType);
    }

    public void readInstructions(LessonType lessonType) {
        System.out.println("Reading potion instructions...");
        ComplexInstruction instructions = switch (lessonType) {
            case INVISIBILITY -> new InvisibilityPotionInstructions();
            case HEALING -> new HealingPotionInstructions();
            case POISON -> new PoisonPotionInstructions();
        };
        instructions.display();
        System.out.println();
    }

    public void demoPotion(LessonType lessonType) {
        AbstractPotionBrewing demo = switch (lessonType) {
            case INVISIBILITY -> new InvisibilityPotionBrewing();
            case HEALING -> new HealingPotionBrewing();
            case POISON -> new PoisonPotionBrewing();
        };
        demo.makePotion();
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

    public void assignGrade(String pwd, String student, String course, double grade) {
        gradingPortal.setGrade(pwd, student, course, grade);
    }

    public void viewStudentGrades(String pwd, String student) {
        gradingPortal.viewStudent(pwd, student);
    }

    public void viewCourseGrades(String pwd, String course) {
        gradingPortal.viewCourse(pwd, course);
    }

    public void viewSpecificGrade(String pwd, String student, String course) {
        gradingPortal.viewGrade(pwd, student, course);
    }

    public void viewAllGrades(String pwd) {
        gradingPortal.viewAll(pwd);
    }
}
