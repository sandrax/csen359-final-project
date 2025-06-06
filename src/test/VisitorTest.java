package test;

import java.util.List;

import equipment.*;
import equipment.StirringRod.Direction;
import equipment.diagnostics.MagicalStabilityVisitor;

public class VisitorTest {
    public static void main(String[] args) {
        Burner burner = new Burner();
        Cauldron cauldron = new Cauldron();
        StirringRod rod = new StirringRod();
        Vial vial = new Vial();

        burner.setHeatLevel(Burner.HeatLevel.LOW);
        burner.setHeatLevel(Burner.HeatLevel.MEDIUM);

        cauldron.heat(Burner.HeatLevel.LOW);
        cauldron.heat(Burner.HeatLevel.MEDIUM);
        cauldron.addIngredient("aconite");
        cauldron.addIngredient("dragon blood");

        rod.stir(Direction.CLOCKWISE, 4);
        rod.stir(Direction.COUNTERCLOCKWISE, 4);
        rod.stir(Direction.CLOCKWISE, 4);
        rod.stir(Direction.COUNTERCLOCKWISE, 4);
        rod.stir(Direction.CLOCKWISE, 4);
        rod.stir(Direction.COUNTERCLOCKWISE, 4);
        rod.stir(Direction.CLOCKWISE, 4);

        vial.fill("potion");
        vial.seal();

        List<Equipment> equipment = List.of(burner, cauldron, rod, vial);
        MagicalStabilityVisitor magicalStabilityVisitor = new MagicalStabilityVisitor();

        for (Equipment e : equipment) {
            e.accept(magicalStabilityVisitor);
        }
    }
}
