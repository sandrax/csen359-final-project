package test;

import discipline.DeputyHeadmaster;
import discipline.DisciplinaryReport;
import discipline.Faculty;
import discipline.HeadOfHouse;
import discipline.Headmaster;
import discipline.ReportLevel;
import teaching.AdvancedStudent;
import teaching.Student;

public class ChainOfResponsibilityTest {
    public static void main(String[] args) {
        // test chain of responsibility
        System.out.println("\n--- Filing a Disciplinary Report ---\n");

        Faculty ghoh = new HeadOfHouse("Gryffindor");
        Faculty shoh = new HeadOfHouse("Slytherin");
        Faculty dep = new DeputyHeadmaster();
        Faculty hm = new Headmaster();

        Student won = new AdvancedStudent("Won", "Reasely", "Slytherin", 4);
        DisciplinaryReport report = new DisciplinaryReport(won, ReportLevel.MINOR);
        ghoh.handleReport(report);
        shoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.MAJOR);
        ghoh.handleReport(report);

        System.out.println("---------");

        ghoh.setSuperior(dep);
        ghoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.SEVERE);
        ghoh.handleReport(report);

        System.out.println("---------");

        dep.setSuperior(hm);
        dep.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.ILLEGAL);
        ghoh.handleReport(report);

        System.out.println("---------");

        report = new DisciplinaryReport(won, ReportLevel.UNCATEGORIZED);
        ghoh.handleReport(report);
    }
}
