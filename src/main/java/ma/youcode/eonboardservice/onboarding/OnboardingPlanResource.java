package ma.youcode.eonboardservice.onboarding;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cl/onboarding-plans")
@RequiredArgsConstructor
public class OnboardingPlanResource {
    private final OnboardingPlanService onboardingPlanService;


    @PostMapping
    public ResponseEntity<OnboardingPlan> createOnboardingPlan(@RequestBody @Valid OnboardingPlanDto onboardingPlanDto) {
        return ResponseEntity.ok(onboardingPlanService.createOnboardingPlan(onboardingPlanDto));
    }


    @GetMapping
    public ResponseEntity<List<OnboardingPlan>> getOnboardingPlan() {
        return ResponseEntity.ok(onboardingPlanService.getAllOnboardingPlans());
    }
    
}
