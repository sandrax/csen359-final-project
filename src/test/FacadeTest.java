package test;

import equipment.*;
import teaching.*;

public class FacadeTest {
    public static void main(String[] args) {
        Burner burner = new Burner();
        Cauldron cauldron = new Cauldron();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();

        Professor professor = new Professor("Snape", burner, cauldron, rod, vial);

        professor.conductLesson(LessonType.HEALING);
        System.out.println();
        professor.conductLesson(LessonType.INVISIBILITY);
    }
}
