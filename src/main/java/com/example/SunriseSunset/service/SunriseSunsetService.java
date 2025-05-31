package com.example.SunriseSunset.service;

import com.example.SunriseSunset.dto.SunriseSunsetDTO;
import com.example.SunriseSunset.model.LocationEntity;
import com.example.SunriseSunset.model.SunriseSunsetEntity;
import com.example.SunriseSunset.model.SunriseSunsetModel;
import com.example.SunriseSunset.repository.LocationRepository;
import com.example.SunriseSunset.repository.SunriseSunsetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SunriseSunsetService {

    private final String SUN_API_URL = "https://api.sunrise-sunset.org/json";
    private final RestTemplate restTemplate;
    private final SunriseSunsetRepository sunriseSunsetRepository;
    private final LocationRepository locationRepository;

    public SunriseSunsetService(RestTemplate restTemplate,
                                SunriseSunsetRepository sunriseSunsetRepository,
                                LocationRepository locationRepository) {
        this.restTemplate = restTemplate;
        this.sunriseSunsetRepository = sunriseSunsetRepository;
        this.locationRepository = locationRepository;
    }

    public SunriseSunsetDTO createSunriseSunset(SunriseSunsetDTO dto) {
        SunriseSunsetModel sunData = getSunriseSunset(dto.getLatitude(), dto.getLongitude(), dto.getDate().toString());
        SunriseSunsetEntity entity = new SunriseSunsetEntity();
        entity.date = dto.getDate();
        entity.latitude = dto.getLatitude();
        entity.longitude = dto.getLongitude();
        entity.sunrise = OffsetDateTime.parse(sunData.getResults().getSunrise());
        entity.sunset = OffsetDateTime.parse(sunData.getResults().getSunset());

        if (dto.getLocationIds() != null && !dto.getLocationIds().isEmpty()) {
            List<LocationEntity> locations = locationRepository.findAllById(dto.getLocationIds());
            entity.locations = locations;
        }

        SunriseSunsetEntity savedEntity = sunriseSunsetRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    public SunriseSunsetDTO getSunriseSunsetById(Integer id) {
        Optional<SunriseSunsetEntity> entity = sunriseSunsetRepository.findById(id);
        return entity.map(this::convertToDTO).orElse(null);
    }

    public List<SunriseSunsetDTO> getAllSunriseSunsets() {
        return sunriseSunsetRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SunriseSunsetDTO updateSunriseSunset(Integer id, SunriseSunsetDTO dto) {
        Optional<SunriseSunsetEntity> existing = sunriseSunsetRepository.findById(id);
        if (existing.isPresent()) {
            SunriseSunsetEntity entity = existing.get();
            entity.date = dto.getDate();
            entity.latitude = dto.getLatitude();
            entity.longitude = dto.getLongitude();
            SunriseSunsetModel sunData = getSunriseSunset(dto.getLatitude(), dto.getLongitude(), dto.getDate().toString());
            entity.sunrise = OffsetDateTime.parse(sunData.getResults().getSunrise());
            entity.sunset = OffsetDateTime.parse(sunData.getResults().getSunset());

            if (dto.getLocationIds() != null && !dto.getLocationIds().isEmpty()) {
                List<LocationEntity> locations = locationRepository.findAllById(dto.getLocationIds());
                entity.locations = locations;
            } else {
                entity.locations.clear();
            }

            SunriseSunsetEntity updatedEntity = sunriseSunsetRepository.save(entity);
            return convertToDTO(updatedEntity);
        }
        return null;
    }

    public void deleteSunriseSunset(Integer id) {
        sunriseSunsetRepository.deleteById(id);
    }

    private SunriseSunsetDTO convertToDTO(SunriseSunsetEntity entity) {
        List<Integer> locationIds = entity.locations.stream()
                .map(location -> location.id)
                .collect(Collectors.toList());
        return new SunriseSunsetDTO(
                entity.id, entity.date, entity.latitude, entity.longitude,
                entity.sunrise, entity.sunset, locationIds
        );
    }

    private SunriseSunsetModel getSunriseSunset(double lat, double lng, String date) {
        String url = String.format("%s?lat=%f&lng=%f&date=%s&formatted=0", SUN_API_URL, lat, lng, date);
        try {
            return restTemplate.getForObject(url, SunriseSunsetModel.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch sunrise/sunset data: " + e.getMessage());
        }
    }
}