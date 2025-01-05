package com.chargeSquare.chargeSquare.service;

import com.chargeSquare.chargeSquare.model.ChargingStationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    private final KafkaProducerService kafkaProducerService;

    public ExternalApiService(RestTemplate restTemplate, KafkaProducerService kafkaProducerService) {
        this.restTemplate = restTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<ChargingStationDTO> fetchAndPublishChargingStations() {
        String apiUrl = "https://api.openchargemap.org/v3/poi/?key=YOUR_API_KEY&countrycode=TR";
        ChargingStationDTO[] response = restTemplate.getForObject(apiUrl, ChargingStationDTO[].class);

        if (response != null) {
            Arrays.stream(response).forEach(station ->
                    kafkaProducerService.sendMessage("charging-stations-topic", station)
            );
        }

        assert response != null;
        return Arrays.asList(response);
    }

}

