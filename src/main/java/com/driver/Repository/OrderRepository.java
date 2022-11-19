package com.driver.Repository;

import com.driver.DeliveryPartner;
import com.driver.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {
    HashMap<String, DeliveryPartner> partners = new HashMap<>();


    public DeliveryPartner addPartner(String partnerId){
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partners.put(partnerId, deliveryPartner);
        return deliveryPartner;
    }

    public DeliveryPartner getPartner(String id){
        return partners.get(id);
    }

//    OrderRepository

    HashMap<String, Order> orders = new HashMap<>();

    public Order addOrder(Order order){
        return orders.put(order.getId(), order);
    }

    public Order getOrder(String id){
        return orders.get(id);
    }

    public List<String> getAllOrders(){
        List<String> orderList = new ArrayList<>();
        for(Order ord : orders.values()){
            orderList.add(ord.getId());
        }

        return orderList;
    }

    public void deleteOrderById(String orderId){

        Order order = orders.get(orderId);

        for(List<Order> list : orderPartnerPair.values()){
            if(list.contains(order)){
                list.remove(order);
                break;
            }
        }

        String partnerId = partnerByOrder.get(orderId);

        getPartner(partnerId)
                .setNumberOfOrders(getPartner(partnerId)
                        .getNumberOfOrders()-1);

        partnerByOrder.remove(orderId);

        if(orders.containsKey(orderId)){
            orders.remove(orderId);
        }
    }

    //    OrderPartnerPairRepository
    HashMap<String, List<Order>> orderPartnerPair = new HashMap<>();

    HashMap<String, String> partnerByOrder = new HashMap<>();

    public boolean addOrderPartnerPair(String orderId, String partnerId){
        Order order = getOrder(orderId);
        DeliveryPartner deliveryPartner = getPartner(partnerId);

        partnerByOrder.put(orderId, partnerId);

        boolean orderCheck = false;

        for(Order ord : orders.values()){
            if(ord.getId().equals(orderId)){
                orderCheck = true;
                break;
            }
        }

        boolean partnerCheck = false;

        for(DeliveryPartner part : partners.values()){
            if(part.getId().equals(partnerId)){
                partnerCheck = true;
                break;
            }
        }

        if(!orderCheck || !partnerCheck){
            return false;
        }

        if(orderPartnerPair.containsKey(partnerId)){
            orderPartnerPair.get(partnerId).add(order);

        }else{
            orderPartnerPair.put(partnerId, new ArrayList<>());
            orderPartnerPair.get(partnerId).add(order);
        }

        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders()+1);

        return true;
    }

//    OrderBYPartnerIDCount

    public Integer getOrderCountBYPartnerId(String partnerId){
        if(orderPartnerPair.containsKey(partnerId)){
            return orderPartnerPair.get(partnerId).size();
        }

        return 0;
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orderList = new ArrayList<>();
        if(orderPartnerPair.containsKey(partnerId)){
            for(Order order : orderPartnerPair.get(partnerId)){
                orderList.add(order.getId());
            }
        }

        return orderList;
    }

    public Integer unassignedOrders(){

        Integer unassignedOrder=0;

        for(List<Order> list : orderPartnerPair.values()){
            unassignedOrder += list.size();
        }

        Integer totalOrders = orders.size();

        return totalOrders-unassignedOrder;
    }

    public Integer getOrdersLeftAfterGivenTimeBYPartnerID(String time, String partnerId){
        List<Order> orderList = orderPartnerPair.get(partnerId);
        int HH = Integer.valueOf(time.substring(0, 2));
        int MM = Integer.valueOf(time.substring(3));
        // The deliveryTime has to converted from string to int and then stored in the attribute
        int drivedTime  = HH*60 + MM;
        Integer orderCount = 0;
        for(Order order : orderList){
            if(order.getDeliveryTime() > drivedTime){
                orderCount++;
            }
        }

        return orderCount;
    }

    public String getLastDeliveryTimeByPartner(String partnerId){
        List<Order> orderList = orderPartnerPair.get(partnerId);

        int maxTime = 0;
        for(Order order : orderList){
            maxTime = Math.max(maxTime, order.getDeliveryTime());
        }

        String HH = String.valueOf(maxTime/60);
        String MM = String.valueOf(maxTime%60);

        String time = HH+":"+MM;

        return time;
    }

//    delete partner by Id

    public void deletePartnerBYId(String partnerId){

        if(partners.containsKey(partnerId)){
            orderPartnerPair.remove(partnerId);
            partners.remove(partnerId);
        }
    }
}
