package lab1.domain.models;

public class CustomerDecorator {
    public static Customer createCustomer(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return new Customer(fullName);
    }
}
