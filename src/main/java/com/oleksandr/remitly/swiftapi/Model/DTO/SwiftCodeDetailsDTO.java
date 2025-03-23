package com.oleksandr.remitly.swiftapi.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwiftCodeDetailsDTO {
    private String address;
    private String bankName;
    private String countryISO2;
    private boolean isHeadquarter;
    private String swiftCode;
}