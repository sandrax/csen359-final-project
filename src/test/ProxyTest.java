package test;

import teaching.grades.*;

public class ProxyTest {
    public static void main(String[] args) {
        RealGradingDatabase db = new RealGradingDatabase();

        GradingDatabase staff = new StaffPortal(db);
        GradingDatabase student = new StudentPortal(db);

        // staff
        System.out.println("STAFF TESTING - Snape");

        staff.setGrade("28741", "Ron", "Potions", 57.55);
        staff.setGrade("28741", "Harry", "Potions", 32.39);
        staff.setGrade("28741", "Harry", "Charms", 89.00);
        staff.setGrade("28741", "Ron", "Charms", 78.00);
        staff.setGrade("28741", "Harry", "DADA", 98.00);
        staff.setGrade("28741", "Draco", "Potions", 99.11);
        staff.setGrade("28741", "Hermione", "Potions", 100.00);
        staff.setGrade("28741", "Hermione", "DADA", 91.42);

        System.out.println("\n--- View A Student (Hermione) ---");
        staff.viewStudent("28741", "Hermione");

        System.out.println("\n--- View A Grade (Ron) ---");
        staff.viewGrade("28741", "Ron", "Potions");

        System.out.println("\n--- View A Course (Potions) ---");
        staff.viewCourse("28741", "Potions");

        staff.viewAll("28741");

        // student
        System.out.println("STUDENT TESTING - Harry");

        System.out.println("\n--- View A Student (Hermione) ---");
        student.viewStudent("478", "Hermione");

        System.out.println("\n--- View A Grade (Ron) ---");
        student.viewGrade("478", "Ron", "Potions");

        System.out.println("\n--- View A Student (Harry) ---");
        student.viewStudent("478", "Harry");

        System.out.println("\n--- View A Grade (Harry) ---");
        student.viewGrade("478", "Harry", "Potions");
    }
}
