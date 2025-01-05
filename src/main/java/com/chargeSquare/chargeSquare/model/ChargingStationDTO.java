package com.chargeSquare.chargeSquare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ChargingStationDTO {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("NumberOfPoints")
    private Integer capacity;

    @JsonProperty("AddressInfo")
    private Location location;  // AddressInfo, Location sınıfına bağlanmış durumda

    // Getter ve Setter metodları

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

    // API yanıtını DTO'ya dönüştürürken kullanılacak yapı
    public static ChargingStationDTO fromApiResponse(Map<String, Object> apiResponse) {
        ChargingStationDTO dto = new ChargingStationDTO();

        // Capacity'yi API yanıtından alıyoruz
        dto.setCapacity((Integer) apiResponse.get("NumberOfPoints"));

        // Location bilgisi AddressInfo'dan alınacak
        Map<String, Object> addressInfo = (Map<String, Object>) apiResponse.get("AddressInfo");
        if (addressInfo != null) {
            Location location = new Location();

            // AddressLine1 ve Title bilgilerini AddressInfo'dan alıyoruz
            location.setAddressLine1((String) addressInfo.get("AddressLine1"));
            location.setTitle((String) addressInfo.get("Title"));  // Buradaki Title'ı doğru şekilde alıyoruz

            dto.setLocation(location);
        }

        // ID'yi alıyoruz
        dto.setId((Long) apiResponse.get("ID"));

        return dto;
    }
}
