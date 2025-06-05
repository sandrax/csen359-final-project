package discipline;

public class DeputyHeadmaster implements Faculty {
    private Faculty superior;

    @Override
    public void setSuperior(Faculty superior) {
        this.superior = superior;
    }

    @Override
    public void handleReport(DisciplinaryReport report) {
        System.out.print("[Deputy Headmaster]: ");
        if (report.getSeverity().isBetterThan(ReportLevel.MAJOR)) {
            System.out.println("15 points from " + report.getStudentHouse() + "! Next!");
        }
        else if (report.getSeverity() == ReportLevel.MAJOR) {
            System.out.println("100 points from " + report.getStudentHouse() + "! And detention for two weeks!");
        }
        else {
            System.out.println("The Headmaster must know of this!");
            if (this.superior != null) {
                this.superior.handleReport(report);
            }
        }
    }
}
