package equipment.diagnostics;

import equipment.*;

public class MagicalStabilityVisitor implements EquipmentVisitor {

    @Override
    public void visit(Burner e) {
        int stability = e.getMagicalStability();
        System.out.println("Burner Stability: " + stability);
        if (stability < 20) {
            System.out.println("Warning: Burner is dangerously unstable! Consider turning it off.");
        }
    }

    @Override
    public void visit(Cauldron cauldron) {
        int stability = cauldron.getMagicalStability();
        System.out.println("Cauldron Stability: " + stability);
        if (stability < 20) {
            System.out.println("Warning: Cauldron may explode if not handled properly.");
        }
    }

    @Override
    public void visit(StirringRod rod) {
        int stability = rod.getMagicalStability();
        System.out.println("Stirring Rod Stability: " + stability);
        if (stability < 30) {
            System.out.println("Warning: Stirring rod is disrupting the potion's balance.");
        }
    }

    @Override
    public void visit(Vial vial) {
        int stability = vial.getMagicalStability();
        System.out.println("Vial Stability: " + stability);
        if (stability < 25) {
            System.out.println("Warning: Vial may leak or crack under magical pressure.");
        }
    }
}
