package lab1.builder;

import lab1.domain.models.*;
import lab1.domain.factory.Meal;
import lab1.observers.OrderObserver;
import lab1.strategies.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private Restaurant restaurant;
    private List<Meal> meals;
    private Address deliveryAddress;
    private List<OrderObserver> observers = new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    private Order(OrderBuilder builder) {
        this.customer = builder.customer;
        this.restaurant = builder.restaurant;
        this.meals = builder.meals;
        this.deliveryAddress = builder.deliveryAddress;
    }

    public void attachObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void detachObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }

    public void updateOrderStatus(String status) {
        notifyObservers("Order status updated: " + status);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment() {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy set. Unable to process payment.");
        } else {
            double totalAmount = calculateTotalAmount();
            paymentStrategy.pay(totalAmount);
        }
    }

    private double calculateTotalAmount() {
        return meals.stream().mapToDouble(Meal::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order for " + customer.getName() + " from " + restaurant.getName() +
                " with meals: " + meals.toString() +
                " to be delivered at: " + deliveryAddress.toString();
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
