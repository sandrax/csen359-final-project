package test;

import ingredients.DragonBlood;
import ingredients.Extract;
import ingredients.HelleboreSyrup;
import ingredients.LiquidIngredient;
import ingredients.Purify;

public class BridgeTest {
    public static void main(String[] args) {
        // test bridge
        LiquidIngredient base = new DragonBlood(new Extract(), new Purify());
        base.prepare();

        base = new HelleboreSyrup(new Extract(), new Purify());
        base.prepare();
    }
}
