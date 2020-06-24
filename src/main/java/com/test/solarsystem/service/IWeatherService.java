package com.test.solarsystem.service;

import com.test.solarsystem.model.Position;

public interface IWeatherService {

    void initWeatherSimulation();

    double calculateAreaBetweenPlanets(Position ferengiPos, Position betasoidePos, Position vulcanoPos);

    boolean verifySolarAlignment(Position planetA, Position planetB, Position sun);
}
