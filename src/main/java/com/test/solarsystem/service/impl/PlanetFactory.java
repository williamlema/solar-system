package com.test.solarsystem.service.impl;

import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.model.Planet;
import com.test.solarsystem.model.Position;
import com.test.solarsystem.service.IPlanetFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlanetFactory implements IPlanetFactory {

    @Value("${weather.planet.Ferengi.angular-speed}")
    private int ferengiAngularSpeed;
    @Value("${weather.planet.Ferengi.clockwise}")
    private boolean ferengiClockwise;
    @Value("${weather.planet.Ferengi.sun-distance}")
    private int ferengiSunDistance;

    @Value("${weather.planet.Betasoide.angular-speed}")
    private int betasoideAngularSpeed;
    @Value("${weather.planet.Betasoide.clockwise}")
    private boolean betasoideClockwise;
    @Value("${weather.planet.Betasoide.sun-distance}")
    private int betasoideSunDistance;

    @Value("${weather.planet.Vulcano.angular-speed}")
    private int vulcanoAngularSpeed;
    @Value("${weather.planet.Vulcano.clockwise}")
    private boolean vulcanoClockwise;
    @Value("${weather.planet.Vulcano.sun-distance}")
    private int vulcanoSunDistance;

    @Override
    public Planet createPlanet(PlanetName name) {
        Planet planet = null;
        switch (name) {
            case FERENGIS:
                planet = new Planet(ferengiAngularSpeed, ferengiClockwise, ferengiSunDistance, new Position(0, 0, ferengiClockwise ? 0 : 360));
                break;
            case VULCANOS:
                planet = new Planet(vulcanoAngularSpeed, vulcanoClockwise, vulcanoSunDistance, new Position(0, 0, vulcanoClockwise ? 0 : 360));
                break;
            case BETASOIDES:
                planet = new Planet(betasoideAngularSpeed, betasoideClockwise, betasoideSunDistance, new Position(0, 0, betasoideClockwise ? 0 : 360));
                break;
        }
        return planet;
    }
}
