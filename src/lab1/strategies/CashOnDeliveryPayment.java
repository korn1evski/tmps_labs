package lab1.strategies;

public class CashOnDeliveryPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Cash on Delivery.");
    }
}
