package com.test.solarsystem.service.impl;

import com.test.solarsystem.constant.PeriodType;
import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.model.Planet;
import com.test.solarsystem.model.Position;
import com.test.solarsystem.model.Weather;
import com.test.solarsystem.payload.AnalyticsResponse;
import com.test.solarsystem.repositorio.WeatherRepository;
import com.test.solarsystem.service.IPlanetFactory;
import com.test.solarsystem.service.IPlanetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class WeatherServiceTest {

    @InjectMocks
    WeatherService service;
    @Mock
    IPlanetFactory planetServiceFactory;
    @Mock
    IPlanetService planetService;
    @Mock
    WeatherRepository weatherRepository;

    @BeforeEach
    public void beforeEach() {
       ReflectionTestUtils.setField(service, "days", 1);
    }

    @Test
    void initWeatherSimulation() {
        Planet ferengi = new Planet(1,true,100, new Position(100,0,0));
        Planet betasoide = new Planet(1,true,100, new Position(50,0,0));
        Planet vulcano = new Planet(1,true,100, new Position(10,0,0));
        Weather weather = new Weather(1, PeriodType.DROUGHT, 0.0, 180.0, false, true);
        List<Weather> list = Arrays.asList(weather);

        doReturn(ferengi).when(planetServiceFactory).createPlanet(PlanetName.FERENGIS);
        doReturn(betasoide).when(planetServiceFactory).createPlanet(PlanetName.BETASOIDES);
        doReturn(vulcano).when(planetServiceFactory).createPlanet(PlanetName.VULCANOS);

        doNothing().when(planetService).doRotation(ferengi);
        doNothing().when(planetService).doRotation(betasoide);
        doNothing().when(planetService).doRotation(vulcano);

        doReturn(list).when(weatherRepository).saveAll(any());

        service.initWeatherSimulation();

        verify(planetServiceFactory, times(3)).createPlanet(any());
        verify(planetService, times(3)).doRotation(any());
        verify(weatherRepository).saveAll(any());
        verifyNoMoreInteractions(planetServiceFactory, planetService, weatherRepository);
    }

    @Test
    public void getAnalytics(){
        Weather weather = new Weather(2, PeriodType.DROUGHT, 0.0, 180.0, false, true);
        List<Weather> list = Arrays.asList(weather);
        AnalyticsResponse expected = new AnalyticsResponse(1,1, Arrays.asList(2), 1);

        doReturn(list).when(weatherRepository).findAll();
        doReturn(list).when(weatherRepository).findAllByPeriodType(any());

        AnalyticsResponse response = service.getAnalytics();
        verify(weatherRepository, times(3)).findAllByPeriodType(any());
        verify(weatherRepository).findAll();
        verifyNoMoreInteractions(weatherRepository);
    }
}