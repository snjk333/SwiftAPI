package com.oleksandr.remitly.swiftapi.Controller;

import com.oleksandr.remitly.swiftapi.Model.DTO.BankResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.CountrySwiftCodesResponseDTO;
import com.oleksandr.remitly.swiftapi.Model.DTO.SwiftCodeRequestDTO;
import com.oleksandr.remitly.swiftapi.Service.SwiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "main_methods")
@RestController
@RequestMapping("/v1")
public class MainController {

    private final SwiftService swiftService;

    @Autowired
    public MainController(SwiftService swiftService) {
        this.swiftService = swiftService;
    }


    @Operation(
            summary = "Retrieve details of a single SWIFT code whether for a headquarters or branches."
    )
    @GetMapping("/swift-codes/{swift-code}")
    public BankResponseDTO findBySwiftCode(@PathVariable("swift-code") String swiftCode) {
        return swiftService.getSwiftCodeDetails(swiftCode);
    }
    @Operation(
            summary = "Return all SWIFT codes with details for a specific country (both headquarters and branches)."
    )
    @GetMapping("/swift-codes/country/{countryISO2code}")
    public CountrySwiftCodesResponseDTO getSwiftCodesByISO(@PathVariable("countryISO2code") String countryISO2) {
        return swiftService.getSwiftCodesByISO(countryISO2);
    }

    @Operation(
            summary = "Adds new SWIFT code entries to the database for a specific country."
    )
    @PostMapping("/swift-codes")
    public String postSwiftCode(@RequestBody SwiftCodeRequestDTO swiftCodeRequestDTO) {
        swiftService.addSwiftCode(swiftCodeRequestDTO);
        return swiftCodeRequestDTO.getSwiftCode();
    }

    @Operation(
            summary = "Deletes swift-code data if swiftCode matches the one in the database."
    )
    @DeleteMapping("/swift-codes/{swift-code}")
    public String deleteSwiftCode(@PathVariable("swift-code") String swiftCode) {
        swiftService.deleteSwiftCode(swiftCode);
        return swiftCode;
    }

}
