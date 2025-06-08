package teaching.grades;

import teaching.FullStudentCollection;
import teaching.Student;
import teaching.StudentCollection;
import teaching.iterator.StudentIterator;

public class GradeCalculator {
    public void calculateGrades(StudentCollection studentCollection) {
        System.out.println("--- " + studentCollection.getType() + " Student Grades ---" );
        StudentIterator iterator = studentCollection.getIterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            System.out.println(s.getName() + ": " + s.calculateGrade());
        }
    }

    public void calculateGrades(FullStudentCollection roster) {
        System.out.println("--- Student Grade Report ---");
        calculateGrades(roster.getBasicStudents());
        calculateGrades(roster.getAdvancedStudents());
    }
}
