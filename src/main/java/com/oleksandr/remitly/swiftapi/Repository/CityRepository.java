package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String cityName);
}
