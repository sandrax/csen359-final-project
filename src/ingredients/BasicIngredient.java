package ingredients;

public abstract class BasicIngredient implements Ingredient {
    protected String name;

    // geminio is the spell to clone an object
    public abstract BasicIngredient geminio();

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
