package rs.mvd.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.mvd.domain.LayoutProperties;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class LayoutRepository {

    @PersistenceContext
    private EntityManager em;

    public LayoutProperties getLayoutProperties(String templateName) {
        try {
            return (LayoutProperties) em.createNativeQuery("select * from layout_properties where template_name = :templateName", LayoutProperties.class)
                    .setParameter("templateName", templateName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
