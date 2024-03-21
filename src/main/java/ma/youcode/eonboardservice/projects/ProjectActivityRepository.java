package ma.youcode.eonboardservice.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectActivityRepository extends JpaRepository<ProjectActivity, Long>{
    
}
