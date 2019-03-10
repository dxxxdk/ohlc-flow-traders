package com.arm;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeMap;


class AddOrderCommandTest {
    private final Order buyOrder = new Order(1, "buyOrder", OrderSide.BUY, 1, 10);

    private final Order sellOrder = new Order(2, "sellOrder", OrderSide.SELL, 10, 11);

    private final TreeMap<Integer, TreeMap<Integer, Order>> buyOrders = new TreeMap<>();
    private final TreeMap<Integer, TreeMap<Integer, Order>> sellOrders = new TreeMap<>();
    private final HashMap<String, Order> idToOrder = new HashMap<>();


    @Test
    void execute_addsBuyOrder_emptyMaps() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(buyOrder);
        assert buyOrders.size() == 0;
        assert idToOrder.size() == 0;

        addOrderCommand.execute(buyOrders, sellOrders, idToOrder);
        assert buyOrders.size() == 1;
        assert buyOrders.get(buyOrder.getPrice()).get(buyOrder.getTime()) == buyOrder;
        assert sellOrders.size() == 0;
        assert idToOrder.size() == 1;
        assert idToOrder.get(buyOrder.getId()) == buyOrder;
    }

    @Test
    void execute_addsSellOrder_emptyMaps() {
        AddOrderCommand addOrderCommand = new AddOrderCommand(sellOrder);
        assert sellOrders.size() == 0;
        assert idToOrder.size() == 0;

        addOrderCommand.execute(buyOrders, sellOrders, idToOrder);
        assert sellOrders.size() == 1;
        assert sellOrders.get(sellOrder.getPrice()).get(sellOrder.getTime()) == sellOrder;
        assert buyOrders.size() == 0;
        assert idToOrder.size() == 1;
        assert idToOrder.get(sellOrder.getId()) == sellOrder;
    }
}