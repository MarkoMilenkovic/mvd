package rs.mvd.factories;

import rs.mvd.domain.City;
import rs.mvd.models.CityModel;

public class CityFactory {

    public static CityModel createCityModelFromCity(City city){
        return new CityModel(city);
    }

}
