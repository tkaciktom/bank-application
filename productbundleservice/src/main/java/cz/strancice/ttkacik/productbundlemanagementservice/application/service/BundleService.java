package cz.strancice.ttkacik.productbundlemanagementservice.application.service;

import cz.strancice.ttkacik.productbundlemanagementservice.application.port.in.GetUserBundlesUseCase;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundleRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BundleService implements GetUserBundlesUseCase {

    private final BundleRepository bundleRepository;

    @Override
    public List<Bundle> getUserBundles(String userId) {
        return bundleRepository.getBundlesByUserId(userId);
    }
}
