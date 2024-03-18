package ma.youcode.eonboardservice.candidates;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ma.youcode.eonboardservice.dossiers.Dossier;


@ToString
@Document(collection = "candidates")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidate {
    
    @Id
    Long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Dossier dossier;
}
