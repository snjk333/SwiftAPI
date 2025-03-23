package com.oleksandr.remitly.swiftapi.Service;

import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;

import java.util.List;

public interface SwiftService {
    List<SwiftCode> getSwiftCodeDetails(String swiftCode); //endpoint 1

    List<SwiftCode> getSwiftCodesByISO(String swiftCode); //endpoint 2

    void addSwiftCode(SwiftCode swiftCode); //endpoint 3

    void deleteSwiftCode(String swiftCode); //endpoint 4

}
