package lab1.commands;

import lab1.builder.Order;
import lab1.facade.OrderingFacade;

public class PlaceOrderCommand implements OrderCommand {
    private final OrderingFacade orderingFacade;
    private final Order order;

    public PlaceOrderCommand(OrderingFacade orderingFacade, Order order) {
        this.orderingFacade = orderingFacade;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println("Placing order...");
        // Here you can use OrderingFacade to carry out further actions if needed
        System.out.println(order);
    }
}
