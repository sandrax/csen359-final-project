package ingredients;

public abstract class LiquidIngredient implements Ingredient {
	protected LiquidPrep extract;
	protected LiquidPrep purify;
	public String name;
	
	protected LiquidIngredient(LiquidPrep e, LiquidPrep p) {
		this.extract = e;
		this.purify = p;
	}
	
	public abstract void prepare();
	
	public String toString() {
		return this.name + " base";
	}
}
