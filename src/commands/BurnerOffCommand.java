package commands;

import equipment.Burner;

public class BurnerOffCommand implements Command {
    private Burner burner;

    public BurnerOffCommand(Burner burner) {
        this.burner = burner;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting burner OFF command...");
        burner.setHeatLevel(Burner.HeatLevel.OFF);
    }
}
