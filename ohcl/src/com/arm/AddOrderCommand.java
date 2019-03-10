package com.arm;

import java.util.TreeMap;

/**
 * Command class for adding a new order.
 */
class AddOrderCommand extends Command {
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
     * Runs the command.
     * <p>
     * Adds the new order to the order book.
     *
     * @param orderBook the order book
     */
    @Override
    public void run(OrderBook orderBook) {
        orderBook.getIdToOrder().put(order.getId(), order);

        if (order.getSide() == OrderSide.BUY) {
            addOrderTo(orderBook.getBuyOrders());
        } else {
            addOrderTo(orderBook.getSellOrders());
        }
    }

    /**
     * Updates the OHCL if not null.
     *
     * @param ohlc  the ohlc
     * @param value the updated value (-1 if not defined)
     */
    @Override
    protected void updateOhlc(Ohlc ohlc, int value) {
        if (ohlc != null) {
            ohlc.update(order.getTime(), value);
        }
    }

    private void addOrderTo(TreeMap<Integer, TreeMap<Integer, Order>> orders) {
        TreeMap<Integer, Order> ordersAtPrice = orders.computeIfAbsent(order.getPrice(), k -> new TreeMap<>());
        ordersAtPrice.put(order.getTime(), order);
    }
}
