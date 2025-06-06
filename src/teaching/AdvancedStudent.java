package teaching;

public class AdvancedStudent extends Student {

    public AdvancedStudent(String firstName, String lastName, String house, int year) {
        super(firstName, lastName, house, year);
    }

    public AdvancedStudent(String firstName, String lastName, String house, int year, int potionComplexity, int stabilityScore) {
        super(firstName, lastName, house, year);
        this.potionComplexity = potionComplexity;
        this.stabilityScore = stabilityScore;
    }

    @Override
    public double calculateGrade() {
        if (potionComplexity == null || stabilityScore == null) {
            System.out.println("[Warning] potion complexity or stability score has not been set yet.");
            return 0;
        }
        double grade =  0.6 * potionComplexity + 0.4 * stabilityScore;
        return (Math.round(grade * 100.0) / 100.0);
    }

}
