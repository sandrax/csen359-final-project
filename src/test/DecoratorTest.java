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
        System.out.println("\n--- Gathering Decorated Ingredients ---\n");

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
