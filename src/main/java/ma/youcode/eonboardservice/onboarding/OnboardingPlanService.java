package ma.youcode.eonboardservice.onboarding;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import lombok.RequiredArgsConstructor;
import ma.youcode.eonboardservice.candidates.CandidateRepository;
import ma.youcode.eonboardservice.email.EmailTemplate;
import ma.youcode.eonboardservice.email.EmailTemplateRepository;
import ma.youcode.eonboardservice.joboffers.JobOffer;
import ma.youcode.eonboardservice.joboffers.JobOfferRepository;

@Service
@RequiredArgsConstructor
public class OnboardingPlanService {

    private final OnboardingPlanRepository onboardingPlanRepository;
    private final EmailTemplateRepository emailTemplateRepository;
    private final CandidateRepository candidateRepository;
    private final JobOfferRepository jobOfferRepository; 



    public OnboardingPlan createOnboardingPlan(OnboardingPlanDto onboardingPlanDto) {
        var candidates = candidateRepository.findAllById(onboardingPlanDto.candidatesIds());
        EmailTemplate emailTemplate = null;
        if(onboardingPlanDto.notifyCandidates()){
            emailTemplate = emailTemplateRepository.findById(onboardingPlanDto.emailTemplateId()).orElse(EmailTemplate.builder().build());
            // process notification
        }
        var jobOffer = jobOfferRepository.findById(onboardingPlanDto.jobOfferId()).orElseThrow(() -> new RuntimeException("Job offer not found"));
        var onboardingPlan = OnboardingPlan.builder()
                .emailTemplate(emailTemplate)
                .name(onboardingPlanDto.onboardingSubject())
                .plannedAt(onboardingPlanDto.onboardingDate())
                .candidates(candidates)
                .jobOffer(List.of(jobOffer))
                .build();
        var onboardingPlanSaved = onboardingPlanRepository.save(onboardingPlan);
        return onboardingPlanSaved;
    }
    
}
