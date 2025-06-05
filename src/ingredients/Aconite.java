package ingredients;

public class Aconite extends BasicIngredient {
    protected String part;

    public Aconite(String part) {
        this.name = "aconite";
        this.part = part;
    }

    @Override
    public Aconite geminio() {
        return new Aconite(this.part);
    }

    @Override
    public String toString() {
        return this.name + " " + this.part;
    }
}
