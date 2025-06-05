package ingredients;

public class Powdered extends BasicIngredientDecorator {

    public Powdered(BasicIngredient ingredient) {
        super(ingredient);
        this.name = "powdered " + ingredient.getName();
    }

    @Override
    public BasicIngredient geminio() {
        BasicIngredient cloned = this.ingredient.geminio();
        return new Powdered(cloned);
    }
}
