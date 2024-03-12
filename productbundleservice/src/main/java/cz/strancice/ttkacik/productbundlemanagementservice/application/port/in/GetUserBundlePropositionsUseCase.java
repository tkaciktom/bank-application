package cz.strancice.ttkacik.productbundlemanagementservice.application.port.in;

import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;

import java.util.List;

public interface GetUserBundlePropositionsUseCase {

    List<BundleProposition> getUserBundlePropositions(String userId);

}
