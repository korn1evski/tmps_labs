package lab1.domain.models;

public class AddressDecorator {

    public static Address createAddress(int streetNumber, String streetName, String city) {
        String street = streetNumber + " " + streetName;
        return new Address(street, city);
    }
}
