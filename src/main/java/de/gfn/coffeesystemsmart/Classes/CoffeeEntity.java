package de.gfn.coffeesystemsmart.Classes;

public class CoffeeEntity implements Coffee{

    private int id;  // CoffeeTypeID

    private String name;

    private double price; // CofffeePrice

    private int mLiter;

    public CoffeeEntity(){}

    public CoffeeEntity(int id, String name, double price, int mLiter) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mLiter = mLiter;
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



    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getmLiter() {
        return mLiter;
    }

    public void setmLiter(int mLiter) {
        this.mLiter = mLiter;
    }
}
