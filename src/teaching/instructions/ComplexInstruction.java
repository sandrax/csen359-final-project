package teaching.instructions;

import java.util.ArrayList;
import java.util.List;

public class ComplexInstruction implements PotionInstruction {
    private String description;
    private List<PotionInstruction> steps = new ArrayList<>();

    public ComplexInstruction(String description) {
        this.description = description;
    }

    @Override
    public void display() {
        // Print the outermost title without a number
        System.out.println("--- " + description + " ---");

        for (int i = 0; i < steps.size(); i++) {
            PotionInstruction step = steps.get(i);
            String subPrefix = (i + 1) + "";
            step.display("  " + subPrefix);
        }
    }

    @Override
    public void display(String prefix) {
        System.out.println(prefix + ". " + description);

        for (int i = 0; i < steps.size(); i++) {
            PotionInstruction step = steps.get(i);
            String subPrefix = prefix + "." + (i + 1);
            step.display("  " + subPrefix);
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public void addStep(PotionInstruction step) {
        steps.add(step);
    }

    public void removeStep(PotionInstruction step) {
        steps.remove(step);
    }
}
