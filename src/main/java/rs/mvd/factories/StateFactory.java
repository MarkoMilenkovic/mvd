package rs.mvd.factories;

import rs.mvd.domain.State;
import rs.mvd.domain.City;
import rs.mvd.models.StateModel;

import java.util.List;
import java.util.stream.Collectors;

public class StateFactory {

    public static StateModel createStateModel(State state) {
        return new StateModel(state);
    }

    public static State createState(StateModel stateModel) {
        State state = new State();
        state.setName(stateModel.getName());
        if (stateModel.getCities() != null && !stateModel.getCities().isEmpty()) {
            List<City> cityList = stateModel.getCities().stream().map(e -> new City(e, state)).collect(Collectors.toList());
            state.setCities(cityList);
        }
        return state;
    }

    public static List<StateModel> createStateModelList(List<State> states) {
//        List<StateModel> stateModels = new ArrayList<>();
//        for (State state : states) {
//            StateModel stateModel = createStateModel(state);
//            stateModels.add(stateModel);
//        }
        return states.stream().map(StateFactory::createStateModel).collect(Collectors.toList());
//        return stateModels;
    }
}
