package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

public interface Command {
    /**
     * Executes the command.
     *
     * @param buyOrders  the buy orders in a nested TreeMap (keys: price, time)
     * @param sellOrders the sell orders in a nested TreeMap (keys: price, time)
     * @param idToOrder  a HashMap from order IDs to Order instances
     */
    void execute(
            TreeMap<Integer, TreeMap<Integer, Order>> buyOrders,
            TreeMap<Integer, TreeMap<Integer, Order>> sellOrders,
            HashMap<String, Order> idToOrder);
}
