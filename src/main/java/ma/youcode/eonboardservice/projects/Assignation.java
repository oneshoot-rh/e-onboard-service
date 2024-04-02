package ma.youcode.eonboardservice.projects;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.youcode.eonboardservice.Employees.Employee;
import ma.youcode.eonboardservice.Sites.Site;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "assignations")
@Entity
public class Assignation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deployedJobTitle; 

    @OneToOne
    @JoinColumn(name = "projectId")
    private Project project; 
    @OneToOne
    @JoinColumn(name = "employeeId")
    private Employee employee; 
    private String assignationCode;
    @Enumerated(EnumType.STRING)
    private AssignationType assignationType;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignationStartDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime assignationEndDate;

    @ManyToOne
    @JoinColumn(name = "siteId")
    private Site site;

    private Long initiatedBy; 




    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    
}
