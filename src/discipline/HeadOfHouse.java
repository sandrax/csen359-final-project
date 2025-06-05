package discipline;

public class HeadOfHouse implements Faculty {
    private Faculty superior;
    public String house;

    public HeadOfHouse(String house) {
        this.house = house;
    }

    @Override
    public void setSuperior(Faculty superior) {
        this.superior = superior;
    }

    @Override
    public void handleReport(DisciplinaryReport report) {
        System.out.print("[" + this.house + " Head of House]: ");
        if (report.getSeverity() == ReportLevel.MINOR) {
            if (this.house.equals(report.getStudentHouse())) {
                System.out.println("From my own house? Well... 5 points from " + this.house + " then.");
            }
            else {
                System.out.println("Ha! 25 points from " + report.getStudentHouse() + ".");
            }
        }
        else {
            if (this.superior != null) {
                System.out.println("This is rather serious...");
                this.superior.handleReport(report);
            }
            else {
                System.out.println("Pending further action...");
            }
        }
    }
}
