package com.chargeSquare.chargeSquare;

import com.chargeSquare.chargeSquare.model.ChargingStation;
import com.chargeSquare.chargeSquare.service.ChargingStationService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class ChargingStationMutationResolver implements GraphQLMutationResolver {
    private final ChargingStationService service;

    public ChargingStationMutationResolver(ChargingStationService service) {
        this.service = service;
    }

    public <ChargingStationInput> ChargingStation addStation(ChargingStationInput input) {
        return service.addStation((ChargingStation) input);
    }
}
