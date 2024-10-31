package lab1.builder;

import lab1.domain.models.Address;
import lab1.domain.models.Customer;
import lab1.domain.models.Restaurant;
import lab1.domain.factory.Meal;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private Restaurant restaurant;
    private List<Meal> meals;
    private Address deliveryAddress;

    private Order(OrderBuilder builder) {
        this.customer = builder.customer;
        this.restaurant = builder.restaurant;
        this.meals = builder.meals;
        this.deliveryAddress = builder.deliveryAddress;
    }

    @Override
    public String toString() {
        return "Order for " + customer.getName() + " from " + restaurant.getName() + " with meals: " + meals.toString() + " to be delivered at: " + deliveryAddress.toString();
    }

    public static class OrderBuilder {
        private Customer customer;
        private Restaurant restaurant;
        private List<Meal> meals = new ArrayList<>();
        private Address deliveryAddress;

        public OrderBuilder(Customer customer) {
            this.customer = customer;
        }

        public OrderBuilder withRestaurant(Restaurant restaurant) {
            this.restaurant = restaurant;
            return this;
        }

        public OrderBuilder addMeal(Meal meal) {
            this.meals.add(meal);
            return this;
        }

        public OrderBuilder withDeliveryAddress(Address address) {
            this.deliveryAddress = address;
            return this;
        }

        public Order build() {
            if (customer == null || restaurant == null || deliveryAddress == null || meals.isEmpty()) {
                throw new IllegalStateException("Order cannot be created with missing details");
            }
            return new Order(this);
        }
    }
}
