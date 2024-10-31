package lab1.domain.factory;

import lab1.domain.factory.*;

public class MealFactory {
    public static Meal createMeal(MealType type) {
        switch (type) {
            case PIZZA:
                return new Pizza();
            case BURGER:
                return new Burger();
            case PASTA:
                return new Pasta();
            default:
                throw new IllegalArgumentException("Unknown meal type: " + type);
        }
    }
}
