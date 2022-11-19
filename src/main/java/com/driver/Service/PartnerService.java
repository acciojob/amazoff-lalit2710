package com.driver.Service;

import com.driver.DeliveryPartner;

public interface PartnerService {
    DeliveryPartner addPartner(String partnerId);

    DeliveryPartner getPartnerById(String id);
    void deletePartnerById(String partnerId);
}
