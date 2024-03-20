package ma.youcode.eonboardservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import ma.youcode.eonboardservice.candidates.Candidate;
import ma.youcode.eonboardservice.dossiers.Dossier;
import ma.youcode.eonboardservice.dossiers.DossierService;

@SpringBootApplication
@RequiredArgsConstructor
public class EOnboardServiceApplication {

	private final DossierService dossierService;

	public static void main(String[] args) {
		SpringApplication.run(EOnboardServiceApplication.class, args);
	}

	//@Bean
	CommandLineRunner runner() {
		return args -> {
			List.of(
				Dossier.builder()
					.id(UUID.randomUUID())
					.candidate(Candidate.builder().id(1L).firstName("John").lastName("Doe").email("johnDoe@gmail.com").build())
					.isEvaluated(Boolean.FALSE)
					.createdAt(LocalDateTime.now())
					.resumeId(UUID.randomUUID())
					.build(),
				Dossier.builder()
					.id(UUID.randomUUID())
					.candidate(Candidate.builder().id(2L).firstName("Jane").lastName("Doe").email("Jane@hotmail.fr").build())
					.isEvaluated(Boolean.FALSE)
					.createdAt(LocalDateTime.now())
					.resumeId(UUID.randomUUID())
					.build()
			).forEach(dossierService::saveDossier);
		};
	}

}
