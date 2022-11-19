package com.driver.Service;

import java.util.List;

public interface OrderPartnerPair {
    void addOrderPartnerPair(String orderId, String partnerId);
    Integer getOrderCountByPartnerId(String partnerId);
    List<String> getOrdersByPartnerId(String partnerId);
    Integer getCountOfUnassignedOrders();
    Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId);
    String getLastDeliveryTimeByPartnerId(String partnerId);



}
