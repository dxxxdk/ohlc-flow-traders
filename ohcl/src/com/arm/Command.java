package com.arm;

public abstract class Command {
    /**
     * Runs the command, executes the orders and updates the OHCL.
     *
     * @param orderBook the order book
     * @param ohlc      the ohlc
     */
    void execute(OrderBook orderBook, Ohlc ohlc) {
        run(orderBook);
        executeOrders(orderBook);

        if (orderBook.hasBidAndAsk()) {
            int bid = orderBook.getBid();
            int ask = orderBook.getAsk();
            updateOhlc(ohlc, (bid + ask) / 2);
        } else {
            updateOhlc(ohlc, -1);
        }
    }

    /**
     * Runs the command.
     *
     * @param orderBook the order book
     */
    protected abstract void run(OrderBook orderBook);

    /**
     * Updates the OHCL.
     *
     * @param ohlc  the ohlc
     * @param value the updated value (-1 if not defined)
     */
    protected abstract void updateOhlc(Ohlc ohlc, int value);

    private void executeOrders(OrderBook orderBook) {
        if (!(orderBook.hasBidAndAsk() && orderBook.isTransactionPossible())) {
            return;
        }

        Order buyOrder = orderBook.getHighestBuyOrder();
        Order sellOrder = orderBook.getLowestSellOrder();

        updateOrderSize(orderBook, buyOrder, buyOrder.getSize() - sellOrder.getSize());
        updateOrderSize(orderBook, sellOrder, sellOrder.getSize() - buyOrder.getSize());

        executeOrders(orderBook);
    }

    private void updateOrderSize(OrderBook orderBook, Order order, int size) {
        if (size > 0) {
            new ModifyOrderCommand(
                    order.getTime(),
                    order.getId(),
                    size,
                    order.getPrice())
                    .run(orderBook);
        } else {
            new CancelOrderCommand(
                    order.getTime(),
                    order.getId())
                    .run(orderBook);
        }
    }
}
