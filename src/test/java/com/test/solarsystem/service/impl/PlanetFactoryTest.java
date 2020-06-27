package com.test.solarsystem.service.impl;

import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.model.Planet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class PlanetFactoryTest {

    @InjectMocks
    PlanetFactory factory;

    @BeforeEach
    public void beforeEach() {
        ReflectionTestUtils.setField(factory, "ferengiAngularSpeed", 1);
        ReflectionTestUtils.setField(factory, "ferengiClockwise", true);
        ReflectionTestUtils.setField(factory, "ferengiSunDistance", 100);

        ReflectionTestUtils.setField(factory, "betasoideAngularSpeed", 2);
        ReflectionTestUtils.setField(factory, "betasoideClockwise", false);
        ReflectionTestUtils.setField(factory, "betasoideSunDistance", 200);

        ReflectionTestUtils.setField(factory, "vulcanoAngularSpeed", 3);
        ReflectionTestUtils.setField(factory, "vulcanoClockwise", true);
        ReflectionTestUtils.setField(factory, "vulcanoSunDistance", 300);
    }

    @Test
    void givenFerengilanetNameWhenCreatePlanetThenReturnPlanet() {

        Planet planet = this.factory.createPlanet(PlanetName.FERENGIS);

        assertThat(true).isEqualTo(planet.isClockwise());
        assertThat(100).isEqualTo(planet.getSunDistance());
        assertThat(1).isEqualTo(planet.getAngularSpeed());
    }

    @Test
    void givenBetasoidePlanetNameWhenCreatePlanetThenReturnPlanet() {

        Planet planet = this.factory.createPlanet(PlanetName.BETASOIDES);

        assertThat(false).isEqualTo(planet.isClockwise());
        assertThat(200).isEqualTo(planet.getSunDistance());
        assertThat(2).isEqualTo(planet.getAngularSpeed());
    }

    @Test
    void givenVulcanoPlanetNameWhenCreatePlanetThenReturnPlanet() {

        Planet planet = this.factory.createPlanet(PlanetName.VULCANOS);

        assertThat(true).isEqualTo(planet.isClockwise());
        assertThat(300).isEqualTo(planet.getSunDistance());
        assertThat(3).isEqualTo(planet.getAngularSpeed());
    }
}