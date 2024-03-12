package cz.strancice.ttkacik.productbundlemanagementservice.application.port.out;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;

import java.util.List;
import java.util.Optional;

public interface BundlePropositionRepository {
    List<BundleProposition> getBundlePropositionsByUserId(String userId);

    Optional<BundleProposition> findById(String offerId);
}
