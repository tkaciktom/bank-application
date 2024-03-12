package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundleRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.Bundle;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.BundleJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BundleRepositoryImpl implements BundleRepository {

    private final BundleJpaRepository jpaRepository;
    private final BundleEntityMapper mapper;

    @Override
    public Optional<Bundle> loadBundle(String bundleId) {
        return jpaRepository.findById(bundleId).map(mapper::toDomain);
    }

    @Override
    public void saveBundle(Bundle bundle) {
        jpaRepository.save(mapper.toEntity(bundle));
    }

    @Override
    public void deleteBundle(Bundle bundle) {
        jpaRepository.deleteById(bundle.getId());
    }

    @Override
    public List<Bundle> getBundlesByUserId(String userId) {
        return jpaRepository.findBundleEntitiesByOwnerId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}
