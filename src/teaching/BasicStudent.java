package teaching;

public class BasicStudent extends Student {
    private Integer participation;

    public BasicStudent(String firstName, String lastName, String house, int year) {
        super(firstName, lastName, house, year);
    }

    public BasicStudent(String firstName, String lastName, String house, int year, int potionComplexity,
            int stabilityScore, int participation) {
        super(firstName, lastName, house, year);
        this.potionComplexity = potionComplexity;
        this.stabilityScore = stabilityScore;
        this.participation = participation;
    }

    public int getParticipationScore() {
        return participation;
    }

    public void setParticipationScore(int score) {
        participation = score;
    }

    @Override
    public double calculateGrade() {
        if (potionComplexity == null || stabilityScore == null || participation == null) {
            System.out.println("[Warning] potion complexity or stability score or participation has not been set yet.");
            return 0;
        }
        double grade = (0.5 * participation + 0.25 * potionComplexity + 0.25 * stabilityScore);
        return (Math.round(grade * 100.0) / 100.0);
    }
}
