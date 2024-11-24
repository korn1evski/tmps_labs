package lab1.client;

import lab1.builder.Order;
import lab1.domain.factory.MealType;
import lab1.domain.models.*;
import lab1.domain.factory.Meal;
import lab1.facade.OrderingFacade;
import lab1.commands.*;
import lab1.observers.CustomerObserver;
import lab1.strategies.*;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public void placeOrder() {
        OrderingFacade orderingFacade = new OrderingFacade();

        // Adding restaurants using the facade
        orderingFacade.addRestaurant("Pizza Palace");
        orderingFacade.addRestaurant("Burger Barn");
        orderingFacade.addRestaurant("Pasta Place");

        System.out.println("Available Restaurants:");
        for (Restaurant restaurant : orderingFacade.getAvailableRestaurants()) {
            System.out.println(restaurant.getName());
        }

        // Creating meals using the facade
        Meal pizza = orderingFacade.createMeal(MealType.PIZZA);
        Meal burger = orderingFacade.createMeal(MealType.BURGER);

        // Getting or creating a customer using the facade
        Customer customer = orderingFacade.getOrCreateCustomer("Jane", "Doe");

        // Creating address using the facade
        Address address = orderingFacade.createAddress(456, "Elm St", "Foodtown");

        // Selecting a restaurant
        Restaurant selectedRestaurant = orderingFacade.getAvailableRestaurants().get(1);

        // Creating an order using the facade
        List<Meal> meals = new ArrayList<>();
        meals.add(pizza);
        meals.add(burger);
        Order order = orderingFacade.createOrder(customer, selectedRestaurant, meals, address);

        // Attach a customer observer to the order
        CustomerObserver customerObserver = new CustomerObserver(customer);
        order.attachObserver(customerObserver);

        // Update order status
        order.updateOrderStatus("Preparing");

        // Set payment strategy and make payment
        PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9012-3456", "Jane Doe");
        order.setPaymentStrategy(paymentStrategy);
        order.makePayment();

        // Placing an order using command pattern
        OrderCommand placeOrderCommand = new PlaceOrderCommand(orderingFacade, order);
        OrderInvoker invoker = new OrderInvoker();
        invoker.setCommand(placeOrderCommand);
        invoker.executeCommand();

        // Canceling an order using command pattern
        OrderCommand cancelOrderCommand = new CancelOrderCommand("Order123");
        invoker.setCommand(cancelOrderCommand);
        invoker.executeCommand();
    }
}
