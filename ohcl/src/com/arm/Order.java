package com.arm;

public class Order {
    private final int time;
    private final String id;
    private final OrderSide side;
    private final int size;
    private final int price;

    /**
     * Constructs an Order object.
     *
     * @param time  the Unix time (seconds) at which the order was placed
     * @param id    the ID of the order
     * @param side  the buy/sell side
     * @param size  the number of units to buy/sell
     * @param price the price per unit
     */
    public Order(int time, String id, OrderSide side, int size, int price) {
        this.time = time;
        this.id = id;
        this.side = side;
        this.size = size;
        this.price = price;
    }

    /**
     * @return the Unix time (seconds) at which the order was placed
     */
    public int getTime() {
        return time;
    }

    /**
     * @return the ID of the order
     */
    public String getId() {
        return id;
    }

    /**
     * @return the buy/sell side
     */
    public OrderSide getSide() {
        return side;
    }

    /**
     * @return the number of units to buy/sell
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the price per unit
     */
    public int getPrice() {
        return price;
    }
}
