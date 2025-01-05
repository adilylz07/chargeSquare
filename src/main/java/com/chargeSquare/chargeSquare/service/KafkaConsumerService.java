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
            // Mesajı JSON'dan ChargingStationDTO'ya dönüştür
            ChargingStationDTO stationDTO = new ObjectMapper().readValue(message, ChargingStationDTO.class);

            // ChargingStation nesnesine dönüşüm yap
            ChargingStation station = new ChargingStation();

            // Eşlemeleri burada yapıyoruz
             // ID
            station.setId(stationDTO.getId());
            station.setName(stationDTO.getLocation().getTitle());  // Title => name
            station.setLocation(stationDTO.getLocation().getAddressLine1());  // AddressLine1 => location
            station.setCapacity(stationDTO.getCapacity());  // NumberOfPoints => capacity
            System.out.println(station.toString());
            // ChargingStation nesnesini veritabanına kaydet
            repository.save(station);  // ChargingStation nesnesini kaydet
        } catch (JsonProcessingException e) {
            System.out.println("Error processing message: " + message);
            e.printStackTrace();
        }
    }

}
