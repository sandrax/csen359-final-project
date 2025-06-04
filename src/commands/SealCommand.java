package commands;

import equipment.Vial;

public class SealCommand implements Command {
    private Vial vial;

    public SealCommand(Vial vial) {
        this.vial = vial;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting seal command...");
        vial.seal();
    }
}
