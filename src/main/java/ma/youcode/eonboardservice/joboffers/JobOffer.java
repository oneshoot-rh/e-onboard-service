package ma.youcode.eonboardservice.joboffers;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "jobOffers")
@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private String jobType;
    private String jobCategory;
    private String jobStatus;
    private String jobSalary;
    private String jobExperience;
    private String jobQualification;
    private String jobSkills;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime jobPostedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime jobClosedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime jobDeadline;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    
}
