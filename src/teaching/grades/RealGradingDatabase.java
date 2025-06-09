package teaching.grades;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class RealGradingDatabase implements GradingDatabase {
    private static RealGradingDatabase instance;

    private Set<String> students = new HashSet<>();
    private Map<GradeEntry, Double> grades = new HashMap<>();

    private RealGradingDatabase() {}

    public static synchronized RealGradingDatabase getInstance() {
        if (instance == null) {
            instance = new RealGradingDatabase();
        }
        return instance;
    }

    @Override
    public void setGrade(String pwd, String student, String course, double grade) {
        if (student == null) {
            throw new IllegalArgumentException("INVALID NAME: No student name provided.");
        }
        if (course == null) {
            throw new IllegalArgumentException("INVALID COURSE: No course name provided.");
        }
        students.add(student);
        grades.put(new GradeEntry(student, course), grade);
    }

    @Override
    public void viewGrade(String pwd, String student, String course) {
        if (student == null) {
            throw new IllegalArgumentException("INVALID NAME: No student name provided.");
        }
        if (course == null) {
            throw new IllegalArgumentException("INVALID COURSE: No course name provided.");
        }
        System.out.println(student + ": " + course + " - " + grades.get(new GradeEntry(student, course)));
    }

    @Override
    public void viewStudent(String pwd, String student) {
        if (student == null) {
            throw new IllegalArgumentException("INVALID NAME: No student name provided.");
        }
        System.out.println(student + ":");
        for (GradeEntry key : grades.keySet()) {
            if (key.getStudent().equals(student)) {
                System.out.println("    " + key.getCourse() + " - " + grades.get(key));
            }
        }
    }

    @Override
    public void viewCourse(String pwd, String course) {
        if (course == null) {
            throw new IllegalArgumentException("INVALID COURSE: No course name provided.");
        }
        System.out.println(course + ":");
        for (GradeEntry key : grades.keySet()) {
            if (key.getCourse().equals(course)) {
                System.out.println("    " + key.getStudent() + " - " + grades.get(key));
            }
        }
    }

    @Override
    public void viewAll(String pwd) {
        System.out.println("\n--- Complete Student Grade Report ---\n");
        for (String s: students) {
            this.viewStudent(pwd, s);
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
