package discipline;

import teaching.Student;

public class DisciplinaryReport {
    private ReportLevel level;
    private String student;
    private String studentHouse;
    private int studentYear;

    public DisciplinaryReport(Student student, ReportLevel level) {
        this.level = level;
        this.student = student.getName();
        this.studentHouse = student.getHouse();
        this.studentYear = student.getYear();
    }

    public ReportLevel getSeverity() {
        return this.level;
    }

    public String getStudent() {
        return this.student;
    }

    public String getStudentHouse() {
        return this.studentHouse;
    }

    public int getStudentYear() {
        return this.studentYear;
    }
}
