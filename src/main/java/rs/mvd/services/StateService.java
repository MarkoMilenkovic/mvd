package rs.mvd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.mvd.domain.State;
import rs.mvd.repository.StateRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public Optional<State> getById(long id){
        return stateRepository.getById(id);
    }

    public void save(State state) {
        stateRepository.insert(state);
    }

    public Optional<State> getOneByName(String stateName) {
        return stateRepository.getOneByName(stateName);
    }

    public List<State> getStates(int limit, int offset) {
        return stateRepository.getStates(limit, offset);
    }

}
