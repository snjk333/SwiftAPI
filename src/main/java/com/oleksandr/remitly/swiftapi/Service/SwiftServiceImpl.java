package com.oleksandr.remitly.swiftapi.Service;

import com.oleksandr.remitly.swiftapi.Mapper.SwiftCodeMapper;
import com.oleksandr.remitly.swiftapi.Model.DTO.BankResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.CountrySwiftCodesResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeDetailsDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeRequestDTO;
import com.oleksandr.remitly.swiftapi.Model.Entity.Country;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import com.oleksandr.remitly.swiftapi.Repository.SwiftCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SwiftServiceImpl implements SwiftService{

    SwiftCodeRepository swiftCodeRepository;
    SwiftCodeMapper swiftCodeMapper;

    @Autowired
    public SwiftServiceImpl(SwiftCodeRepository swiftCodeRepository, SwiftCodeMapper swiftCodeMapper) {
        this.swiftCodeRepository = swiftCodeRepository;
        this.swiftCodeMapper = swiftCodeMapper;
    }

    @Override
    public BankResponseDTO getSwiftCodeDetails(String swiftCode) {
        return null;
    }

    @Override
    public CountrySwiftCodesResponseDTO getSwiftCodesByISO(String isoCode) {
       return null;
    }

    @Override
    public void addSwiftCode(SwiftCodeRequestDTO swiftCode) {
        SwiftCode swiftObject = swiftCodeMapper.toEntity(swiftCode);
        swiftCodeRepository.save(swiftObject);
    }

    @Override
    public void deleteSwiftCode(String swiftCode) {
        swiftCodeRepository.deleteBySwiftCode(swiftCode);
    }
}
