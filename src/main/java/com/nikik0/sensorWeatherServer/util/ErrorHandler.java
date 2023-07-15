package com.nikik0.sensorWeatherServer.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ErrorHandler {
    public static String getErrorMessage(BindingResult bindingResult){
        StringBuilder error = new StringBuilder();
        for (FieldError fieldError: bindingResult.getFieldErrors()) {
            error.append(fieldError.getField())
                    .append(" - ")
                    .append(fieldError.getDefaultMessage())
                    .append(";");
        }
        //System.out.println(error);
        return error.toString();
    }
}
