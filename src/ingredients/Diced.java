package ingredients;

public class Diced extends BasicIngredientDecorator {
	
	public Diced(BasicIngredient ingredient) {
		super(ingredient);
		this.name = "diced " + ingredient.getName();
	}
	
	@Override
	public BasicIngredient geminio() {
		BasicIngredient cloned = this.ingredient.geminio();
		return new Diced(cloned);
	}
}
