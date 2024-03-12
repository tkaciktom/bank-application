package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence;

import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundlePropositionRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.BundlePropositionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BundlePropositionRepositoryImpl implements BundlePropositionRepository {

    private final BundlePropositionJpaRepository jpaRepository;
    private final BundlePropositionEntityMapper mapper;

    @Override
    public List<BundleProposition> getBundlePropositionsByUserId(String userId) {
        return this.jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BundleProposition> findById(String offerId) {
        return jpaRepository.findById(offerId)
                .map(mapper::toDomain);
    }
}
