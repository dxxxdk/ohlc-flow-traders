package com.arm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class ModifyOrderCommandTest {
    private final Order buyOrderToModify = new Order(1, "buyOrderToModify", OrderSide.BUY, 1, 10);

    private final Order sellOrderToModify = new Order(2, "sellOrderToModify", OrderSide.SELL, 10, 11);

    private final TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = new TreeMap<>();
    private final TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = new TreeMap<>();
    private final HashMap<String, Order> idToOrder = new HashMap<>();

    @BeforeEach
    void setUp() {
        TreeMap<Integer, Order> buyOrdersAtPrice = new TreeMap<>();
        buyOrdersAtPrice.put(buyOrderToModify.getTime(), buyOrderToModify);
        buyOrders.put(buyOrderToModify.getPrice(), buyOrdersAtPrice);

        TreeMap<Integer, Order> sellOrdersAtPrice = new TreeMap<>();
        sellOrdersAtPrice.put(sellOrderToModify.getTime(), sellOrderToModify);
        sellOrders.put(sellOrderToModify.getPrice(), sellOrdersAtPrice);

        idToOrder.put(buyOrderToModify.getId(), buyOrderToModify);
        idToOrder.put(sellOrderToModify.getId(), sellOrderToModify);
    }

    @Test
    void execute_modifiesBuyOrder_mapsWithBuyOrder() {
        ModifyOrderCommand modifyOrderCommand = new ModifyOrderCommand(10, "buyOrderToModify", 500, 9);

        assertEquals(1, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());

        modifyOrderCommand.execute(buyOrders, sellOrders, idToOrder);

        Order modifiedBuyOrder = buyOrders.get(9).get(10);
        assertEquals(500, modifiedBuyOrder.getSize());
        assertEquals(9, modifiedBuyOrder.getPrice());

        assertNotEquals(buyOrderToModify, modifiedBuyOrder);
        assertEquals(sellOrderToModify, sellOrders.get(sellOrderToModify.getPrice()).get(sellOrderToModify.getTime()));

        assertEquals(1, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());
    }

    @Test
    void execute_modifiesSellOrder_mapsWithBuyOrder() {
        ModifyOrderCommand modifyOrderCommand = new ModifyOrderCommand(20, "sellOrderToModify", 500, 11);

        assertEquals(1, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());

        modifyOrderCommand.execute(buyOrders, sellOrders, idToOrder);

        Order modifiedSellOrder = sellOrders.get(11).get(20);
        assertEquals(500, modifiedSellOrder.getSize());
        assertEquals(11, modifiedSellOrder.getPrice());

        assertNotEquals(sellOrderToModify, modifiedSellOrder);
        assertEquals(buyOrderToModify, buyOrders.get(buyOrderToModify.getPrice()).get(buyOrderToModify.getTime()));

        assertEquals(1, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());
    }
}