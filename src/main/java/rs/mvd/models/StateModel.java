package rs.mvd.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import rs.mvd.domain.City;
import rs.mvd.domain.State;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StateModel {

    private long id;
    private String name;
    private List<String> cities;

    public StateModel() {
    }

    public StateModel(long id, String name, List<String> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public StateModel(State state) {
        this.id = state.getId();
        this.name = state.getName();
        if (state.getCities() != null) {
            cities = state.getCities().stream().map(City::getName).collect(Collectors.toList());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateModel that = (StateModel) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "StateModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cities=" + cities +
                '}';
    }

}
