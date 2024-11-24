package lab1.observers;

import lab1.domain.models.Customer;

public class CustomerObserver implements OrderObserver {
    private Customer customer;

    public CustomerObserver(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void update(String message) {
        System.out.println("Notification for customer " + customer.getName() + ": " + message);
    }
}
