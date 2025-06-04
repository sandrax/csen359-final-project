package commands;

public class Wand {

    private static Wand instance;
    private Command currentCommand;

    private Wand() {
    }

    public static synchronized Wand getInstance() {
        if (instance == null) {
            instance = new Wand();
        }
        return instance;
    }

    public void setCommand(Command command) {
        this.currentCommand = command;
    }

    public void cast() {
        if (currentCommand != null) {
            currentCommand.execute();
            currentCommand = null; // Clear after use
        } else {
            System.out.println("[Wand]: No command set.");
        }
    }
}
