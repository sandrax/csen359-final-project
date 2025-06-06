package teaching;

import teaching.iterator.StudentIterator;

public class GradeCalculator {
    public void calculateGrades(StudentCollection studentCollection) {
        System.out.println("--- Student Grade Report ---");

        StudentIterator iterator = studentCollection.getIterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            System.out.println(s.getName() + ": " + s.calculateGrade());
        }
    }
}
