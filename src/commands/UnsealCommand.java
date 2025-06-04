package commands;

import equipment.Vial;

public class UnsealCommand implements Command {
    private Vial vial;

    public UnsealCommand(Vial vial) {
        this.vial = vial;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting unseal command...");
        vial.unseal();
    }
}
