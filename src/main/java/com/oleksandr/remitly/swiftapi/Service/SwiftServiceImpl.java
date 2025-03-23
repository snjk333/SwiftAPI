package com.oleksandr.remitly.swiftapi.Service;

import com.oleksandr.remitly.swiftapi.Mapper.SwiftCodeMapper;
import com.oleksandr.remitly.swiftapi.Model.DTO.*;
import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import com.oleksandr.remitly.swiftapi.Model.Entity.Country;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import com.oleksandr.remitly.swiftapi.Repository.SwiftCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
        Optional<SwiftCode> swiftCodeOptional = swiftCodeRepository.findBySwiftCode(swiftCode);
        if(swiftCodeOptional.isEmpty()) {
                return new
                        BankResponseDTO("empty",
                        "empty",
                        "empty",
                        "empty",
                        false,
                        swiftCode,
                        Collections.emptyList());
        }
        SwiftCode swiftCodeObject = swiftCodeOptional.get();

        String address = swiftCodeObject.getAddress();
        String bankName = swiftCodeObject.getBank().getName();
        Country country = swiftCodeObject.getCity().getCountry();
        String countryISOCode = country.getIsoCode();
        String countryName = country.getName();
        boolean isHeadquarter = swiftCodeObject.isHeadquarter();

        BankName bank = swiftCodeObject.getBank();

        List<SwiftCode> listOfSwiftCodes = swiftCodeRepository.findByBank(bank);

        List<BranchDTO> branchDTOS = listOfSwiftCodes.stream()
                .map(br -> new BranchDTO(br.getAddress(),
                        br.getBank().getName(),
                        br.getCity().getCountry().getIsoCode(),
                        br.isHeadquarter(),
                        br.getSwiftCode())).filter(sc -> !sc.getSwiftCode().equals(swiftCode))
                .toList();
        return new BankResponseDTO(address, bankName, countryISOCode, countryName, isHeadquarter,swiftCode ,branchDTOS);
    }

    @Override
    public CountrySwiftCodesResponseDTO getSwiftCodesByISO(String isoCode) {
        List<SwiftCode> swiftCodeList = swiftCodeRepository.findByBank_Country_isoCode(isoCode);
        // we take list of swift codes by ISO2 code
        if (swiftCodeList.isEmpty()) {
            return new CountrySwiftCodesResponseDTO(isoCode, "Unknown", Collections.emptyList());
        }
        // if we don't have any codes -> return empty list

        // we take info step by step
        // take county at first
        Country country = swiftCodeList.get(0).getBank().getCountry();

        //take ISO2 code from country
        String countryISO = country.getIsoCode();

        //take name from country
        String countryName = country.getName();

        //map List of DTO objects from swiftCodeList
        List<SwiftCodeDetailsDTO> details = swiftCodeList.stream()
                .map(sc -> new SwiftCodeDetailsDTO(
                        sc.getAddress(),
                        sc.getBank().getName(),
                        countryISO,
                        sc.isHeadquarter(),
                        sc.getSwiftCode()))
                .collect(Collectors.toList());

        // use all info to return
        return new CountrySwiftCodesResponseDTO(countryISO, countryName, details);

    }

    @Override
    public void addSwiftCode(SwiftCodeRequestDTO swiftCode) {
        SwiftCode swiftObject = swiftCodeMapper.toEntity(swiftCode); //map DTO to entity
        swiftCodeRepository.save(swiftObject);
    }

    @Override
    public void deleteSwiftCode(String swiftCode) {
        swiftCodeRepository.deleteBySwiftCode(swiftCode);
    }
}
