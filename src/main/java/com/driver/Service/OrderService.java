package com.driver.Service;

import com.driver.Order;

import java.util.List;

public interface OrderService {
    Order addOrder(Order Order);

    Order getOrderById(String id);
    List<String> getAllOrders();

    void deleteOrderById(String orderId);
}
