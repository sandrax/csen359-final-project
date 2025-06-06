package teaching.instructions;

public class SimpleInstruction implements PotionInstruction {
    private String description;

    public SimpleInstruction(String description) {
        this.description = description;
    }

    @Override
    public void display() {
        // Fallback if called directly
        System.out.println("- " + description);
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + ". " + description);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
