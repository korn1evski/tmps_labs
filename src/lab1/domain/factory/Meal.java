package lab1.domain.factory;

public abstract class Meal {
    String name;
    double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}