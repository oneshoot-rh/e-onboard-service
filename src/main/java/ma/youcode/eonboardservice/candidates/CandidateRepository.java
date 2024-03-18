package ma.youcode.eonboardservice.candidates;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, Long>{
    Optional<Candidate> findByEmail(String email);   
}
