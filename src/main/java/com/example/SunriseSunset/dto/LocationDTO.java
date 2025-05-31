package com.example.SunriseSunset.dto;

import java.util.List;

public class LocationDTO {

    private Integer id;
    private String name;
    private String country;
    private List<Integer> sunriseSunsetIds;

    public LocationDTO() {}

    public LocationDTO(Integer id, String name, String country, List<Integer> sunriseSunsetIds) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.sunriseSunsetIds = sunriseSunsetIds;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public List<Integer> getSunriseSunsetIds() { return sunriseSunsetIds; }
    public void setSunriseSunsetIds(List<Integer> sunriseSunsetIds) { this.sunriseSunsetIds = sunriseSunsetIds; }
}