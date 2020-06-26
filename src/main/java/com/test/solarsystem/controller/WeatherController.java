package com.test.solarsystem.controller;

import com.test.solarsystem.payload.AnalyticsResponse;
import com.test.solarsystem.payload.WeatherResponse;
import com.test.solarsystem.service.IWeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather")
public class WeatherController {

    private final IWeatherService service;

    public WeatherController(IWeatherService service) {
        this.service = service;
    }

    @PostMapping("init")
    public ResponseEntity<String> initSimulation (){
        this.service.initWeatherSimulation();
        return ResponseEntity.ok("Init weather simulations successfully");
    }

    @GetMapping("analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics (){
        return ResponseEntity.ok(this.service.getAnalytics());
    }

    @GetMapping
    public ResponseEntity<WeatherResponse> getWeatherByDay (@RequestParam("day") Integer day){
        return ResponseEntity.ok(this.service.getDayWeather(day));
    }

}
