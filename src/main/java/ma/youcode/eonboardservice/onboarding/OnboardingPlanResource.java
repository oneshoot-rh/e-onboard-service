package ma.youcode.eonboardservice.onboarding;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/onboarding-plans")
@RequiredArgsConstructor
public class OnboardingPlanResource {
    private final OnboardingPlanService onboardingPlanService;


    @PostMapping
    public ResponseEntity<OnboardingPlan> createOnboardingPlan(@RequestBody @Valid OnboardingPlanDto onboardingPlanDto) {
        return ResponseEntity.ok(onboardingPlanService.createOnboardingPlan(onboardingPlanDto));
    }
    
}
