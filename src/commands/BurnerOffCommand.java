package commands;

import equipment.Burner;
import equipment.Cauldron;

public class BurnerOffCommand implements Command {
    private Burner burner;
    private Cauldron cauldron;

    public BurnerOffCommand(Burner burner, Cauldron cauldron) {
        this.burner = burner;
        this.cauldron = cauldron;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting burner OFF command...");
        burner.setHeatLevel(Burner.HeatLevel.OFF);
        cauldron.unheat();
    }
}
