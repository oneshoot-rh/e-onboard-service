package ma.youcode.eonboardservice.config;


import ma.youcode.eonboardservice.tenant.TenantRepository;
import ma.youcode.eonboardservice.tenant.UserRepository;
import ma.youcode.eonboardservice.tenant.db.HibernateTenantIdentifierResolver;
import ma.youcode.eonboardservice.tenant.db.TenantContext;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/default")
                .dataSource(dataSource)
                .schemas(TenantContext.DEFAULT_TENANT_ID)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner commandLineRunner(TenantRepository repository, DataSource dataSource) {
        return args -> {
            // replace with a web service call to get tenants
            RestTemplate restTemplate = new RestTemplate();
            
            repository.findAll().forEach(tenant -> {
                String tenantId = tenant.getOrganizationName();
                Flyway flyway = Flyway.configure()
                        .locations("db/migration/tenants")
                        .dataSource(dataSource)
                        .schemas(tenantId)
                        .load();
                flyway.migrate();
            });
        };
    }
}
