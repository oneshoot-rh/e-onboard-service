package ma.youcode.eonboardservice.tenant;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.youcode.eonboardservice.tenant.db.TenantContext;

@Service
@RequiredArgsConstructor
@Slf4j
public class TenantService {
    private final TenantRepository tenantRepository;
    private final DataSource dataSource;


    private void initDataBase(String scheme) {
        Flyway flyway = Flyway.configure()
                .locations("db/migration/tenants")
                .dataSource(dataSource)
                .schemas(scheme)
                .load();
        flyway.migrate();
    }

    public boolean onBoardNewTenant(String tenantId){
        // to verify existence of the db later !
        boolean created=false; 
        String schema = new StringBuilder().append("eonbdb_").append(tenantId).toString();
        log.info("onboarding new tenant with id {}, and database identifier as {}",tenantId,schema);
        try{
            initDataBase(schema);
            log.debug("database is initialized successfully");
            created=true;
        }catch(FlywayException ex){
            log.debug("failed to initilize database");
            log.debug("ISSUE: {}",ex.getMessage());
        }
        return created;
    }
}
