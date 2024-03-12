package cz.strancice.ttkacik.productbundlemanagementservice.application.port.out;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchase;

import java.util.Optional;

public interface BusinessDealPurchaseRepository {

    Optional<BusinessDealPurchase> getBusinessDealPurchaseById(String id);

    Optional<BusinessDealPurchase> getBusinessDealPurchaseByOwnerId(String ownerId);

    void save(BusinessDealPurchase businessDealPurchase);

}
