package rs.mvd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.mvd.domain.City;
import rs.mvd.domain.State;
import rs.mvd.exceptions.BadRequestException;
import rs.mvd.factories.CityFactory;
import rs.mvd.models.CityModel;
import rs.mvd.response.Responses;
import rs.mvd.factories.ResponseFactory;
import rs.mvd.services.CityService;
import rs.mvd.services.StateService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@RestController
@Path("private/cities")
public class CityEndpoint {

    @Autowired
    private CityService cityService;
    @Autowired
    private StateService stateService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Responses getCityById(@PathParam("id") /*@PathVariable("id")*/ long id) {
        Optional<City> cityOptional = cityService.getById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            CityModel cityModel = CityFactory.createCityModelFromCity(city);
            return ResponseFactory.ok("City returned successfully!", cityModel);
        } else {
            throw new BadRequestException("City with id " + id + " does not exists.");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Responses insertNewCity(City city) {
        String stateName = city.getState().getName();
        Optional<State> stateOptional = stateService.getOneByName(stateName);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            city.setState(state);
            cityService.save(city);
            CityModel cityModel = CityFactory.createCityModelFromCity(city);
            return ResponseFactory.ok("City inserted successfully!", cityModel);
        } else {
            throw new BadRequestException("City: " + stateName + " does not exists.");
        }
    }

}
