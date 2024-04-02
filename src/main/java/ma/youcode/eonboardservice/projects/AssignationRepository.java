package ma.youcode.eonboardservice.projects;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignationRepository extends JpaRepository<Assignation,Long>{
    List<Assignation> findAllByOrderByProjectIdAsc();
    List<Assignation> findAllByProjectId(Long id);
}
