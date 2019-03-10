package com.arm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ResetCommandTest {

    @Test
    void execute() {
        OrderBook orderBook = new OrderBook();
        TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = orderBook.getBuyOrders();
        TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = orderBook.getSellOrders();
        HashMap<String, Order> idToOrder = orderBook.getIdToOrder();

        Order buyOrder = new Order(1, "buyOrder ", OrderSide.BUY, 1, 10);
        Order sellOrder = new Order(2, "sellOrder", OrderSide.SELL, 10, 11);

        TreeMap<Integer, Order> buyOrdersAtPrice = new TreeMap<>();
        buyOrdersAtPrice.put(buyOrder.getTime(), buyOrder);
        buyOrders.put(buyOrder.getPrice(), buyOrdersAtPrice);

        TreeMap<Integer, Order> sellOrdersAtPrice = new TreeMap<>();
        sellOrdersAtPrice.put(sellOrder.getTime(), sellOrder);
        sellOrders.put(sellOrder.getPrice(), sellOrdersAtPrice);

        idToOrder.put(buyOrder.getId(), buyOrder);
        idToOrder.put(sellOrder.getId(), sellOrder);

        assertEquals(1, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());

        ResetCommand resetCommand = new ResetCommand(1);
        resetCommand.execute(orderBook, null);

        assertEquals(0, buyOrders.size());
        assertEquals(0, sellOrders.size());
        assertEquals(0, idToOrder.size());
    }
}