package lab1.client;

import lab1.builder.Order;
import lab1.domain.factory.MealType;
import lab1.domain.models.Address;
import lab1.domain.models.Customer;
import lab1.domain.models.Restaurant;
import lab1.domain.factory.Meal;
import lab1.domain.factory.MealFactory;
import lab1.singleton.RestaurantDirectory;
import lab1.singleton.Cache;

public class Client {
    public void placeOrder() {
        RestaurantDirectory restaurantDirectory = RestaurantDirectory.getInstance();
        restaurantDirectory.addRestaurant(new Restaurant("Pizza Palace"));
        restaurantDirectory.addRestaurant(new Restaurant("Burger Barn"));
        restaurantDirectory.addRestaurant(new Restaurant("Pasta Place"));

        System.out.println("Available Restaurants:");
        for (Restaurant restaurant : restaurantDirectory.getRestaurants()) {
            System.out.println(restaurant.getName());
        }

        Meal pizza = MealFactory.createMeal(MealType.PIZZA);
        Meal burger = MealFactory.createMeal(MealType.BURGER);

        Cache cache = Cache.getInstance();
        String customerName = "Jane Doe";
        Customer customer;

        if (cache.containsKey(customerName)) {
            System.out.println("Retrieved customer from cache: " + customerName);
            customer = new Customer(customerName);
        } else {
            customer = new Customer(customerName);
            cache.put(customerName, customer.toString());
        }

        Address address = new Address("456 Elm St", "Foodtown");
        Restaurant selectedRestaurant = restaurantDirectory.getRestaurants().get(1);

        Order order = new Order.OrderBuilder(customer)
                .withRestaurant(selectedRestaurant)
                .addMeal(pizza)
                .addMeal(burger)
                .withDeliveryAddress(address)
                .build();

        System.out.println("\nOrder Details:");
        System.out.println(order);
    }
}
