package rs.mvd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.mvd.domain.City;
import rs.mvd.repository.CityRepository;

import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public void save(City city){
        cityRepository.insertNewCity(city);
    }

    public Optional<City> getById(long id){
        return cityRepository.getById(id);
    }

}


