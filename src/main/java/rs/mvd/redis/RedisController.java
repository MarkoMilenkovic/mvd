package rs.mvd.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.mvd.domain.State;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.factories.StateFactory;
import rs.mvd.models.StateModel;
import rs.mvd.response.Responses;
import rs.mvd.services.StateService;

@RestController
@RequestMapping("private/redis")
public class RedisController {

    @Autowired
    private StateService stateService;

    @GetMapping(value = "{id}", produces = "application/json")
    public Responses getStateById(@PathVariable("id") long id) {
        State state = stateService.getById(id).orElseThrow(() -> new BadRequestException("State with this id does not exists!"));
        return ResponseFactory.ok("State updated successfully!", state);
    }

    @PostMapping(produces = "application/json")
    public Responses insertNewState(@RequestBody StateModel stateModel) {
        State state = StateFactory.createState(stateModel);
        stateService.save(state);
        return ResponseFactory.ok("State inserted successfully!", state);
    }

    @DeleteMapping("{id}")
    public Responses deleteStateById(@PathVariable("id") int id){
        return ResponseFactory.ok("State deleted successfully!");
    }
}
