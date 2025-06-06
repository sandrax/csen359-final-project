package test;

import teaching.AdvancedStudent;
import teaching.AdvancedStudentCollection;
import teaching.BasicStudent;
import teaching.BasicStudentCollection;
import teaching.GradeCalculator;

public class IteratorTest {
    public static void main(String[] args) {
         BasicStudentCollection basicStudents = new BasicStudentCollection();
        basicStudents.addStudent(new BasicStudent("Neville", "Longbottom", "Gryffindor", 1, 64, 80, 95));
        basicStudents.addStudent(new BasicStudent("Seamus", "Finnigan", "Gryffindor", 2, 45, 67, 90));

        AdvancedStudentCollection advancedStudents = new AdvancedStudentCollection();
        advancedStudents.addStudent(new AdvancedStudent("Hermione", "Granger", "Gryffindor", 4, 95, 90));

        GradeCalculator grades = new GradeCalculator();
        grades.calculateGrades(basicStudents);
        grades.calculateGrades(advancedStudents);
    }
}
