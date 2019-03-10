package com.arm;

import java.util.TreeMap;

/**
 * Command class for canceling an existing order.
 */
public class CancelOrderCommand extends Command {
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
     * Runs the command.
     * <p>
     * Removes the order from the order book.
     *
     * @param orderBook the order book
     */
    @Override
    public void run(OrderBook orderBook) {
        Order order = orderBook.getIdToOrder().get(id);
        orderBook.getIdToOrder().remove(id);
        if (order.getSide() == OrderSide.BUY) {
            removeOrderFrom(order, orderBook.getBuyOrders());
        } else {
            removeOrderFrom(order, orderBook.getSellOrders());
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
            ohlc.update(time, value);
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
