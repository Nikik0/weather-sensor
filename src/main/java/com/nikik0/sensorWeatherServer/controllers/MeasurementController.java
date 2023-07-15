package com.nikik0.sensorWeatherServer.controllers;

import com.nikik0.sensorWeatherServer.DTO.MeasurementDTO;
import com.nikik0.sensorWeatherServer.DTO.SensorDTO;
import com.nikik0.sensorWeatherServer.models.Measurement;
import com.nikik0.sensorWeatherServer.models.Sensor;
import com.nikik0.sensorWeatherServer.services.MeasurementService;
import com.nikik0.sensorWeatherServer.util.CreationException;
import com.nikik0.sensorWeatherServer.util.ErrorHandler;
import com.nikik0.sensorWeatherServer.util.ErrorMessage;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping()
    public List<Measurement> getAll(){
        return measurementService.getAll();
    }

    @GetMapping("/coldDays")
    public List<Measurement> getColdMeasurements(){
        return measurementService.getNegativeTemperature();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CreationException(ErrorHandler.getErrorMessage(bindingResult));
        }else{
            measurementService.save(convertToDTO(measurementDTO));
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
        }
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> creationExceptionHandler(CreationException creationException){
        ErrorMessage errorMessage = new ErrorMessage(
                creationException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    private Measurement convertToDTO(MeasurementDTO measurementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Measurement measurement = new Measurement();
        modelMapper.map(measurementDTO, measurement);
        return measurement;
    }
}
