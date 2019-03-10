package com.arm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class CancelOrderCommandTest {
    private final Order buyOrderToCancel = new Order(1, "buyOrderToCancel ", OrderSide.BUY, 1, 10);

    private final Order sellOrderToCancel = new Order(2, "sellOrderToCancel", OrderSide.SELL, 10, 11);

    private final OrderBook orderBook = new OrderBook();
    private final TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = orderBook.getBuyOrders();
    private final TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = orderBook.getSellOrders();
    private final HashMap<String, Order> idToOrder = orderBook.getIdToOrder();

    @BeforeEach
    void setUp() {
        TreeMap<Integer, Order> buyOrdersAtPrice = new TreeMap<>();
        buyOrdersAtPrice.put(buyOrderToCancel.getTime(), buyOrderToCancel);
        buyOrders.put(buyOrderToCancel.getPrice(), buyOrdersAtPrice);

        TreeMap<Integer, Order> sellOrdersAtPrice = new TreeMap<>();
        sellOrdersAtPrice.put(sellOrderToCancel.getTime(), sellOrderToCancel);
        sellOrders.put(sellOrderToCancel.getPrice(), sellOrdersAtPrice);

        idToOrder.put(buyOrderToCancel.getId(), buyOrderToCancel);
        idToOrder.put(sellOrderToCancel.getId(), sellOrderToCancel);
    }

    @Test
    void execute_removesBuyOrder_mapsWithBuyOrder() {
        CancelOrderCommand cancelOrderCommand =
                new CancelOrderCommand(buyOrderToCancel.getTime(), buyOrderToCancel.getId());
        assertEquals(1, buyOrders.size());
        assertEquals(2, idToOrder.size());

        cancelOrderCommand.execute(orderBook, null);

        assertEquals(0, buyOrders.size());
        assertEquals(1, sellOrders.size());
        assertEquals(1, idToOrder.size());
    }

    @Test
    void execute_removesSellOrder_mapsWithSellOrder() {
        CancelOrderCommand cancelOrderCommand =
                new CancelOrderCommand(sellOrderToCancel.getTime(), sellOrderToCancel.getId());
        assertEquals(1, sellOrders.size());
        assertEquals(2, idToOrder.size());

        cancelOrderCommand.execute(orderBook, null);

        assertEquals(0, sellOrders.size());
        assertEquals(1, buyOrders.size());
        assertEquals(1, idToOrder.size());
    }
}