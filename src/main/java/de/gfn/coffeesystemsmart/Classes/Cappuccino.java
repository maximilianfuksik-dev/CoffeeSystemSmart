package de.gfn.coffeesystemsmart.Classes;

public class Cappuccino implements Coffee{

    @Override
    public String getName() {
        return "Cappuccino"; //<-- Muss noch ersetz werden
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public int getMLiter() {
        return 0;
    }

    @Override
    public void brew() {

    }
}
