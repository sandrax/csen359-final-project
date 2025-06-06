package test;

import ingredients.Aconite;
import ingredients.Mandrake;
import ingredients.OccamyEgg;

public class PrototypeTest {
    public static void main(String[] args) {
         // test prototype
        System.out.println("\n--- Gathering Ingredients ---\n");

        Aconite a1 = new Aconite("root");
        Aconite a2 = a1.geminio();
        System.out.println("new aconite?: " + (a1 != a2));

        Mandrake m1 = new Mandrake();
        Mandrake m2 = m1.geminio();
        System.out.println("new mandrake?: " + (m1 != m2));

        OccamyEgg o1 = new OccamyEgg();
        OccamyEgg o2 = o1.geminio();
        System.out.println("new egg?: " + (o1 != o2));
    }
}
