package rs.mvd.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.mvd.domain.Tenant;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class TenantRepository {

    @PersistenceContext
    private EntityManager em;
    @Value("${tenantdb}")
    private String tenantdb;// = "tenantdb";

    public Optional<Tenant> getByName(String name) {
        setSchema(tenantdb);
        String singleResult = (String) em.createNativeQuery("SELECT DATABASE()").getSingleResult();
        System.out.println("---------------- " + singleResult + " ----------------");
        try {
            Tenant tenant = (Tenant) em.createNativeQuery("SELECT t.* FROM tenantdb.tenant t where t.name = '" + name + "'", Tenant.class)
                    .getSingleResult();
            return Optional.of(tenant);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public void setSchema(String schemaName) {
        em.createNativeQuery("use " + schemaName)
                .executeUpdate();
    }

}
