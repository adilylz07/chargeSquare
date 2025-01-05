package com.chargeSquare.chargeSquare.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty("AddressLine1")
    private String addressLine1;

    @JsonProperty("Title")
    private String title;  // Title alanını burada tanımlıyoruz.

    // Getter ve Setter metodları

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
