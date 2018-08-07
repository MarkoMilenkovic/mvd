package rs.mvd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.mvd.domain.State;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.factories.StateFactory;
import rs.mvd.models.StateModel;
import rs.mvd.response.Responses;
import rs.mvd.services.StateService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@RestController
@Path("private/state")
public class StateEndpoint {

    @Autowired
    private StateService stateService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses getStateById(@PathParam("id") long id) {
        Optional<State> stateOptional = stateService.getById(id);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            StateModel stateModel = StateFactory.createStateModel(state);
            return ResponseFactory.ok("State returned successfully!", stateModel);
        } else {
            throw new BadRequestException("State with id " + id + " does not exists.");
        }
    }

    @GetMapping(produces = "application/json")
    public Responses getStatesPaging(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        List<State> states = stateService.getStates(limit, offset);
        List<StateModel> stateModel = StateFactory.createStateModelList(states);
        return ResponseFactory.ok("State returned successfully!", stateModel);
    }

    @PostMapping(produces = "application/json")
    public Responses insertNewState(@RequestBody StateModel stateModel) {
        State state = StateFactory.createState(stateModel);
        stateService.save(state);
        return ResponseFactory.ok("State inserted successfully!");
    }
}
