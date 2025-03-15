package com.oleksandr.remitly.swiftapi.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "timezones")
public class Timezone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "timezone_name", nullable = false)
    private String timezoneName;

    public Timezone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimezoneName() {
        return timezoneName;
    }

    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }
}
