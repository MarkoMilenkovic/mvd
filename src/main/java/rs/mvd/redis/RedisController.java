package rs.mvd.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import rs.mvd.domain.State;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.factories.StateFactory;
import rs.mvd.models.StateModel;
import rs.mvd.repository.StateRepository;
import rs.mvd.response.Responses;
import rs.mvd.services.StateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("private/redis")
public class RedisController {

    //    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private ObjectMapper objectMapper;
    @Autowired
    private StateRepository stateRepository;

//    @GetMapping(value = "{id}", produces = "application/json")
//    public Responses getStateById(@PathVariable("id") long id) throws IOException {
//        String s = redisTemplate.opsForValue().get("state/" + id);
//        if (s != null && !s.isEmpty()) {
//            State state = objectMapper.readValue(s, State.class);
//            return ResponseFactory.ok("Success", state);
//        } else {
//            Optional<State> stateOptional = stateService.getById(id);
//            if (stateOptional.isPresent()) {
//                State state = stateOptional.get();
//                StateModel stateModel = StateFactory.createStateModel(state);
//                return ResponseFactory.ok("State returned successfully!", stateModel);
//            } else {
//                throw new BadRequestException("State with id " + id + " does not exists.");
//            }
//        }
//    }

//    @GetMapping
//    public Responses getAllStates() throws IOException {
//        Set<String> keys = redisTemplate.keys("state/*");
//        List<State> states = new ArrayList<>();
//        for (String key : keys) {
//            String s = redisTemplate.opsForValue().get(key);
//            if (s != null && !s.isEmpty()) {
//                State state = objectMapper.readValue(s, State.class);
//                states.add(state);
//            }
//        }
//        return ResponseFactory.ok("Success", states);
//    }

//    @PostMapping(produces = "application/json")
//    public Responses insertNewState(@RequestBody StateModel stateModel) throws JsonProcessingException {
//        State state = StateFactory.createState(stateModel);
//        State insert = stateService.save(state);
//        redisTemplate.opsForValue().set("state/" + insert.getId(), objectMapper.writeValueAsString(insert));
//        return ResponseFactory.ok("State inserted successfully!");
//    }
//
//    @DeleteMapping("{id}")
//    public Responses deleteStateById(@PathVariable("id") int id){
//        stateService.deleteStateById(id);
//        redisTemplate.delete("state/" + id);
//        return ResponseFactory.ok("State deleted successfully!");
//    }
//
//    @PutMapping("{id}")
//    public Responses updateState(@PathVariable("id") int id, @RequestBody StateModel model) throws JsonProcessingException {
//        State state = stateService.getById(id).orElseThrow(() -> new BadRequestException("State with id: " + id + " does not exists!"));
//        state.setName(model.getName());
//        stateService.update(state);
//        redisTemplate.opsForValue().set("state/"+id, objectMapper.writeValueAsString(state));
//        return ResponseFactory.ok("State updated successfully!");
//    }


//    @GetMapping(value = "{id}", produces = "application/json")
//    @Cacheable(value = "states", key = "#id")
//    public Responses getStateById(@PathVariable("id") long id) {
//        StateModel stateModel = new StateModel();
//        stateModel.setId(id);
//        stateModel.setName("JOVAN");
//        return ResponseFactory.ok("State updated successfully!", stateModel);
//    }
//
//    @PostMapping(produces = "application/json")
//    @CachePut(value = "states", key = "#stateModel.id")
//    public Responses insertNewState(@RequestBody StateModel stateModel) throws JsonProcessingException {
//        return ResponseFactory.ok("State inserted successfully!", stateModel);
//    }
//
//    @DeleteMapping("{id}")
//    @CacheEvict(value = "states", key = "#id")
//    public Responses deleteStateById(@PathVariable("id") int id){
//        return ResponseFactory.ok("State deleted successfully!");
//    }


    @GetMapping(value = "{id}", produces = "application/json")
    public Responses getStateById(@PathVariable("id") long id) {
        State byId = stateRepository.getById(id).get();
        return ResponseFactory.ok("State updated successfully!", byId);
    }

    @PostMapping(produces = "application/json")
    public Responses insertNewState(@RequestBody StateModel stateModel) {
        State state = StateFactory.createState(stateModel);
        State inserted = stateRepository.insert(state);
        return ResponseFactory.ok("State inserted successfully!", inserted);
    }

    @DeleteMapping("{id}")
    public Responses deleteStateById(@PathVariable("id") int id){
        return ResponseFactory.ok("State deleted successfully!");
    }
}
