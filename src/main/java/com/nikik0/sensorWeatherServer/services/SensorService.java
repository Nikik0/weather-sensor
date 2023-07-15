package com.nikik0.sensorWeatherServer.services;

import com.nikik0.sensorWeatherServer.models.Sensor;
import com.nikik0.sensorWeatherServer.repositories.SensorRepo;
import com.nikik0.sensorWeatherServer.util.SensorStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public List<Sensor> getAll(){
        return sensorRepo.findAll();
    }

    public Optional<Sensor> getOne(int id){
        return sensorRepo.findById(id);
    }

    @Transactional(readOnly = false)
    public void register(Sensor sensor){
        sensorRepo.save(sensor);
    }

    public List<Sensor> getOfflineSensors(){
        return sensorRepo.getSensorsBySensorStatusNotContainsIgnoreCase(SensorStatus.ON);
    }

    public void changeSensorStatus(int id, SensorStatus sensorStatus){
        Sensor sensor = sensorRepo.findById(id).orElseThrow();
        sensor.setSensorStatus(sensorStatus.toString());
        sensorRepo.save(sensor);
    }
}
