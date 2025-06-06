package teaching;

import java.util.ArrayList;
import java.util.List;

import teaching.iterator.BasicStudentIterator;
import teaching.iterator.StudentIterator;

public class BasicStudentCollection implements StudentCollection{
    private List<BasicStudent> students = new ArrayList<>();

    public void addStudent(BasicStudent student) {
        students.add(student);
    }

    public StudentIterator getIterator() {
        return new BasicStudentIterator(students);
    }
}
