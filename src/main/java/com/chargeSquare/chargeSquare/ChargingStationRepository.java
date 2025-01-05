package com.chargeSquare.chargeSquare;

import com.chargeSquare.chargeSquare.model.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingStationRepository extends JpaRepository<ChargingStation, Long> {
}