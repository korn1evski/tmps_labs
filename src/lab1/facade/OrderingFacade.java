package lab1.facade;

import lab1.builder.Order;
import lab1.domain.factory.Meal;
import lab1.domain.factory.MealFactory;
import lab1.domain.factory.MealType;
import lab1.domain.models.*;
import lab1.singleton_flyweight.RestaurantDirectory;
import lab1.singleton_flyweight.Cache;

import java.util.List;

public class OrderingFacade {

    private final RestaurantDirectory restaurantDirectory;
    private final Cache cache;

    public OrderingFacade() {
        this.restaurantDirectory = RestaurantDirectory.getInstance();
        this.cache = Cache.getInstance();
    }

    public void addRestaurant(String name) {
        restaurantDirectory.addRestaurant(new Restaurant(name));
    }

    public List<Restaurant> getAvailableRestaurants() {
        return restaurantDirectory.getRestaurants();
    }

    public Customer getOrCreateCustomer(String firstName, String lastName) {
        String customerKey = firstName + lastName;
        if (cache.containsKey(customerKey)) {
            System.out.println("Retrieved customer from cache: " + firstName + " " + lastName);
            return CustomerDecorator.createCustomer(firstName, lastName);
        } else {
            Customer customer = CustomerDecorator.createCustomer(firstName, lastName);
            cache.put(customerKey, customer.toString());
            return customer;
        }
    }

    public Address createAddress(int streetNumber, String streetName, String city) {
        return AddressDecorator.createAddress(streetNumber, streetName, city);
    }

    public Meal createMeal(MealType mealType) {
        return MealFactory.createMeal(mealType);
    }

    public Order createOrder(Customer customer, Restaurant restaurant, List<Meal> meals, Address address) {
        Order.OrderBuilder builder = new Order.OrderBuilder(customer)
                .withRestaurant(restaurant)
                .withDeliveryAddress(address);

        for (Meal meal : meals) {
            builder.addMeal(meal);
        }

        return builder.build();
    }
}
