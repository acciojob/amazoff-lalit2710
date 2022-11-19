package com.driver.Service.ServiceImpl;

import com.driver.Order;
import com.driver.Repository.OrderRepository;
import com.driver.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;


    @Override
    public  Order addOrder(Order order) {
        order = orderRepository.addOrder(order);
        return order;
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.getOrder(id);
    }

    @Override
    public List<String> getAllOrders() {
        List<String> list = orderRepository.getAllOrders();
        return list;
    }

    @Override
    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderById(orderId);
    }

}
