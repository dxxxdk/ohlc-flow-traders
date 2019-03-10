package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Command class for clearing all existing orders.
 */
public class ResetCommand implements Command {
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
     * Executes the command.
     * <p>
     * Clears both order TreeMaps and the HashMap.
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
        buyOrders.clear();
        sellOrders.clear();
        idToOrder.clear();
    }
}
