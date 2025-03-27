package com.oleksandr.remitly.swiftapi.Mapper;

import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeRequestDTO;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import com.oleksandr.remitly.swiftapi.Model.Entity.City;
import com.oleksandr.remitly.swiftapi.Repository.BankNameRepository;
import com.oleksandr.remitly.swiftapi.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SwiftCodeMapper {

    private final BankNameRepository bankNameRepository;
    private final CityRepository cityRepository;

    @Autowired
    public SwiftCodeMapper(BankNameRepository bankNameRepository, CityRepository cityRepository) {
        this.bankNameRepository = bankNameRepository;
        this.cityRepository = cityRepository;
    }

    protected BankName getBank(String bankName) {
        return bankNameRepository.findByName(bankName)
                .orElseThrow(() -> new RuntimeException("Bank not found: " + bankName));
    }

    protected City getCity(String cityName) {
        return cityRepository.findByName(cityName)
                .orElseThrow(() -> new RuntimeException("City not found: " + cityName));
    }

    public SwiftCode toEntity(SwiftCodeRequestDTO dto){
        return new SwiftCode(
                dto.getSwiftCode(),
                dto.isHeadquarter(),
                dto.getAddress(),
                getBank(getBank(dto.getBankName()).getName()),
                null // we can't take city from request -> we have only country
        );
    }


}
