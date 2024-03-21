package ma.youcode.eonboardservice.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailSettingsRepository extends JpaRepository<EmailSettings, Long>{
    
}
