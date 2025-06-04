package commands;

import equipment.StirringRod;

public class StirCommand implements Command {
    private StirringRod rod;
    private StirringRod.Direction direction;
    private int durationInSeconds;

    public StirCommand(StirringRod rod, StirringRod.Direction direction, int durationInSeconds) {
        this.rod = rod;
        this.direction = direction;
        this.durationInSeconds = durationInSeconds;
    }

    @Override
    public void execute() {
        System.out.println("[Wand]: Casting stir command...");
        rod.stir(direction, durationInSeconds);
    }
}