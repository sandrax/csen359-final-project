package ingredients;

public class DragonBlood extends LiquidIngredient {

    public DragonBlood(LiquidPrep e, LiquidPrep p) {
        super(e, p);
        this.name = "dragon blood";
    }

    @Override
    public void prepare() {
        System.out.println("Unsealing " + this.name + "...");
        this.extract.castSpell();
        this.purify.castSpell();
    }
}
