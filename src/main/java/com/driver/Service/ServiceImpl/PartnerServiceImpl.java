package com.driver.Service.ServiceImpl;

import com.driver.DeliveryPartner;
import com.driver.Repository.OrderRepository;
import com.driver.Service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public DeliveryPartner addPartner(String partnerId) {

        DeliveryPartner deliveryPartner = orderRepository.addPartner(partnerId);
        return deliveryPartner;
    }

    @Override
    public DeliveryPartner getPartnerById(String id) {
        DeliveryPartner deliveryPartner = orderRepository.getPartner(id);
        return deliveryPartner;
    }
    @Override
    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerBYId(partnerId);
    }

}
