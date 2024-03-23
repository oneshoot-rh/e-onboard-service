package ma.youcode.eonboardservice.candidates;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import ma.youcode.eonboardservice.dossiers.Dossier;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "candidates")
public class Candidate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    // @OneToOne
    // @JsonIgnore
    // private Dossier dossier;
}
