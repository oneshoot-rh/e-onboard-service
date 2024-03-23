package ma.youcode.eonboardservice.dossiers;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.youcode.eonboardservice.candidates.Candidate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
@Table(name = "dossiers")
@Entity
public class Dossier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private UUID resumeId;
    private Boolean isEvaluated;
    private LocalDateTime createdAt;
    @OneToOne
    @JoinColumn(name = "candidateId")
    private Candidate candidate;
}
