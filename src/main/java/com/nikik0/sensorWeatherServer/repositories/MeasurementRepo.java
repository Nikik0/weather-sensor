package com.nikik0.sensorWeatherServer.repositories;

import com.nikik0.sensorWeatherServer.models.Measurement;
import com.nikik0.sensorWeatherServer.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepo extends JpaRepository<Measurement, Integer> {
    public List<Measurement> getMeasurementsByCurrentTemperatureIsLessThan(float temperature);

}
