package teaching;

public class Student {
    private String firstName;
    private String lastName;
    private String house;
    private int year;

    public Student(String firstName, String lastName, String house, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.house = house;
        this.year = year;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
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
}
