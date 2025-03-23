package com.oleksandr.remitly.swiftapi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeRequestDTO;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import com.oleksandr.remitly.swiftapi.Model.Entity.City;
import com.oleksandr.remitly.swiftapi.Repository.BankNameRepository;
import com.oleksandr.remitly.swiftapi.Repository.CityRepository;

@Mapper(componentModel = "spring")
public abstract class SwiftCodeMapper {

    private final BankNameRepository bankNameRepository;
    private final CityRepository cityRepository;

    @Autowired
    public SwiftCodeMapper(BankNameRepository bankNameRepository, CityRepository cityRepository) {
        this.bankNameRepository = bankNameRepository;
        this.cityRepository = cityRepository;
    }

    @Mapping(target = "bank", expression = "java(getBank(dto.getBankName()))")
    @Mapping(target = "city", expression = "java(getCity(dto.getCountryName()))")
    public abstract SwiftCode toEntity(SwiftCodeRequestDTO dto);

    protected BankName getBank(String bankName) {
        return bankNameRepository.findByName(bankName)
                .orElseThrow(() -> new RuntimeException("Bank not found: " + bankName));
    }

    protected City getCity(String cityName) {
        return cityRepository.findByName(cityName)
                .orElseThrow(() -> new RuntimeException("City not found: " + cityName));
    }
}
