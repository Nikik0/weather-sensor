package com.nikik0.sensorWeatherServer.controllers;

import com.nikik0.sensorWeatherServer.DTO.SensorDTO;
import com.nikik0.sensorWeatherServer.models.Sensor;
import com.nikik0.sensorWeatherServer.services.SensorService;
import com.nikik0.sensorWeatherServer.util.ErrorHandler;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping()
    public List<Sensor> getAll(){
        return sensorService.getAll();
    }

    @GetMapping("/{id}")
    public Sensor getOne(@PathVariable("id") int id){
        return sensorService.getOne(id).orElseThrow();
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ErrorHandler.getErrorMessage(bindingResult);
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }else{
            sensorService.register(convertToDTO(sensorDTO));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }
    }

    private Sensor convertToDTO(SensorDTO sensorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Sensor sensor = new Sensor();
        modelMapper.map(sensorDTO, sensor);
        return sensor;
    }
}
