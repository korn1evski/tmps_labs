# *Structural Design Patterns*

**Author**: Cornievschi Bogdan

**Group**: FAF-222

----

## Objectives:
&ensp; &ensp; __1. Study and understand the Structural Design Patterns.__

&ensp; &ensp; __2. As a continuation of the previous laboratory work, think about the functionalities that your system will need to provide to the user.__

&ensp; &ensp; __3. Implement some additional functionalities using structural design patterns.__

## Used Design Patterns:

* Flyweight Pattern
* Decorator Pattern
* Facade Pattern

## Implementation

This version of my project involved extending the Food Ordering System to include more structural design patterns, which enable flexibility when dealing with orders, customers, and restaurants without changing the core structure. Specifically, I used the Flyweight, Decorator, and Facade patterns for performance optimization, dynamic customization options, and interaction simplification with the system.

### Flyweight Pattern

I implemented the Flyweight Pattern using the Cache component to manage frequently accessed objects, such as customers and restaurants. Instead of creating multiple instances of a customer with identical details, the cache allows the reuse of existing objects, by this optimizing memory usage and improving efficiency.

Code Snippet:

Here is the example of how I “adapted” the `getInstance` method:

```java
public static Cache getInstance() {
        if (instance == null) {
            synchronized (Cache.class) {
                if (instance == null) {
                    instance = new Cache();
                }
            }
        }
        return instance;
    }
```

### Decorator Pattern

I used the Decorator Pattern to extend dynamically the properties of the Address and Customer classes. Instead of modifying their base classes, I used decorators like the AddressDecorator and CustomerDecorator to append dynamically new attributes in these classes.

Code Snippet:

As you can see in this code snippet, `CustomerDecorator` to extend functionality for creation of the customer`

```java
public class CustomerDecorator {
    public static Customer createCustomer(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return new Customer(fullName);
    }
}

```


### Facade Pattern

I used the Facade Pattern to make interaction with the system easier by using the OrderingFacade class. This facade offers a unified interface to a set of interacting objects, like RestaurantDirectory, MealFactory, and OrderBuilder; it abstracts away complexities that the user does not need to know about for ordering from restaurants.

Code Snippet:
As you can see from this snippet, with implemented facade, the process for creating customer and working with cache now become only a one method, the fact that significantly makes easier the whole process of interacting with the system for the Client
```java
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
```

## Conclusions / Results

As a result we have modified Restaurant Ordering System with additionally implement structural design patterns such as:
* Flyweight Pattern: To optimize memory and reuse existing objects like customers and restaurants.
* Decorator Pattern: To add extra features to entities like Address and Customer without altering the original classes.
* Facade Pattern: To simplify user interaction by providing a one-stop interface for managing orders and system components.