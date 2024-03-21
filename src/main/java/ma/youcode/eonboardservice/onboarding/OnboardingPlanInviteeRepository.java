package ma.youcode.eonboardservice.onboarding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingPlanInviteeRepository extends JpaRepository<OnboardingPlanInvitee, Long>{
    
}
