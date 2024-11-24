package lab1.commands;

public class CancelOrderCommand implements OrderCommand {
    private final String orderId;

    public CancelOrderCommand(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void execute() {
        // Logic to cancel the order
        System.out.println("Canceling order with ID: " + orderId);
    }
}
