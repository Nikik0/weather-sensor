package com.nikik0.sensorWeatherServer.repositories;

import com.nikik0.sensorWeatherServer.models.Sensor;
import com.nikik0.sensorWeatherServer.util.SensorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Integer> {
    public List<Sensor> getSensorsBySensorStatusNotContainsIgnoreCase(SensorStatus onStatus);
}
