package rs.mvd.models;

import rs.mvd.domain.City;

public class CityModel {

    private long id;
    private String name;
    private String state;

    public CityModel() {
    }

    public CityModel(long id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public CityModel(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.state = city.getState().getName();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityModel cityModel = (CityModel) o;

        return id == cityModel.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
