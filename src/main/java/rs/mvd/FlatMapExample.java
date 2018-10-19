package rs.mvd;

import rs.mvd.domain.City;
import rs.mvd.domain.State;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {


    public static void main(String[] args) {
        State state = new State(1L);
        state.setName("State");
        state.setCities(Arrays.asList(new City(1L, "City1"), new City(2L, "City2"), new City(3L, "City3")));

        State state2 = new State(2L);
        state2.setName("State2");
        state2.setCities(Arrays.asList(new City(4L, "City4"), new City(5L, "City5"), new City(6L, "City6")));

        List<State> states = Arrays.asList(state, state2);

        List<City> cities = states
                .stream()
                .flatMap(s -> s.getCities().stream())
                .filter(s -> s.getId() < 6)
                .collect(Collectors.toList());

        for (City city : cities) {
            System.out.println(city.getId());
        }


    }

}
