package com.nikik0.sensorWeatherServer.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty
    private int id;

    @NotEmpty
    private String sensorStatus;

    @NotEmpty
    @Size(min = 5, max = 20, message = "Invalid location length")
    private String sensorLocation;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(String sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

}
