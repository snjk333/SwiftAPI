package com.oleksandr.remitly.swiftapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bank_names")
public class BankName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    public BankName() {
    }

    public BankName(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
