package ma.youcode.eonboardservice.onboarding;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.youcode.eonboardservice.candidates.Candidate;
import ma.youcode.eonboardservice.email.EmailTemplate;
import ma.youcode.eonboardservice.joboffers.JobOffer;
import ma.youcode.eonboardservice.tenant.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "onboarding_plans")
@Entity
public class OnboardingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String flags;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "plannedAt")
    private LocalDateTime plannedAt; 

    @ManyToOne
    @JoinColumn(name = "emailTemplateId")
    @ToStringExclude
    @JsonIgnore
    private EmailTemplate emailTemplate;

    @OneToMany
    @ToStringExclude
    @JoinTable(name = "onboarding_plan_job_offers", joinColumns = @JoinColumn(name = "onboardingPlanId"), inverseJoinColumns = @JoinColumn(name = "jobOfferId"))
    private List<JobOffer> jobOffer;

    // @ManyToOne
    // @JoinColumn(name = "createdBy")
    // @JsonIgnore
    // @ToStringExclude
    // private User creator;

    @OneToMany
    @JoinTable(name = "onboading_plan_invitees", joinColumns = @JoinColumn(name = "onboardingPlanId"), inverseJoinColumns = @JoinColumn(name = "candidateId"))
    @ToStringExclude
    private List<Candidate> candidates;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
}
