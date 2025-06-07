package test;

import equipment.*;
import teaching.*;

public class FacadeTest {
    public static void main(String[] args) {
        Cauldron cauldron = new Cauldron();
        Burner burner = new Burner();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();

        TeachingAssistant ta = new TeachingAssistant(cauldron, burner, rod, vial);
        Professor professor = new Professor("Snape", ta);

        professor.conductLesson(LessonType.HEALING);
        System.out.println();
        professor.conductLesson(LessonType.INVISIBILITY);
    }
}
