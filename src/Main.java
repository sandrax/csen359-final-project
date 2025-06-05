import java.util.List;

import commands.*;
import equipment.*;
import equipment.diagnostics.MagicalStabilityVisitor;
import ingredients.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Wand wand = Wand.getInstance();

        Burner burner = new Burner();
        Cauldron cauldron = new Cauldron();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();

        AddIngredientCommand addNettles = new AddIngredientCommand(cauldron, "nettles");
        AddIngredientCommand addPurifiedWater = new AddIngredientCommand(cauldron, "purified water");
        AddIngredientCommand addCrushedNewtEye = new AddIngredientCommand(cauldron, "crushed newt eye");
        BurnerOffCommand burnerOff = new BurnerOffCommand(burner);
        SetBurnerHeatCommand burnerLow = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.LOW);
        SetBurnerHeatCommand burnerMed = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.MEDIUM);
        SetBurnerHeatCommand burnerHigh = new SetBurnerHeatCommand(burner, cauldron, Burner.HeatLevel.HIGH);
        StirCommand stirClockwise = new StirCommand(rod, StirringRod.Direction.CLOCKWISE, 5);
        FillVialCommand fillVial = new FillVialCommand(vial, "potion");

        List<Equipment> equipment = List.of(burner, cauldron, rod, vial);
        MagicalStabilityVisitor magicalStabilityVisitor = new MagicalStabilityVisitor();

        // test wand commands

        wand.setCommand(burnerLow);
        wand.cast();

        wand.setCommand(addNettles);
        wand.cast();

        wand.setCommand(addPurifiedWater);
        wand.cast();

        wand.setCommand(stirClockwise);
        wand.cast();

        wand.setCommand(burnerMed);
        wand.cast();

        wand.setCommand(addCrushedNewtEye);
        wand.cast();

        wand.setCommand(stirClockwise);
        wand.cast();

        wand.setCommand(burnerHigh);
        wand.cast();

        wand.setCommand(stirClockwise);
        wand.cast();

        wand.setCommand(fillVial);
        wand.cast();

        wand.setCommand(burnerOff);
        wand.cast();

        System.out.println("\n--- Potion Making Complete ---\n");
        System.out.println("Running magical stability diagnostics...\n");

        // test visitor
        for (Equipment e : equipment) {
            e.accept(magicalStabilityVisitor);
        }
        
        // test prototype
        System.out.println("\n--- Gathering Some Materials ---\n");

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
		
		// test bridge
		LiquidIngredient base = new DragonBlood(new Extract(), new Purify());
		base.prepare();
		
		base = new HelleboreSyrup(new Extract(), new Purify());
		base.prepare();
    }
}
