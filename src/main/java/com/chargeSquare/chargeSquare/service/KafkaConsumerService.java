package com.chargeSquare.chargeSquare.service;

import com.chargeSquare.chargeSquare.ChargingStationRepository;
import com.chargeSquare.chargeSquare.model.ChargingStation;
import com.chargeSquare.chargeSquare.model.ChargingStationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final ChargingStationRepository repository;

    public KafkaConsumerService(ChargingStationRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "charging-stations-topic", groupId = "charging-stations-group")
    public void consumeMessage(String message) {
        try {
            ChargingStationDTO stationDTO = new ObjectMapper().readValue(message, ChargingStationDTO.class);

            ChargingStation station = new ChargingStation();


            station.setId(stationDTO.getId());
            station.setName(stationDTO.getLocation().getTitle());
            station.setLocation(stationDTO.getLocation().getAddressLine1());
            station.setCapacity(stationDTO.getCapacity());
            System.out.println(station.toString());
            repository.save(station);
        } catch (JsonProcessingException e) {
            System.out.println("Error processing message: " + message);
            e.printStackTrace();
        }
    }

}
