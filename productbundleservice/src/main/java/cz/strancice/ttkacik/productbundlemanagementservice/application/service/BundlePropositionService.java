package cz.strancice.ttkacik.productbundlemanagementservice.application.service;

import cz.strancice.ttkacik.productbundlemanagementservice.application.port.in.GetUserBundlePropositionsUseCase;
import cz.strancice.ttkacik.productbundlemanagementservice.application.port.out.BundlePropositionRepository;
import cz.strancice.ttkacik.productbundlemanagementservice.domain.BundleProposition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BundlePropositionService implements GetUserBundlePropositionsUseCase {

    private final BundlePropositionRepository repository;

    @Override
    public List<BundleProposition> getUserBundlePropositions(String userId) {
        return repository.getBundlePropositionsByUserId(userId);
    }
}
