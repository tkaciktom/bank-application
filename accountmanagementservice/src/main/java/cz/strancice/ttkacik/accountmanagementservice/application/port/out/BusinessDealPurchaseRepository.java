package cz.strancice.ttkacik.accountmanagementservice.application.port.out;

import cz.strancice.ttkacik.accountmanagementservice.domain.BusinessDealPurchase;

import java.util.List;
import java.util.Optional;

public interface BusinessDealPurchaseRepository {

    Optional<BusinessDealPurchase> loadById(String id);

    void save(BusinessDealPurchase purchase);

    void remove(BusinessDealPurchase purchase);

}
