package equipment;

import java.util.Random;

import equipment.diagnostics.EquipmentVisitor;

public class Vial implements Equipment{
    private boolean sealed = false;
    private String contents = ""; // this can be changed to a Potion later
    private int magicalStability = 100;

    private final Random random = new Random();

    public void fill(String potion) {
        this.contents = potion;
        magicalStability = Math.max(1, magicalStability - (5 + random.nextInt(5)));
        System.out.println("Vial filled with: " + potion);
    }

    public void seal() {
        sealed = true;
        magicalStability = Math.min(100, magicalStability + (3 + random.nextInt(5)));
        System.out.println("Vial sealed.");
    }

    public void unseal() {
        sealed = false;
        magicalStability = Math.max(1, magicalStability - (3 + random.nextInt(5)));
        System.out.println("Vial unsealed.");
    }

    public boolean isSealed() {
        return sealed;
    }

    public String getContents() {
        return contents;
    }

    public int getMagicalStability() {
        return magicalStability;
    }

    @Override
    public void accept(EquipmentVisitor visitor) {
         visitor.visit(this);
    }
}
