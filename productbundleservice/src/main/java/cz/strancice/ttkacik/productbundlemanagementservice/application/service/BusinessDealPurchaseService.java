package cz.strancice.ttkacik.productbundlemanagementservice.application.service;

import cz.strancice.ttkacik.productbundlemanagementservice.application.port.in.*;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundlePropositionRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundleRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BusinessDealPurchaseRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.BusinessDealPurchase;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.exception.BundlePropositionNotFoundException;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.exception.BusinessDealPurchaseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BusinessDealPurchaseService implements
        CreateBusinessDealPurchaseUseCase,
        ConfirmBusinessDealPurchaseUseCase {

    private final BundleRepository bundleRepository;
    private final BundlePropositionRepository bundlePropositionRepository;
    private final BusinessDealPurchaseRepository businessDealPurchaseRepository;

    @Override
    public void createBusinessDealPurchase(String businessDealPurchaseId,
                                           String propositionId,
                                           String ownerId,
                                           String messageId) {
        BundleProposition bundleProposition = this.findBundlePropositionById(propositionId);
        BusinessDealPurchase businessDealPurchase = new BusinessDealPurchase(
                businessDealPurchaseId,
                bundleProposition
        );
        businessDealPurchaseRepository.save(businessDealPurchase);
    }

    @Override
    public void confirmBusinessDealPurchase(String businessDealPurchaseId,
                                            String propositionId,
                                            String ownerId,
                                            String messageId) {
        BusinessDealPurchase businessDealPurchase = this.findBusinessDealPurchaseById(businessDealPurchaseId);
        Bundle bundle = new Bundle(ownerId, businessDealPurchase);
        bundleRepository.saveBundle(bundle);
    }

    private BundleProposition findBundlePropositionById(String propositionId) {
        return bundlePropositionRepository.findById(propositionId)
                .orElseThrow(() -> new BundlePropositionNotFoundException("Cannot find bundle proposition with id: %s".formatted(propositionId)));
    }

    private BusinessDealPurchase findBusinessDealPurchaseById(String businessDealPurchaseId) {
        return businessDealPurchaseRepository.getBusinessDealPurchaseById(businessDealPurchaseId)
                .orElseThrow(() -> new BusinessDealPurchaseNotFoundException("Business deal purchase cannot be found by id: %s".formatted(businessDealPurchaseId)));
    }

}
