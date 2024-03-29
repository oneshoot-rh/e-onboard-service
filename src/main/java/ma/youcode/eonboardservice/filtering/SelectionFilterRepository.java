package ma.youcode.eonboardservice.filtering;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionFilterRepository extends JpaRepository<SelectionFilter, Long>{
    
}
