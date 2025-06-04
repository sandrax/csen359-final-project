package ingredients;

public class HelleboreSyrup extends LiquidIngredient {
	
	public HelleboreSyrup(LiquidPrep e, LiquidPrep p) {
		super(e, p);
		this.name = "hellebore syrup";
	}
	
	@Override
	public void prepare() {
		System.out.println("Obtaining fresh " + this.name + "...");
		this.extract.castSpell();
		this.purify.castSpell();
	}
}
