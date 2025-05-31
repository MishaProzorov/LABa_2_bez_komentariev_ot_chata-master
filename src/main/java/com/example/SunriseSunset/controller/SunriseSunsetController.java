package com.example.SunriseSunset.controller;

import com.example.SunriseSunset.dto.SunriseSunsetDTO;
import com.example.SunriseSunset.service.SunriseSunsetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sun/times")
public class SunriseSunsetController {

    private final SunriseSunsetService sunService;

    public SunriseSunsetController(SunriseSunsetService sunService) {
        this.sunService = sunService;
    }

    @PostMapping
    public ResponseEntity<SunriseSunsetDTO> createSunriseSunset(@RequestBody SunriseSunsetDTO dto) {
        SunriseSunsetDTO savedDto = sunService.createSunriseSunset(dto);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SunriseSunsetDTO> getSunriseSunsetById(@PathVariable Integer id) {
        SunriseSunsetDTO dto = sunService.getSunriseSunsetById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<SunriseSunsetDTO>> getAllSunriseSunsets() {
        return ResponseEntity.ok(sunService.getAllSunriseSunsets());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SunriseSunsetDTO> updateSunriseSunset(@PathVariable Integer id, @RequestBody SunriseSunsetDTO dto) {
        SunriseSunsetDTO updatedDto = sunService.updateSunriseSunset(id, dto);
        return updatedDto != null ? ResponseEntity.ok(updatedDto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSunriseSunset(@PathVariable Integer id) {
        sunService.deleteSunriseSunset(id);
        return ResponseEntity.noContent().build();
    }
}