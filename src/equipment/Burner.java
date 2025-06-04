package equipment;

import java.util.Random;

import equipment.diagnostics.EquipmentVisitor;

public class Burner implements Equipment {
    public enum HeatLevel {
        OFF, LOW, MEDIUM, HIGH
    }

    private HeatLevel heatLevel = HeatLevel.OFF;
    private int magicalStability = 100;

    private final Random random = new Random();

    public void setHeatLevel(HeatLevel level) {
        this.heatLevel = level;
        switch (heatLevel) {
            case LOW -> magicalStability -= 10;
            case MEDIUM -> magicalStability -= 20;
            case HIGH -> magicalStability -= 40;
            default -> {
            }
        }
        magicalStability -= random.nextInt(5);
        magicalStability = Math.max(1, magicalStability);
        System.out.println("Burner set to: " + level);
    }

    public HeatLevel getHeatLevel() {
        return heatLevel;
    }

    public int getMagicalStability() {
        return magicalStability;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
        visitor.visit(this);
    }
}