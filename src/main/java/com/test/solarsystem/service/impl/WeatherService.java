package com.test.solarsystem.service.impl;

import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.model.Planet;
import com.test.solarsystem.model.Position;
import com.test.solarsystem.service.IPlanetFactory;
import com.test.solarsystem.service.IPlanetService;
import com.test.solarsystem.service.IWeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherService implements IWeatherService {

    private final IPlanetFactory planetServiceFactory;
    private final IPlanetService planetService;

    public WeatherService(IPlanetFactory planetServiceFactory, IPlanetService planetService) {
        this.planetServiceFactory = planetServiceFactory;
        this.planetService = planetService;
    }


    @Override
    public void initWeatherSimulation() {

        Planet ferengi = this.planetServiceFactory.createPlanet(PlanetName.FERENGIS);
        Planet betasoide = this.planetServiceFactory.createPlanet(PlanetName.BETASOIDES);
        Planet vulcano = this.planetServiceFactory.createPlanet(PlanetName.VULCANOS);

        for (double i = 1; i <= 660; i++) {
            this.planetService.doRotation(ferengi);
            this.planetService.doRotation(betasoide);
            this.planetService.doRotation(vulcano);

            System.out.println(String.format("Day: %s = Ferengi: [%s ,%s] ,Betasoide: [%s ,%s], Vulcano: [%s ,%s] == Area [%s] === SolarAlignment [%s]", i,
                    ferengi.getPosition().getX(), ferengi.getPosition().getY(),
                    betasoide.getPosition().getX(), betasoide.getPosition().getY(),
                    vulcano.getPosition().getX(), vulcano.getPosition().getY(),
                    calculateAreaBetweenPlanets(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition()),
                    verifySolarAlignment(ferengi.getPosition(), betasoide.getPosition(), new Position(0,0,0))));
        }
    }

    @Override
    public double calculateAreaBetweenPlanets(Position ferengiPos, Position betasoidePos, Position vulcanoPos) {
        double a = lengthSide(betasoidePos, vulcanoPos);
        double b = lengthSide(ferengiPos, vulcanoPos);
        double c = lengthSide(ferengiPos, betasoidePos);
        double s = (a + b + c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }

    @Override
    public boolean verifySolarAlignment(Position planetA, Position planetB, Position sun) {
        return (planetB.getY() - planetA.getY()) / (planetB.getX() - planetA.getX()) == (sun.getY() - planetB.getY()) / (sun.getX() - planetB.getX());
    }

    double lengthSide(Position p1, Position p2) {
        double xDiff = p1.getX() - p2.getX();
        double yDiff = p1.getY() - p2.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

}
