package com.nikik0.sensorWeatherServer.ClientServer;

import org.springframework.web.reactive.function.client.WebClient;

public class ClientServer {
    public void postRandomMeasurements(){
        WebClient client = WebClient.create("http://localhost.com");
        
    }
}
