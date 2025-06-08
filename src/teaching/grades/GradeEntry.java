package teaching.grades;

import java.util.Objects;

public class GradeEntry {
    private String student;
    private String course;
    private double grade;

    public GradeEntry(String student, String course) {
        this.student = student;
        this.course = course;
    }

    public String getStudent() {
        return this.student;
    }

    public String getCourse() {
        return this.course;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof GradeEntry)) {
            return false;
        }

        GradeEntry o = (GradeEntry) other;
        return (o.getStudent().equals(this.student)) && (o.getCourse().equals(this.course));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.course);
    }
}
