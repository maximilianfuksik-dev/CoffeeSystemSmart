package de.gfn.coffeesystemsmart.Classes;

public class CoffeeEntity  implements Coffee{

    private final int id;  // CoffeeTypeID

    private final String name;

    private final double price; // CofffeePrice


    CoffeeEntity(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getMLiter() { //<-- ohne Funktion bis jetzt
        return 0;
    }
}
