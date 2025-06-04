package equipment;

import java.util.Random;

import equipment.diagnostics.EquipmentVisitor;

public class StirringRod implements Equipment{    
    public enum Direction { CLOCKWISE, COUNTERCLOCKWISE }

    private int magicalStability = 100;

    private final Random random = new Random();

    public void stir(Direction direction, int durationSeconds) {
        magicalStability = Math.max(1, magicalStability - (10 + random.nextInt(5)));
        System.out.println("Stirring " + direction + " for " + durationSeconds + " seconds.");
    }

    public int getMagicalStability() {
        return magicalStability;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
         visitor.visit(this);
    }
}
