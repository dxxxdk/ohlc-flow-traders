package com.arm;

import java.util.HashMap;
import java.util.TreeMap;

class OrderBook {
    private final TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = new TreeMap<>();
    private final TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = new TreeMap<>();
    private final HashMap<String, Order> idToOrder = new HashMap<>();

    /**
     * @return the buy orders in a nested TreeMap (keys: price, time)
     */
    TreeMap<Integer, TreeMap<Integer, Order>> getBuyOrders() {
        return buyOrders;
    }

    /**
     * @return the sell orders in a nested TreeMap (keys: price, time)
     */
    TreeMap<Integer, TreeMap<Integer, Order>> getSellOrders() {
        return sellOrders;
    }

    /**
     * @return a HashMap from order IDs to Order instances
     */
    HashMap<String, Order> getIdToOrder() {
        return idToOrder;
    }

    /**
     * @return whether there is a bid and ask price available
     */
    boolean hasBidAndAsk() {
        return buyOrders.size() > 0 && sellOrders.size() > 0;
    }

    /**
     * @return whether a transaction is possible
     */
    boolean isTransactionPossible() {
        return buyOrders.lastKey() >= sellOrders.firstKey();
    }

    /**
     * @return the bid price
     */
    int getBid() {
        return buyOrders.lastEntry().getKey();
    }

    /**
     * @return the ask price
     */
    int getAsk() {
        return sellOrders.firstEntry().getKey();
    }

    /**
     * @return the buy order with the highest price that was placed first
     */
    Order getHighestBuyOrder() {
        return buyOrders.lastEntry().getValue().firstEntry().getValue();
    }

    /**
     * @return the sell order with the lowest price that was placed first
     */
    Order getLowestSellOrder() {
        return sellOrders.firstEntry().getValue().firstEntry().getValue();
    }
}