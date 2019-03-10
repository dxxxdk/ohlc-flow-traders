package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Command class for adding a new order.
 */
class AddOrderCommand implements Command {
    private final Order order;

    /**
     * Constructs an AddOrderCommand object.
     *
     * @param order the order to add
     */
    AddOrderCommand(Order order) {
        this.order = order;
    }

    /**
     * Executes the command.
     * <p>
     * Adds the order to the appropriate TreeMap and the HashMap.
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
        idToOrder.put(order.getId(), order);

        if (order.getSide() == OrderSide.BUY) {
            addOrderTo(buyOrders);
        } else {
            addOrderTo(sellOrders);
        }
    }

    private void addOrderTo(TreeMap<Integer, TreeMap<Integer, Order>> orders) {
        TreeMap<Integer, Order> ordersAtPrice = orders.computeIfAbsent(order.getPrice(), k -> new TreeMap<>());
        ordersAtPrice.put(order.getTime(), order);
    }
}
