package teaching;

import java.util.ArrayList;
import java.util.List;

import teaching.iterator.StudentIterator;

public class FullStudentCollection {
    private BasicStudentCollection basicStudents = new BasicStudentCollection();
    private AdvancedStudentCollection advancedStudents = new AdvancedStudentCollection();

    public void addBasicStudent(BasicStudent student) {
        basicStudents.addStudent(student);
    }

    public void addAdvancedStudent(AdvancedStudent student) {
        advancedStudents.addStudent(student);
    }

    public StudentCollection getBasicStudents() {
        return basicStudents;
    }

    public StudentCollection getAdvancedStudents() {
        return advancedStudents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("--- Basic Students ---\n");
        StudentIterator basicIt = basicStudents.getIterator();
        while (basicIt.hasNext()) {
            Student b = basicIt.next();
            sb.append(b.describe()).append("\n");
        }

        sb.append("\n--- Advanced Students ---\n");
        StudentIterator advancedIt = advancedStudents.getIterator();
        while (advancedIt.hasNext()) {
            Student a = advancedIt.next();
            sb.append(a.describe()).append("\n");
        }

        return sb.toString();
    }

}
