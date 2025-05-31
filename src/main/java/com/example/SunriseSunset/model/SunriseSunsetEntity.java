package com.example.SunriseSunset.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sunrise_and_sunset")
public class SunriseSunsetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer id;

    @Column(name = "date", nullable = false)
    public LocalDate date;

    @Column(name = "latitude", nullable = false)
    public Double latitude;

    @Column(name = "longitude", nullable = false)
    public Double longitude;

    @Column(name = "sunrise")
    public OffsetDateTime sunrise;

    @Column(name = "sunset")
    public OffsetDateTime sunset;

    @ManyToMany
    @JoinTable(
            name = "sunrise_sunset_locations",
            joinColumns = @JoinColumn(name = "sunrise_sunset_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    public List<LocationEntity> locations = new ArrayList<>();
}