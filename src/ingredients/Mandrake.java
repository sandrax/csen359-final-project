package ingredients;

public class Mandrake extends BasicIngredient {
	
	public Mandrake() {
		this.name = "mandrake";
	}
	
	@Override
	public Mandrake geminio() {
		return new Mandrake();
	}	
}
