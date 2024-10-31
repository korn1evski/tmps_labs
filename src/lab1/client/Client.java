package lab1.client;

import lab1.builder.Order;
import lab1.domain.factory.MealType;
import lab1.domain.models.Address;
import lab1.domain.models.Customer;
import lab1.domain.models.Restaurant;
import lab1.domain.factory.Meal;
import lab1.domain.factory.MealFactory;
import lab1.singleton.RestaurantDirectory;

public class Client {
    public void placeOrder() {
        // Singleton: Get the single instance of RestaurantDirectory
        RestaurantDirectory restaurantDirectory = RestaurantDirectory.getInstance();
        restaurantDirectory.addRestaurant(new Restaurant("Pizza Palace"));
        restaurantDirectory.addRestaurant(new Restaurant("Burger Barn"));
        restaurantDirectory.addRestaurant(new Restaurant("Pasta Place"));

        // Display available restaurants
        System.out.println("Available Restaurants:");
        for (Restaurant restaurant : restaurantDirectory.getRestaurants()) {
            System.out.println(restaurant.getName());
        }

        // Factory Method: Create meals
        Meal pizza = MealFactory.createMeal(MealType.PIZZA);
        Meal burger = MealFactory.createMeal(MealType.BURGER);

        // Builder: Create an order
        Customer customer = new Customer("Jane Doe");
        Address address = new Address("456 Elm St", "Foodtown");
        Restaurant selectedRestaurant = restaurantDirectory.getRestaurants().get(1); // Burger Barn

        Order order = new Order.OrderBuilder(customer)
                .withRestaurant(selectedRestaurant)
                .addMeal(pizza)
                .addMeal(burger)
                .withDeliveryAddress(address)
                .build();

        // Print the order details
        System.out.println("\nOrder Details:");
        System.out.println(order);
    }
}
