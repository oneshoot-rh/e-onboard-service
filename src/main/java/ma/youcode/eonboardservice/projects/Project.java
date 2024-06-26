package ma.youcode.eonboardservice.projects;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import ma.youcode.eonboardservice.Employees.Employee;
import ma.youcode.eonboardservice.Sites.Site;
import ma.youcode.eonboardservice.enums.ProjectStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "projects")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @ManyToOne
    @JoinColumn(name = "familyId")
    private ProjectFamily family;

    @ManyToOne
    @JoinColumn(name = "projectManagerId")
    private Employee projectManager;

    @OneToMany
    @JoinTable(name = "projects_employees", joinColumns = @JoinColumn(name = "projectId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
    private List<Employee> deployedEmployees;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;



    public String getProjectManagerWithCode(){
        return new StringBuilder().append(this.projectManager.getName()).append(" (").append(this.projectManager.getEmployeeCode()).append(")").toString();
    }
    
}
