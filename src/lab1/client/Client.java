package lab1.client;

import lab1.builder.Order;
import lab1.domain.factory.MealType;
import lab1.domain.models.*;
import lab1.domain.factory.Meal;
import lab1.facade.OrderingFacade;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public void placeOrder() {
        OrderingFacade orderingFacade = new OrderingFacade();

        orderingFacade.addRestaurant("Pizza Palace");
        orderingFacade.addRestaurant("Burger Barn");
        orderingFacade.addRestaurant("Pasta Place");

        System.out.println("Available Restaurants:");
        for (Restaurant restaurant : orderingFacade.getAvailableRestaurants()) {
            System.out.println(restaurant.getName());
        }

        Meal pizza = orderingFacade.createMeal(MealType.PIZZA);
        Meal burger = orderingFacade.createMeal(MealType.BURGER);

        Customer customer = orderingFacade.getOrCreateCustomer("Jane", "Doe");
        Customer customer2 = orderingFacade.getOrCreateCustomer("Jane", "Doe");

        Address address = orderingFacade.createAddress(456, "Elm St", "Foodtown");

        Restaurant selectedRestaurant = orderingFacade.getAvailableRestaurants().get(1);

        List<Meal> meals = new ArrayList<>();
        meals.add(pizza);
        meals.add(burger);
        Order order = orderingFacade.createOrder(customer, selectedRestaurant, meals, address);

        System.out.println("\nOrder Details:");
        System.out.println(order);
    }
}
