package ma.youcode.eonboardservice.candidates;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
    Optional<Candidate> findByEmail(String email);   
    Optional<Candidate> findByCandidateId(UUID candidateId);
    Page<Candidate> findAllByStatus(CandidateStatus selected,Pageable pageable);
}
