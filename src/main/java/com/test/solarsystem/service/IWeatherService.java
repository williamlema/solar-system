package com.test.solarsystem.service;

import com.test.solarsystem.model.Position;
import com.test.solarsystem.payload.AnalyticsResponse;
import com.test.solarsystem.payload.WeatherResponse;

public interface IWeatherService {

    void initWeatherSimulation();

    double calculateAreaBetweenPlanets(Position ferengiPos, Position betasoidePos, Position vulcanoPos);

    double calculatePlanetsPerimeter(Position ferengiPos, Position betasoidePos, Position vulcanoPos);

    boolean verifySolarAlignment(Position planetA, Position planetB, Position sun);

    boolean verifySunInArea(Position ferengiPos, Position betasoidePos, Position vulcanoPos, Position sun);

    WeatherResponse getDayWeather(Integer day);

    AnalyticsResponse getAnalytics();
}
