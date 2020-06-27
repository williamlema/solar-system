package com.test.solarsystem.controller;

import com.test.solarsystem.constant.PeriodType;
import com.test.solarsystem.payload.AnalyticsResponse;
import com.test.solarsystem.payload.WeatherResponse;
import com.test.solarsystem.service.IWeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration
class WeatherControllerTest {

    @InjectMocks
    WeatherController controller;

    @Mock
    IWeatherService service;


    @Test
    void giveRequestWhenInitSimulationThenCallServiceToInitWeatherSimulation() {

        doNothing().when(service).initWeatherSimulation();

        ResponseEntity<String> response = controller.initSimulation();

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat("Init weather simulations successfully").isEqualTo(response.getBody());

        verify(service).initWeatherSimulation();
        verifyNoMoreInteractions(service);
    }

    @Test
    void givenRequestWhenGetAnalyticsThenCallServiceToGetAnalytics() {

        AnalyticsResponse analyticsResponse =
                new AnalyticsResponse(100, 100, Arrays.asList(1,2,3), 100);

        doReturn(analyticsResponse).when(service).getAnalytics();

        ResponseEntity<AnalyticsResponse> response = controller.getAnalytics();

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(analyticsResponse).isEqualTo(response.getBody());

        verify(service).getAnalytics();
        verifyNoMoreInteractions(service);
    }

    @Test
    void givenDayWhenGetWeatherByDayThenReturnWeatherResponse() {

        Integer day = 1;
        WeatherResponse weatherResponse = new WeatherResponse(day, PeriodType.RAINY);

        doReturn(weatherResponse).when(service).getDayWeather(day);

        ResponseEntity<WeatherResponse> response = controller.getWeatherByDay(day);

        assertThat(HttpStatus.OK).isEqualTo(response.getStatusCode());
        assertThat(weatherResponse).isEqualTo(response.getBody());

        verify(service).getDayWeather(day);
        verifyNoMoreInteractions(service);
    }
}