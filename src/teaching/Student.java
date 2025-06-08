package teaching;

public abstract class Student {
    private String firstName;
    private String lastName;
    private String house;
    private int year;
    // variables for student grade calculation for ITERATOR
    // can change or add logic later if needed
    protected Integer stabilityScore;
    protected Integer potionComplexity;

    public Student(String firstName, String lastName, String house, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.house = house;
        this.year = year;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getHouse() {
        return this.house;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStabilityScore() {
        return stabilityScore;
    }

    public void setStabilityScore(int score) {
        this.stabilityScore = score;
    }

    public int getPotionComplexity() {
        return potionComplexity;
    }

    public void setPotionComplexity(int complexity) {
        this.potionComplexity = complexity;
    }

    public abstract double calculateGrade();

    public String describe() {
        return String.format("%s %s - %s, Year %d", firstName, lastName, house, year);
    }

}
