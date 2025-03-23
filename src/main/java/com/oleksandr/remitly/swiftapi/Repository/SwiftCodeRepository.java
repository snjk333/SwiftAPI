package com.oleksandr.remitly.swiftapi.Repository;

import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import org.springframework.data.jpa.repository.JpaRepository;
import  com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import java.util.List;
import java.util.Optional;

public interface SwiftCodeRepository extends JpaRepository<SwiftCode, Integer>{

    //endpoint 1
    Optional<SwiftCode> findBySwiftCode(String swiftCode); // I will find optional swift code, take bank
    List<SwiftCode> findByBank(BankName bank); // And use bank to take all swift codes

    //endpoint 2
    List<SwiftCode> findByBank_Country_isoCode(String isoCode); // Bank -> Country - isoCode == isoCode.

    //endpoint 4
    void deleteBySwiftCode(String swiftCode); //delete row with this swift code

}
