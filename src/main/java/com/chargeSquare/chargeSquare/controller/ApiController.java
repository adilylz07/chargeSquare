package com.chargeSquare.chargeSquare.controller;
import com.chargeSquare.chargeSquare.service.ExternalApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/external")
public class ApiController {

    private final ExternalApiService externalApiService;

    public ApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }


    @GetMapping("/fetch")
    public Object fetchAndPublishChargingStations() {
        return externalApiService.fetchAndPublishChargingStations();
    }

}
