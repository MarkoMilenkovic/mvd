package rs.mvd.repository;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.mvd.domain.State;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StateRepository {//} extends CrudRepository<State, Integer> {

    @PersistenceContext
    private EntityManager em;

    @CachePut(value = "states", key = "#state.id")
    public State insert(State state) {
        em.persist(state);
        em.flush();
        return state;
    }

    @Cacheable(value = "states", key = "#id")
    public Optional<State> getById(long id) {
        try {
            State state = em.createQuery("SELECT s FROM State s where s.id = :id", State.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(state);
        } catch (NoResultException e) {
            System.out.println("Exception occurred while getting state with id: " + id);
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @CacheEvict(value = "states", key = "#id")
    public void delete(int id){
        em.createNativeQuery("delete from state where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Optional<State> getOneByName(String stateName) {
        try {
            State state = em.createQuery("SELECT s FROM State s where s.name = :name", State.class)
                    .setParameter("name", stateName)
                    .getSingleResult();
            return Optional.of(state);
        } catch (NoResultException e) {
            System.out.println("Exception occurred while getting state by name: " + stateName);
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public List<State> getStates(int limit, int offset) {
        return em.createQuery("SELECT s FROM State s", State.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}
