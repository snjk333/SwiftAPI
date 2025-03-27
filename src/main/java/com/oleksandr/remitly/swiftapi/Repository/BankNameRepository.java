package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import com.oleksandr.remitly.swiftapi.Model.Entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankNameRepository extends JpaRepository<BankName, Long> {
    Optional<BankName> findByName(String bankName);

    Optional<BankName> findByNameAndCountry(String bankName, Country country);
}
