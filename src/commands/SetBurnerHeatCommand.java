package commands;

import equipment.Burner;
import equipment.Cauldron;

public class SetBurnerHeatCommand implements Command {
    private Burner burner;
    private Cauldron cauldron;
    private Burner.HeatLevel level;

    public SetBurnerHeatCommand(Burner burner, Cauldron cauldron, Burner.HeatLevel level) {
        this.burner = burner;
        this.cauldron = cauldron;
        this.level = level;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting burner heat command...");
        burner.setHeatLevel(level);
        cauldron.heat(level);
    }
}
