package cz.strancice.ttkacik.productbundlemanagementservice.application.port.out;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;

import java.util.List;
import java.util.Optional;

public interface BundleRepository {
    Optional<Bundle> loadBundle(String bundleId);
    void saveBundle(Bundle bundle);
    void deleteBundle(Bundle bundle);
    List<Bundle> getBundlesByUserId(String userId);
}
