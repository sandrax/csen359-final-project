package ingredients;

public class KidneyBean extends BasicIngredient {
	protected String color;
	
	public KidneyBean(String color) {
		this.name = color + " kidney bean";
		this.color = color;
	}
	
	@Override
	public KidneyBean geminio() {
		return new KidneyBean(this.color);
	}
	
	@Override
	public String toString() {
		return this.color + " " + this.name;
	}
}
