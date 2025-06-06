package teaching.iterator;

import java.util.List;

import teaching.BasicStudent;
import teaching.Student;

public class BasicStudentIterator implements StudentIterator {
    private List<BasicStudent> students;
    private int index = 0;

    public BasicStudentIterator(List<BasicStudent> students) {
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
