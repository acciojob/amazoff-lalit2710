package com.driver.Service.ServiceImpl;

import com.driver.Repository.OrderRepository;
import com.driver.Service.OrderPartnerPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPartnerPairImpl implements OrderPartnerPair {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void addOrderPartnerPair(String orderId, String partnerId) {
        orderRepository.addOrderPartnerPair(orderId, partnerId);
    }

    @Override
    public Integer getOrderCountByPartnerId(String partnerId) {
        Integer count = orderRepository.getOrderCountBYPartnerId(partnerId);
        return count;
    }

    @Override
    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> orderList= orderRepository.getOrdersByPartnerId(partnerId);
        return orderList;
    }

    @Override
    public Integer getCountOfUnassignedOrders() {
        Integer count = orderRepository.unassignedOrders();
        return count;
    }

    @Override
    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        Integer count =  orderRepository.getOrdersLeftAfterGivenTimeBYPartnerID(time, partnerId);
        return count;
    }

    @Override
    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        String lastTime = orderRepository.getLastDeliveryTimeByPartner(partnerId);
        return lastTime;
    }
}
