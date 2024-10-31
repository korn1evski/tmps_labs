package lab1.singleton;

import lab1.domain.models.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantDirectory {

    private List<Restaurant> restaurantList;
    private Cache cache;

    private RestaurantDirectory() {
        restaurantList = new ArrayList<>();
        cache = Cache.getInstance();
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
            cache.put(restaurant.getName(), restaurant.toString());
            System.out.println("Restaurant added: " + restaurant.getName());
        } else {
            System.out.println("Cannot add a null restaurant.");
        }
    }

    public synchronized boolean removeRestaurant(Restaurant restaurant) {
        if (restaurant != null && restaurantList.contains(restaurant)) {
            restaurantList.remove(restaurant);
            cache.remove(restaurant.getName());
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
        if (cache.containsKey(name)) {
            System.out.println("Retrieved from cache: " + name);
            return new Restaurant(name);
        }
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getName().equalsIgnoreCase(name)) {
                cache.put(name, restaurant.toString());
                return restaurant;
            }
        }
        System.out.println("Restaurant not found: " + name);
        return null;
    }
}
