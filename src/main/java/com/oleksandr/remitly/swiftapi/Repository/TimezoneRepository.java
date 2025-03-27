package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.Timezone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimezoneRepository extends JpaRepository<Timezone, Long> {
    Optional<Timezone> findByTimezoneName(String timezoneName);
}
