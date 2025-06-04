package commands;

import equipment.Vial;

public class FillVialCommand implements Command {

    private Vial vial;
    private String potion; // later can become a Potion object

    public FillVialCommand(Vial vial, String potionName) {
        this.vial = vial;
        this.potion = potionName;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Filling vial with " + potion + ".");
        vial.fill(potion);
    }
}
