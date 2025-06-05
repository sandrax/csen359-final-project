package ingredients;

public class Moonstone extends BasicIngredient {

    public Moonstone() {
        this.name = "moonstone";
    }

    @Override
    public Moonstone geminio() {
        return new Moonstone();
    }
}
