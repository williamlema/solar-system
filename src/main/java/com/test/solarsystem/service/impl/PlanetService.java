package com.test.solarsystem.service.impl;

import com.test.solarsystem.model.Planet;
import com.test.solarsystem.service.IPlanetService;
import org.springframework.stereotype.Service;

@Service
public class PlanetService implements IPlanetService {

    @Override
    public void doRotation(Planet planet) {

        double t = (Math.PI * planet.getPosition().getAngle()) / 180;
        planet.getPosition().setX(planet.getSunDistance() * Math.cos(t));
        planet.getPosition().setY(planet.getSunDistance() * Math.sin(t));
        int nextAnglePos = planet.getPosition().getAngle();
        if (planet.isClockwise()){
            nextAnglePos = nextAnglePos == 360 ? 0: nextAnglePos;
            nextAnglePos = nextAnglePos + planet.getAngularSpeed() ;
        } else {
            nextAnglePos = nextAnglePos == 0 ? 360: nextAnglePos;
            nextAnglePos = nextAnglePos - planet.getAngularSpeed() ;
        }
        planet.getPosition().setAngle(nextAnglePos);
    }
}
