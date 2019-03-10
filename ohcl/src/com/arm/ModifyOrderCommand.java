package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Command class for modifying an existing order.
 */
public class ModifyOrderCommand implements Command {
    private final int time;
    private final String id;
    private final int size;
    private final int price;

    /**
     * Constructs a ModifyOrderCommand object.
     *
     * @param time  the Unix time (seconds) at which the order was placed
     * @param id    the ID of the order
     * @param size  the number of units to buy/sell
     * @param price the price per unit
     */
    ModifyOrderCommand(int time, String id, int size, int price) {
        this.time = time;
        this.id = id;
        this.size = size;
        this.price = price;
    }

    /**
     * Executes the command.
     * <p>
     * Updates the size, price, and time of the order identified by ID.
     *
     * @param buyOrders  the buy orders in a nested TreeMap (keys: price, time)
     * @param sellOrders the sell orders in a nested TreeMap (keys: price, time)
     * @param idToOrder  a HashMap from order IDs to Order instances
     */
    @Override
    public void execute(
            TreeMap<Integer, TreeMap<Integer, Order>> buyOrders,
            TreeMap<Integer, TreeMap<Integer, Order>> sellOrders,
            HashMap<String, Order> idToOrder) {
        OrderSide orderSide = idToOrder.get(id).getSide();
        new CancelOrderCommand(time, id)
                .execute(buyOrders, sellOrders, idToOrder);
        new AddOrderCommand(new Order(time, id, orderSide, size, price))
                .execute(buyOrders, sellOrders, idToOrder);
    }
}
