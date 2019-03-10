package com.arm;

/**
 * Command class for clearing all existing orders.
 */
public class ResetCommand extends Command {
    private final int time;

    /**
     * Constructs a ResetCommand object.
     *
     * @param time the Unix time (seconds) at which the command was received
     */
    ResetCommand(int time) {
        this.time = time;
    }

    /**
     * Runs the command.
     * <p>
     * Clears the order book.
     *
     * @param orderBook the order book
     */
    @Override
    public void run(OrderBook orderBook) {
        orderBook.getBuyOrders().clear();
        orderBook.getSellOrders().clear();
        orderBook.getIdToOrder().clear();
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
}
