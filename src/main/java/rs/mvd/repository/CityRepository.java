package rs.mvd.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.mvd.domain.City;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class CityRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertNewCity(City city){
        em.persist(city);
        em.flush();
    }

    public Optional<City> getById(long id){
        try{
            City city = em.createQuery("SELECT c FROM City c where c.id = :id", City.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(city);
        }catch (NoResultException e){
            System.out.println("Exception occurred while getting city with id: " + id);
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

}
