package com.oleksandr.remitly.swiftapi.Service;

import com.oleksandr.remitly.swiftapi.Model.DTO.BankResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.CountrySwiftCodesResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeRequestDTO;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;

import java.util.List;

public interface SwiftService {
    BankResponseDTO getSwiftCodeDetails(String swiftCode); //endpoint 1

    CountrySwiftCodesResponseDTO getSwiftCodesByISO(String swiftCode); //endpoint 2

    void addSwiftCode(SwiftCodeRequestDTO swiftCode); //endpoint 3

    void deleteSwiftCode(String swiftCode); //endpoint 4

}
