package com.chargeSquare.chargeSquare.service;


import com.chargeSquare.chargeSquare.ChargingStationRepository;
import com.chargeSquare.chargeSquare.model.ChargingStation;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargingStationService {

    private final ChargingStationRepository repository;

    public ChargingStationService(ChargingStationRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public void deleteAllStations() {
        repository.deleteAll();  // Veritabanındaki tüm istasyonları siler
    }
    @Cacheable("stations")
    @CacheEvict(value = "stations", allEntries = true)
    public List<ChargingStation> getAllStations() {
        return repository.findAll();
    }

    @Cacheable(value = "station", key = "#id")
    public Optional<ChargingStation> getStationById(Long id) {
        return repository.findById(id);
    }

    // Yeni bir istasyon ekleme metodu
    @Cacheable(value = "station", key = "#id")
    public ChargingStation addStation(ChargingStation station) {
        return repository.save(station); // Veritabanına kaydeder
    }
}
