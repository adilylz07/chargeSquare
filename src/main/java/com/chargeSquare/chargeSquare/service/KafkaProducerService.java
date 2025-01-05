package com.chargeSquare.chargeSquare.service;

import com.chargeSquare.chargeSquare.model.ChargingStationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, ChargingStationDTO station) {
        try {
            // Jackson ObjectMapper kullanarak nesneyi JSON string'ine dönüştür
            String message = new ObjectMapper().writeValueAsString(station);
            System.out.println("Sending message: " + message); // Mesajı kontrol etmek için logla
            kafkaTemplate.send(topic, message);  // JSON mesajını Kafka'ya gönder
        } catch (JsonProcessingException e) {
            // Hata durumunda loglama yap
            System.err.println("Error processing JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
