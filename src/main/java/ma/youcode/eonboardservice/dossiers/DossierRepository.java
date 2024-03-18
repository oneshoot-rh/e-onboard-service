package ma.youcode.eonboardservice.dossiers;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DossierRepository extends MongoRepository<Dossier, UUID>{
    
}
