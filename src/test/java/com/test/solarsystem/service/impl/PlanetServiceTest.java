package com.test.solarsystem.service.impl;

import com.test.solarsystem.model.Planet;
import com.test.solarsystem.model.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class PlanetServiceTest {

    @InjectMocks
    PlanetService service;

    @Test
    void givenIsClockwisePlanetWhenDoRotationUpdatePlanetStatus() {
        Planet planet = new Planet(1,true,100, new Position(0,0,0));

        this.service.doRotation(planet);

        assertThat(100*(Math.cos(Math.PI * 0/ 180) )).isEqualTo(planet.getPosition().getX());
        assertThat(100*(Math.sin(Math.PI * 0/ 180) )).isEqualTo(planet.getPosition().getY());
        assertThat(1).isEqualTo(planet.getPosition().getAngle());
    }

    @Test
    void givenIsNotClockwisePlanetWhenDoRotationUpdatePlanetStatus() {
        Planet planet = new Planet(1,false,100, new Position(0,0,0));

        this.service.doRotation(planet);

        assertThat(100*(Math.cos(Math.PI * 0/ 180) )).isEqualTo(planet.getPosition().getX());
        assertThat(100*(Math.sin(Math.PI * 0/ 180) )).isEqualTo(planet.getPosition().getY());
        assertThat(359).isEqualTo(planet.getPosition().getAngle());
    }
}