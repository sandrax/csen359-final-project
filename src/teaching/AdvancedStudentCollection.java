package teaching;

import teaching.iterator.StudentIterator;
import teaching.iterator.AdvancedStudentIterator;

import java.util.LinkedList;

public class AdvancedStudentCollection implements StudentCollection{
    private LinkedList<AdvancedStudent> students = new LinkedList<>();

    public void addStudent(AdvancedStudent student) {
        students.add(student);
    }

    public StudentIterator getIterator() {
        return new AdvancedStudentIterator(students);
    }
}
