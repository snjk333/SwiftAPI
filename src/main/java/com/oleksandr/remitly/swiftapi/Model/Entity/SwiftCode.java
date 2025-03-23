package com.oleksandr.remitly.swiftapi.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "swift_codes")
@Data
@NoArgsConstructor
public class SwiftCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "swift_code", nullable = false, length = 11, unique = true)
    private String swiftCode;

    @Column(name = "is_headquarter", nullable = false)
    private boolean isHeadquarter;

    @Column(name = "address", length = 200)
    private String address;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private BankName bank;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public SwiftCode(String swiftCode, boolean isHeadquarter, String address, BankName bank, City city) {
        this.swiftCode = swiftCode;
        this.isHeadquarter = isHeadquarter;
        this.address = address;
        this.bank = bank;
        this.city = city;
    }

}
