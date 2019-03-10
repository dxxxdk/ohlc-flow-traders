package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Command class for canceling an existing order.
 */
public class CancelOrderCommand implements Command {
    private final int time;
    private final String id;

    /**
     * Constructs a CancelOrderCommand object.
     *
     * @param time the Unix time (seconds) at which the order was placed
     * @param id   the ID of the order
     */
    CancelOrderCommand(int time, String id) {
        this.time = time;
        this.id = id;
    }

    /**
     * Executes the command.
     * <p>
     * Removes the order from its TreeMap and the HashMap.
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
        Order order = idToOrder.get(id);
        idToOrder.remove(id);
        if (order.getSide() == OrderSide.BUY) {
            removeOrderFrom(order, buyOrders);
        } else {
            removeOrderFrom(order, sellOrders);
        }
    }

    private void removeOrderFrom(Order order, TreeMap<Integer, TreeMap<Integer, Order>> orders) {
        TreeMap<Integer, Order> ordersAtPrice = orders.get(order.getPrice());
        ordersAtPrice.remove(order.getTime());
        if (ordersAtPrice.isEmpty()) {
            orders.remove(order.getPrice());
        }
    }
}
