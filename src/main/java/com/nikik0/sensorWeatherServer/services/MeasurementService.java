package com.nikik0.sensorWeatherServer.services;

import com.nikik0.sensorWeatherServer.models.Measurement;
import com.nikik0.sensorWeatherServer.repositories.MeasurementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    private final MeasurementRepo measurementRepo;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo) {
        this.measurementRepo = measurementRepo;
    }

    public List<Measurement> getAll(){
        return measurementRepo.findAll();
    }

    public Optional<Measurement> getOne(int id){
        return measurementRepo.findById(id);
    }

    @Transactional(readOnly = false)
    public void save(Measurement measurement){
        measurementRepo.save(measurement);
    }

    public List<Measurement> getNegativeTemperature(){
        return measurementRepo.getMeasurementsByCurrentTemperatureIsLessThan(0.0f);
    }

}
