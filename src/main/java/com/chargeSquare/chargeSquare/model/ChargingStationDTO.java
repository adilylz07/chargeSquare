package com.chargeSquare.chargeSquare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ChargingStationDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NumberOfPoints")
    private Integer capacity;

    @JsonProperty("AddressInfo")
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public static ChargingStationDTO fromApiResponse(Map<String, Object> apiResponse) {
        ChargingStationDTO dto = new ChargingStationDTO();

        dto.setCapacity((Integer) apiResponse.get("NumberOfPoints"));

        Map<String, Object> addressInfo = (Map<String, Object>) apiResponse.get("AddressInfo");
        if (addressInfo != null) {
            Location location = new Location();

            location.setAddressLine1((String) addressInfo.get("AddressLine1"));
            location.setTitle((String) addressInfo.get("Title"));
            dto.setLocation(location);
        }

        dto.setId((Long) apiResponse.get("ID"));

        return dto;
    }
}
