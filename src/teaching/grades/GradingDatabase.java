package teaching.grades;

public interface GradingDatabase {
    public void setGrade(String pwd, String student, String course, double grade);
    public void viewGrade(String pwd, String student, String course);
    public void viewStudent(String pwd, String student);
    public void viewCourse(String pwd, String course);
    public void viewAll(String pwd);
}
