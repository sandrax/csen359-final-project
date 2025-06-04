package ingredients;

public class OccamyEgg extends BasicIngredient {
	
	public OccamyEgg() {
		this.name = "occamy egg";
	}
	
	@Override
	public OccamyEgg geminio() {
		return new OccamyEgg();
	}
}
