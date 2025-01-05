package com.chargeSquare.chargeSquare.controller;

import com.chargeSquare.chargeSquare.model.ChargingStation;
import com.chargeSquare.chargeSquare.service.ChargingStationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class ChargingStationController {
    private final ChargingStationService service;

    public ChargingStationController(ChargingStationService service) {
        this.service = service;
    }

    @GetMapping
    public List<ChargingStation> getAllStations() {
        return service.getAllStations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChargingStation> getStationById(@PathVariable Long id) {
        return service.getStationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllStations() {
        try {
            service.deleteAllStations();  // Servis metodunu çağır
            return ResponseEntity.noContent().build();  // 204 No Content döner
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Hata durumunda 500 döner
        }
    }
    @PostMapping
    public ChargingStation addStation(@RequestBody ChargingStation station) {
        return service.addStation(station);
    }
}
