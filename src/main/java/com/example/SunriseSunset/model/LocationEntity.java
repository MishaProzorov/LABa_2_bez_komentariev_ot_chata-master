package com.example.SunriseSunset.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "country")
    public String country;

    @ManyToMany
    @JoinTable(
            name = "sunrise_sunset_locations",
            joinColumns = @JoinColumn(name = "location_id"),
            inverseJoinColumns = @JoinColumn(name = "sunrise_sunset_id")
    )
    public List<SunriseSunsetEntity> sunriseSunsets = new ArrayList<>();
}