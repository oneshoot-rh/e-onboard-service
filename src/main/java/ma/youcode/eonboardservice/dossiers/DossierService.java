package ma.youcode.eonboardservice.dossiers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DossierService {
    
    private final DossierRepository dossierRepository;


    public Page<Dossier> getAllDossiers(Pageable page) {
        return dossierRepository.findAll(page);
    }

    public Optional<Dossier> getDossierById(UUID id) {
        return dossierRepository.findById(id);
    }

    public void saveDossier(Dossier dossier) {
        Objects.requireNonNull(dossier, "Dossier must not be null");
        Objects.requireNonNull(dossier.getCandidate(), "Candidate must not be null");
        dossierRepository.save(dossier);
    }
}
