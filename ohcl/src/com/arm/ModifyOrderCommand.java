package com.arm;

/**
 * Command class for modifying an existing order.
 */
public class ModifyOrderCommand extends Command {
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
     * Runs the command.
     * <p>
     * Updates the size, price, and time of the order identified by ID.
     *
     * @param orderBook the order book
     */
    @Override
    public void run(OrderBook orderBook) {
        OrderSide orderSide = orderBook.getIdToOrder().get(id).getSide();
        new CancelOrderCommand(time, id)
                .run(orderBook);
        new AddOrderCommand(new Order(time, id, orderSide, size, price))
                .run(orderBook);
    }

    /**
     * Updates the OHCL if not null.
     *
     * @param ohlc  the ohlc
     * @param value the updated value (-1 if not defined)
     */
    @Override
    protected void updateOhlc(Ohlc ohlc, int value) {
        if(ohlc != null){
            ohlc.update(time, value);
        }
    }
}
