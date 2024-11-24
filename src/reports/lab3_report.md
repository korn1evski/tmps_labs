# *Behavioral Design Patterns*

**Author**: Cornievschi Bogdan

**Group**: FAF-222

----

## Objectives:

&ensp; &ensp; __1. Study and understand the Behavioral Design Patterns.__

&ensp; &ensp; __2. As an extension of the existing Food Ordering System, determine the new behaviors and user interactions needed.__

&ensp; &ensp; __3. Implement these behaviors using suitable behavioral design patterns.__

## Used Design Patterns:

* Observer Pattern
* Command Pattern
* Strategy Pattern

## Implementation

I added behavioural design patterns to the Food Ordering System in this iteration of my project. These patterns were selected in order to enhance user interactions, and offer flexible behaviour control. Three new design patterns were added: **Command Pattern** to encapsulate user activities, **Observer Pattern** to provide real-time notifications, and **Strategy Pattern** to enable dynamic payment method selection.

### Observer Pattern

To give users real-time notifications about updates to their order status, the **Observer Pattern** was put into place. Customers frequently want to know the status of their orders during the meal ordering process, including when they are being produced, shipped, or delivered. I accomplished this by using a `Order` class that keeps track of a list of observers (like customers) who must be informed when a status change occurs.

In this implementation, the `CustomerObserver` is notified whenever the status of the order changes. This pattern allows for easy extensibility: additional observers like restaurant staff or an admin can be added without modifying the core logic.

Code Snippet:

Here is an example of how I notify a `CustomerObserver` when the order status changes:

```java
public void updateOrderStatus(String status) {
    notifyObservers("Order status updated: " + status);
}
```

In the `Client` code, a `CustomerObserver` is attached to the order, and it receives a notification whenever the order status is updated to "Preparing," "Out for Delivery," etc. This provides a seamless, real-time update system for the customer, which greatly enhances the user experience.

### Command Pattern

To separate user activities (such making or cancelling an order) from the classes that carry them out, the **Command Pattern** was put into place. This makes it possible to encapsulate requests as objects, which is very helpful for logging actions, queuing requests, and even enabling undo capabilities.

To do this, I developed a number of command classes that implement the `OrderCommand` interface: `PlaceOrderCommand`, `CancelOrderCommand`, and `UpdateOrderCommand`. An `OrderInvoker` serves as a middleman, receiving the instructions and invoking their `execute()` methods to carry out these command objects.

This strategy greatly increased the system's extensibility. For instance, just a new command class would need to be created in order to add a new sort of action, such as `ChangeDeliveryAddressCommand`, without changing the `Order` or `Client` classes.

Code Snippet:

Below is an example of the `PlaceOrderCommand` execution through the invoker:

```java
OrderCommand placeOrderCommand = new PlaceOrderCommand(orderingFacade, order);
OrderInvoker invoker = new OrderInvoker();
invoker.setCommand(placeOrderCommand);
invoker.executeCommand();
```

This snippet shows how the client can encapsulate an order placement into a command object and let the `OrderInvoker` execute it. This decouples the client from the implementation of the ordering logic, leading to a cleaner and more modular design.

### Strategy Pattern

When placing an order, clients were able to dynamically choose from a variety of payment options thanks to the **Strategy Pattern**. An essential component of any ordering system is the payment procedure, which frequently calls for user flexibility. I created a `PaymentStrategy` interface with many implementations, including `CreditCardPayment`, `PayPalPayment`, and `CashOnDeliveryPayment`, in order to accomplish this.

This technique allows me to assign a `PaymentStrategy` to an order at runtime, allowing me to use several payment methods interchangeably without changing the order logic that underlies it.

Code Snippet:

Here is an example of setting a payment strategy for an order and making the payment:

```java
PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9012-3456", "Jane Doe");
order.setPaymentStrategy(paymentStrategy);
order.makePayment();
```

This illustrates how adaptable the **Strategy Pattern** is. By simply altering the strategy linked to the order, the payment mechanism may be changed with ease, upholding the open/closed principle and increasing the system's flexibility to accommodate future modifications.

## Conclusions / Results

In order to improve the system's flexibility, maintainability, and user experience, I have further expanded the Food Ordering System by including behavioural design principles. The behavioural design patterns listed below were applied:

**Observer Pattern**: To provide a more responsive and user-friendly experience by instantly informing clients of changes to their order status.

**Command Pattern**: To make the system more modular and easier to expand by encapsulating operations such as placing, canceling, or changing orders and separating the client from the logic that carries them out.

**Strategy Pattern**: To give clients a variety of payment choices, enabling dynamic payment method switching without changing the fundamental ordering logic.

The Food Ordering System is now more resilient, responsive, and adaptable thanks to these upgrades, opening the door for further advancements and scalability. By making the system easier to use, maintain, and expand, these design patterns successfully enhance the experience of both developers and users.

