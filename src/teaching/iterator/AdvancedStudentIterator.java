package teaching.iterator;

import teaching.AdvancedStudent;
import teaching.Student;

import java.util.LinkedList;

public class AdvancedStudentIterator implements StudentIterator {
    private LinkedList<AdvancedStudent> students;
    private int index = 0;

    public AdvancedStudentIterator(LinkedList<AdvancedStudent> students) {
        this.students = students;
    }

    @Override
    public boolean hasNext() {
        return index < students.size();
    }

    @Override
    public Student next() {
        return students.get(index++);
    }
}
