package discipline;

public class Headmaster implements Faculty {
    private Faculty superior;

    @Override
    public void setSuperior(Faculty superior) {
        this.superior = superior;
    }

    @Override
    public void handleReport(DisciplinaryReport report) {
        System.out.print("[Headmaster]: ");
        if (report.getSeverity().isBetterThan(ReportLevel.SEVERE)) {
            System.out.println("Perhaps 15 points from " + report.getStudentHouse() + " and an essay of reflection.");
        }
        else if (report.getSeverity() == ReportLevel.SEVERE) {
            System.out.println("500 points from " + report.getStudentHouse() + " and three months of detention.");
        }
        else if (report.getSeverity() == ReportLevel.ILLEGAL) {
            System.out.println("Unfortunately, this must be reported to the Ministry. There is a possibility of expulsion.");
        }
        else {
            if (this.superior != null) {
                System.out.print("It's out of my hands.");
                this.superior.handleReport(report);
            }
            else {
                System.out.println("... I will figure something out...");
            }
        }
    }
}
