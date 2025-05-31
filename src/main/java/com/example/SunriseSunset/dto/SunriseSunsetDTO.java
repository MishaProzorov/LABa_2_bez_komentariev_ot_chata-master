package com.example.SunriseSunset.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public class SunriseSunsetDTO {

    private Integer id;
    private LocalDate date;
    private Double latitude;
    private Double longitude;
    private OffsetDateTime sunrise;
    private OffsetDateTime sunset;
    private List<Integer> locationIds;

    public SunriseSunsetDTO() {}

    public SunriseSunsetDTO(LocalDate date, Double latitude, Double longitude) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public SunriseSunsetDTO(Integer id, LocalDate date, Double latitude, Double longitude,
                            OffsetDateTime sunrise, OffsetDateTime sunset, List<Integer> locationIds) {
        this.id = id;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.locationIds = locationIds;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public OffsetDateTime getSunrise() { return sunrise; }
    public void setSunrise(OffsetDateTime sunrise) { this.sunrise = sunrise; }
    public OffsetDateTime getSunset() { return sunset; }
    public void setSunset(OffsetDateTime sunset) { this.sunset = sunset; }
    public List<Integer> getLocationIds() { return locationIds; }
    public void setLocationIds(List<Integer> locationIds) { this.locationIds = locationIds; }
}