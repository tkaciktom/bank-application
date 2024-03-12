package cz.strancice.ttkacik.productbundlemanagementservice.application.port.in;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;

import java.util.List;

public interface GetUserBundlesUseCase {

    List<Bundle> getUserBundles(String userId);

}
