package com.example.SunriseSunset.service;

import com.example.SunriseSunset.dto.LocationDTO;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.model.SunriseSunsetEntity;
import com.example.SunriseSunset.repository.LocationRepository;
import com.example.SunriseSunset.repository.SunriseSunsetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final SunriseSunsetRepository sunriseSunsetRepository;

    public LocationService(LocationRepository locationRepository, SunriseSunsetRepository sunriseSunsetRepository) {
        this.locationRepository = locationRepository;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
    }

    public LocationDTO createLocation(LocationDTO dto) {
        LocationEntity entity = new LocationEntity();
        entity.name = dto.getName();
        entity.country = dto.getCountry();
        if (dto.getSunriseSunsetIds() != null && !dto.getSunriseSunsetIds().isEmpty()) {
            List<SunriseSunsetEntity> sunriseSunsets = sunriseSunsetRepository.findAllById(dto.getSunriseSunsetIds());
            entity.sunriseSunsets = sunriseSunsets;
        }
        LocationEntity savedEntity = locationRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    public LocationDTO getLocationById(Integer id) {
        Optional<LocationEntity> entity = locationRepository.findById(id);
        return entity.map(this::convertToDTO).orElse(null);
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public LocationDTO updateLocation(Integer id, LocationDTO dto) {
        Optional<LocationEntity> existing = locationRepository.findById(id);
        if (existing.isPresent()) {
            LocationEntity entity = existing.get();
            entity.name = dto.getName();
            entity.country = dto.getCountry();

            if (dto.getSunriseSunsetIds() != null && !dto.getSunriseSunsetIds().isEmpty()) {
                List<SunriseSunsetEntity> sunriseSunsets = sunriseSunsetRepository.findAllById(dto.getSunriseSunsetIds());
                entity.sunriseSunsets = sunriseSunsets;
            } else {
                entity.sunriseSunsets.clear();
            }

            LocationEntity updatedEntity = locationRepository.save(entity);
            return convertToDTO(updatedEntity);
        }
        return null;
    }

    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }

    private LocationDTO convertToDTO(LocationEntity entity) {
        List<Integer> sunriseSunsetIds = entity.sunriseSunsets.stream()
                .map(sunriseSunset -> sunriseSunset.id)
                .collect(Collectors.toList());
        return new LocationDTO(entity.id, entity.name, entity.country, sunriseSunsetIds);
    }
}