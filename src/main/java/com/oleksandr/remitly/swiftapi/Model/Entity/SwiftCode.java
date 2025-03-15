package com.oleksandr.remitly.swiftapi.Model.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "swift_codes")
public class SwiftCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "swift_code", nullable = false)
    private String swiftCode;

    @Column(name = "is_headquarter", nullable = false)
    private boolean isHeadquarter;

    @Column(name = "address", nullable = false)
    private String address;


    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private BankName bank;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public SwiftCode() {
    }

    public SwiftCode(String swiftCode, boolean isHeadquarter, String address, BankName bank, City city) {
        this.swiftCode = swiftCode;
        this.isHeadquarter = isHeadquarter;
        this.address = address;
        this.bank = bank;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BankName getBank() {
        return bank;
    }

    public void setBank(BankName bank) {
        this.bank = bank;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
