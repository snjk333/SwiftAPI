package com.oleksandr.remitly.swiftapi.Reader;

import com.oleksandr.remitly.swiftapi.Model.Entity.BankName;
import com.oleksandr.remitly.swiftapi.Model.Entity.City;
import com.oleksandr.remitly.swiftapi.Model.Entity.Country;
import com.oleksandr.remitly.swiftapi.Model.Entity.SwiftCode;
import com.oleksandr.remitly.swiftapi.Model.Entity.Timezone;
import com.oleksandr.remitly.swiftapi.Repository.BankNameRepository;
import com.oleksandr.remitly.swiftapi.Repository.CityRepository;
import com.oleksandr.remitly.swiftapi.Repository.CountryRepository;
import com.oleksandr.remitly.swiftapi.Repository.SwiftCodeRepository;
import com.oleksandr.remitly.swiftapi.Repository.TimezoneRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Reader;

@Component
public class CSVDataLoader implements CommandLineRunner {

    private final SwiftCodeRepository swiftCodeRepository;
    private final BankNameRepository bankNameRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final TimezoneRepository timezoneRepository;

    @Value("classpath:SwiftCodes.csv")
    private Resource swiftCodesFile;

    @Autowired
    public CSVDataLoader(SwiftCodeRepository swiftCodeRepository,
                         BankNameRepository bankNameRepository,
                         CityRepository cityRepository,
                         CountryRepository countryRepository,
                         TimezoneRepository timezoneRepository) {
        this.swiftCodeRepository = swiftCodeRepository;
        this.bankNameRepository = bankNameRepository;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.timezoneRepository = timezoneRepository;
    }

    @Override
    public void run(String... args) {
        loadSWIFTDataFromCSV();
    }

    private void loadSWIFTDataFromCSV() {
        try (Reader reader = new InputStreamReader(swiftCodesFile.getInputStream())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());

            for (CSVRecord record : csvParser) {

                String swiftCode = record.get("SWIFT CODE");
                if(swiftCodeRepository.findBySwiftCode(swiftCode).isPresent()) {
                    continue;
                }

                String isoCode = record.get("COUNTRY ISO2 CODE");
                String bankName = record.get("NAME");
                String address = record.get("ADDRESS");
                String cityName = record.get("TOWN NAME");
                String countryName = record.get("COUNTRY NAME");
                String timezoneName = record.get("TIME ZONE");
                
                Timezone timezone = timezoneRepository.findByTimezoneName(timezoneName)
                        .orElseGet(() -> timezoneRepository.save(new Timezone(timezoneName)));

                Country country = countryRepository.findByName(countryName)
                        .orElseGet(() -> countryRepository.save(new Country(isoCode, countryName, timezone)));

                City city = cityRepository.findByNameAndCountry(cityName, country)
                        .orElseGet(() -> cityRepository.save(new City(cityName, country)));

                BankName bank = bankNameRepository.findByNameAndCountry(bankName, country)
                        .orElseGet(() -> bankNameRepository.save(new BankName(bankName, country)));

                boolean isHeadquarter = swiftCode.length() == 8 || swiftCode.endsWith("XXX");

                SwiftCode swiftCodeEntity = new SwiftCode(swiftCode, isHeadquarter, address, bank, city);
                swiftCodeRepository.save(swiftCodeEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
