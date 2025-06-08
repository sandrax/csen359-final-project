package teaching.grades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class StaffPortal implements GradingDatabase {
    private RealGradingDatabase db;

    public StaffPortal(RealGradingDatabase db) {
        this.db = db;
    }

    public boolean hasAccess(String pwd) {
        try (InputStream inputStream = GradingDatabase.class.getResourceAsStream("GradesPortalCredentials.txt");
             BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = buff.readLine()) != null) {
                if (line.contains(pwd)) {
                    return true;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Access verification cannot be completed, please contact the administrator.");
        }
        System.err.println("Invalid credentials, please try again.");
        return false;
    }

    @Override
    public void setGrade(String pwd, String student, String course, double grade) {
        if (hasAccess(pwd)) {
            if (this.db == null) {
                throw new NullPointerException("The system has not been made available yet.");
            }
            this.db.setGrade(pwd, student, course, grade);
        }
    }

    @Override
    public void viewGrade(String pwd, String student, String course) {
        if (hasAccess(pwd)) {
            if (this.db == null) {
                throw new NullPointerException("The system has not been made available yet.");
            }
            this.db.viewGrade(pwd, student, course);
        }
    }

    @Override
    public void viewStudent(String pwd, String student) {
        if (hasAccess(pwd)) {
            if (this.db == null) {
                throw new NullPointerException("The system has not been made available yet.");
            }
            this.db.viewStudent(pwd, student);
        }
    }

    @Override
    public void viewCourse(String pwd, String course) {
        if (hasAccess(pwd)) {
            if (this.db == null) {
                throw new NullPointerException("The system has not been made available yet.");
            }
            this.db.viewCourse(pwd, course);
        }
    }

    @Override
    public void viewAll(String pwd) {
        if (hasAccess(pwd)) {
            if (this.db == null) {
                throw new NullPointerException("The system has not been made available yet.");
            }
            this.db.viewAll(pwd);
        }
    }
}
