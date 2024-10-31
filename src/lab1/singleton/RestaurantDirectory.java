package lab1.singleton;

import lab1.domain.models.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantDirectory {

    private List<Restaurant> restaurantList;

    private RestaurantDirectory() {
        restaurantList = new ArrayList<>();
    }

    private static class SingletonHelper {
        private static final RestaurantDirectory INSTANCE = new RestaurantDirectory();
    }

    public static RestaurantDirectory getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public synchronized void addRestaurant(Restaurant restaurant) {
        if (restaurant != null) {
            restaurantList.add(restaurant);
            System.out.println("Restaurant added: " + restaurant.getName());
        } else {
            System.out.println("Cannot add a null restaurant.");
        }
    }

    public synchronized boolean removeRestaurant(Restaurant restaurant) {
        if (restaurant != null && restaurantList.contains(restaurant)) {
            restaurantList.remove(restaurant);
            System.out.println("Restaurant removed: " + restaurant.getName());
            return true;
        } else {
            System.out.println("Restaurant not found or is null.");
            return false;
        }
    }

    public List<Restaurant> getRestaurants() {
        return Collections.unmodifiableList(restaurantList);
    }

    public Restaurant findRestaurantByName(String name) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                return restaurant;
            }
        }
        System.out.println("Restaurant not found: " + name);
        return null;
    }
}
