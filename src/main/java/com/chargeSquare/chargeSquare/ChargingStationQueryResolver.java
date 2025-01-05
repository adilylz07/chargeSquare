package com.chargeSquare.chargeSquare;

import com.chargeSquare.chargeSquare.model.ChargingStation;
import com.chargeSquare.chargeSquare.service.ChargingStationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChargingStationQueryResolver implements GraphQLQueryResolver {
    private final ChargingStationService service;

    public ChargingStationQueryResolver(ChargingStationService service) {
        this.service = service;
    }

    public List<ChargingStation> getAllStations() {
        return service.getAllStations();
    }

    public Optional<ChargingStation> getStationById(Long id) {
        return service.getStationById(id);
    }
}

