package ma.youcode.eonboardservice.onboarding;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OnboardingPlanDto(
    @NotNull Long jobOfferId,
    @NotEmpty @NotNull List<Long> candidatesIds,
    @NotNull String onboardingSubject,
    @NotNull LocalDateTime onboardingDate,
    boolean notifyCandidates,
    Long emailTemplateId,
    String emailSubjectName
) {
    
}
