package ma.youcode.eonboardservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.RequiredArgsConstructor;
import ma.youcode.eonboardservice.candidates.Candidate;
import ma.youcode.eonboardservice.dossiers.Dossier;
import ma.youcode.eonboardservice.dossiers.DossierService;
import ma.youcode.eonboardservice.tenant.Tenant;
import ma.youcode.eonboardservice.tenant.TenantService;

@SpringBootApplication
@RequiredArgsConstructor
public class EOnboardServiceApplication {

	private final DossierService dossierService;
	private final TenantService tenantService;

	public static void main(String[] args) {
		SpringApplication.run(EOnboardServiceApplication.class, args);
	}

	@KafkaListener(topics = "tenant-db-init", groupId = "group_id")
	public void listenToDBInitTopic(String tenantId) {
		System.out.println("Received Message in group group_id: " + tenantId);
		tenantService.onBoardNewTenant(tenantId);
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
