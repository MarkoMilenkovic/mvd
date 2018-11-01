package rs.mvd;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import rs.mvd.domain.Tenant;
import rs.mvd.repository.TenantRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class MvdApplication {

//    @Autowired
//    private TenantRepository tenantRepository;
//    @Autowired
//    private Flyway flyway;

    public static void main(String[] args) {
        SpringApplication.run(MvdApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        startFlyway();
//    }

//    private void startFlyway() {
//        List<Tenant> tenants = tenantRepository.getAllTenants();
//        String[] schemaNames = tenants.stream().map(Tenant::getName).toArray(String[]::new);
//        for (String schemaName : schemaNames) {
//            flyway.setSchemas(schemaName);
//            flyway.migrate();
//        }
//    }

}
