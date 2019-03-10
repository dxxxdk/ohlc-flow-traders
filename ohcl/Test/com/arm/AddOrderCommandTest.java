package com.arm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddOrderCommandTest {
    private final Order buyOrder = new Order(1, "buyOrder", OrderSide.BUY, 1, 10);

    private final Order sellOrder = new Order(2, "sellOrder", OrderSide.SELL, 10, 11);

    private final TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = new TreeMap<>();
    private final TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = new TreeMap<>();
    private final HashMap<String, Order> idToOrder = new HashMap<>();


    @Test
    void execute_addsBuyOrder_emptyMaps() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(buyOrder);
        assertEquals(0, buyOrders.size());
        assertEquals(0, idToOrder.size());

        addOrderCommand.execute(buyOrders, sellOrders, idToOrder);
        assertEquals(1, buyOrders.size());
        assertEquals(buyOrder, buyOrders.get(buyOrder.getPrice()).get(buyOrder.getTime()));
        assertEquals(0, sellOrders.size());
        assertEquals(1, idToOrder.size());
        assertEquals(buyOrder, idToOrder.get(buyOrder.getId()));
    }

    @Test
    void execute_addsSellOrder_emptyMaps() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(sellOrder);
        assertEquals(0, sellOrders.size());
        assertEquals(0, idToOrder.size());

        addOrderCommand.execute(buyOrders, sellOrders, idToOrder);
        assertEquals(1, sellOrders.size());
        assertEquals(sellOrder, sellOrders.get(sellOrder.getPrice()).get(sellOrder.getTime()));
        assertEquals(0, buyOrders.size());
        assertEquals(1, idToOrder.size());
        assertEquals(sellOrder, idToOrder.get(sellOrder.getId()));
    }
}