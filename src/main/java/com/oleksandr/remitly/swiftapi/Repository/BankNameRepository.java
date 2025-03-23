package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankNameRepository extends JpaRepository<BankName, Integer> {
    Optional<BankName> findByName(String bankName);
}
