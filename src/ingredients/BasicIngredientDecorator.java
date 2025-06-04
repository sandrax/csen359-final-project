package ingredients;

public abstract class BasicIngredientDecorator extends BasicIngredient {
	protected BasicIngredient ingredient;
	
	public BasicIngredientDecorator(BasicIngredient ingredient) {
		this.ingredient = ingredient;
	}
}
