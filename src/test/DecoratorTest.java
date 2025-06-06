package test;

import ingredients.Aconite;
import ingredients.BasicIngredient;
import ingredients.Diced;
import ingredients.KidneyBean;
import ingredients.Mandrake;
import ingredients.Moonstone;
import ingredients.OccamyEgg;
import ingredients.Powdered;

public class DecoratorTest {
    public static void main(String[] args) {
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

        // test decorator (combined with prototype)
        BasicIngredient i = new Powdered(new Moonstone());
        System.out.println(i);
        BasicIngredient i2 = i.geminio();
        System.out.println("duplicate powdered? " + (i != i2));

        i = new Diced(new KidneyBean("red"));
        System.out.println(i);
        i2 = i.geminio();
        System.out.println("duplicate diced? " + (i != i2));
    }
}
