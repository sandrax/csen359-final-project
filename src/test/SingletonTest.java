package test;

import commands.Wand;

public class SingletonTest {
    public static void main(String[] args) {
        Wand wand1 = Wand.getInstance();
        Wand wand2 = Wand.getInstance();

        System.out.println("wand1 == wand2 ? " + (wand1 == wand2));
    }
}
