package com.oleksandr.remitly.swiftapi.Model.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "timezones")
@NoArgsConstructor
@Data
public class Timezone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timezone_name", nullable = false, length = 50)
    private String timezoneName;

    @OneToMany(mappedBy = "timezone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Country> countries;


    public Timezone(String timezoneName) {
        this.timezoneName = timezoneName;
    }
}
