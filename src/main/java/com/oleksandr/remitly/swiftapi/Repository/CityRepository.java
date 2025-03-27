package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.City;
import com.oleksandr.remitly.swiftapi.Model.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String cityName);

    Optional<City> findByNameAndCountry(String cityName, Country country);
}
