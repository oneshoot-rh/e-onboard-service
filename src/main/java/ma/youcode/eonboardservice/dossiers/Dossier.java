package ma.youcode.eonboardservice.dossiers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ma.youcode.eonboardservice.candidates.Candidate;

@Document(collection = "dossiers")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class Dossier {
    @Id
    private UUID id;
    private UUID resumeId;
    private Boolean isEvaluated;
    private LocalDateTime createdAt;
    private Candidate candidate;
}
