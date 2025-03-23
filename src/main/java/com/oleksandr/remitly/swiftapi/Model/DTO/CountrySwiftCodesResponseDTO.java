package com.oleksandr.remitly.swiftapi.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountrySwiftCodesResponseDTO {
    private String countryISO2;
    private String countryName;
    private List<SwiftCodeDetailsDTO> swiftCodes;
}