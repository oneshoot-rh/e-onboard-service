package ma.youcode.eonboardservice.candidates;

import java.util.List;
import java.util.Optional;

import org.apache.kafka.common.protocol.types.Field.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;


    public Page<Candidate> getAllCandidates(Pageable pageable,Boolean onlySelected) {
        if(!onlySelected) return candidateRepository.findAllByStatus(CandidateStatus.SELECTED,pageable);
        else return  candidateRepository.findAll(pageable);
    }

    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    public List<Candidate> makeCandidatesSelected(SelectedCandidatesDto selected) {
       return selected.selectedCandidateIds().stream()
       .map(sC -> {
        Candidate candidate = candidateRepository.findByCandidateId(sC).orElseThrow();
        candidate.setStatus(CandidateStatus.SELECTED);
        Candidate updated = candidateRepository.save(candidate);
        return updated;
       }).toList();
    }
}
