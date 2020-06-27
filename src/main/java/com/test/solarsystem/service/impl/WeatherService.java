package com.test.solarsystem.service.impl;

import com.test.solarsystem.constant.PeriodType;
import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.exception.BadRequestException;
import com.test.solarsystem.model.Planet;
import com.test.solarsystem.model.Position;
import com.test.solarsystem.model.Weather;
import com.test.solarsystem.payload.AnalyticsResponse;
import com.test.solarsystem.payload.WeatherResponse;
import com.test.solarsystem.repositorio.WeatherRepository;
import com.test.solarsystem.service.IPlanetFactory;
import com.test.solarsystem.service.IPlanetService;
import com.test.solarsystem.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService implements IWeatherService {

    @Value("${weather.simulation.days}")
    private int days;
    private final IPlanetFactory planetServiceFactory;
    private final IPlanetService planetService;
    private final WeatherRepository weatherRepository;
    private final Position sunPosition;

    @Autowired
    public WeatherService(IPlanetFactory planetServiceFactory, IPlanetService planetService, WeatherRepository weatherRepository) {
        this.planetServiceFactory = planetServiceFactory;
        this.planetService = planetService;
        this.weatherRepository = weatherRepository;
        this.sunPosition = new Position(0, 0, 0);
    }

    @Override
    public void initWeatherSimulation() {

        Planet ferengi = this.planetServiceFactory.createPlanet(PlanetName.FERENGIS);
        Planet betasoide = this.planetServiceFactory.createPlanet(PlanetName.BETASOIDES);
        Planet vulcano = this.planetServiceFactory.createPlanet(PlanetName.VULCANOS);
        List<Weather> list = new ArrayList<>();

        for (int i = 1; i <= days; i++) {
            this.planetService.doRotation(ferengi);
            this.planetService.doRotation(betasoide);
            this.planetService.doRotation(vulcano);

            PeriodType periodType = PeriodType.INDETERMINATE;
            double area = calculateAreaBetweenPlanets(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition());
            double perimeter = calculatePlanetsPerimeter(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition());
            boolean sa = verifySolarAlignment(ferengi.getPosition(), betasoide.getPosition(), sunPosition);
            boolean sia = verifySunInArea(ferengi.getPosition(), betasoide.getPosition(), vulcano.getPosition(), sunPosition);

            if (area == 0) {
                if (sa) {
                    periodType = PeriodType.DROUGHT;
                } else {
                    periodType = PeriodType.OPTIMAL;
                }
            } else {
                if (sia) {
                    periodType = PeriodType.RAINY;
                }
            }
            list.add(new Weather(i, periodType,area, perimeter, sia, sa));
            System.out.println(String.format("D:[%s] = F:[%s ,%s] B:[%s ,%s] V:[%s ,%s] == A[%s] ==P[%s] === SA[%s] === SiA[%s]", i,
                    ferengi.getPosition().getX(), ferengi.getPosition().getY(),
                    betasoide.getPosition().getX(), betasoide.getPosition().getY(),
                    vulcano.getPosition().getX(), vulcano.getPosition().getY(),
                    area,
                    perimeter,
                    sa,
                    sia));
        }
        this.weatherRepository.saveAll(list);
    }

    /**
     * Se realiza el cálculo del tamaño de cada uno de los lados del triángulo y realizando el cálculo con
     * la Fórmula de Herón, obteniendo el valor del semiperímetro se calcula el area del triangulo generado
     * por los tres planetas.
     *
     * @param ferengiPos
     * @param betasoidePos
     * @param vulcanoPos
     * @return
     */
    @Override
    public double calculateAreaBetweenPlanets(Position ferengiPos, Position betasoidePos, Position vulcanoPos) {
        double a = lengthSide(betasoidePos, vulcanoPos);
        double b = lengthSide(ferengiPos, vulcanoPos);
        double c = lengthSide(ferengiPos, betasoidePos);
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /**
     *
     * @param ferengiPos
     * @param betasoidePos
     * @param vulcanoPos
     * @return
     */
    @Override
    public double calculatePlanetsPerimeter(Position ferengiPos, Position betasoidePos, Position vulcanoPos) {
        double a = lengthSide(betasoidePos, vulcanoPos);
        double b = lengthSide(ferengiPos, vulcanoPos);
        double c = lengthSide(ferengiPos, betasoidePos);
        return a + b + c;
    }

    /**
     * Se realiza el cálculo de la pendiente de la recta generada por dos de los planetas y se igual a la pendiente
     * generada por uno de los planetas con el sol, si las dos pendientes son igual se asume que están alineados con
     * el sol.
     *
     * @param planetA
     * @param planetB
     * @param sun
     * @return
     */
    @Override
    public boolean verifySolarAlignment(Position planetA, Position planetB, Position sun) {
        return (planetB.getY() - planetA.getY()) / (planetB.getX() - planetA.getX()) ==
                (sun.getY() - planetB.getY()) / (sun.getX() - planetB.getX());
    }

    /**
     * Se calcula la orientación del triángulo generado por los tres planetas, posteriormente se valida si el sol es un
     * punto interior de ese triángulo realizando el cálculo de la orientación de tres triángulos donde, en cada uno de
     * ellos se reemplaza un planeta por la posición del sol. Si todas las orientaciones coinciden se asume que el punto
     * del sol está dentro del triángulo generado por los tres planetas.
     * <p>
     * fbv (triángulo inicial)
     * fbs (triángulo donde sol reemplaza VulcanoPos)
     * bvs (triángulo donde sol reemplaza Ferengi)
     * vfs (triángulo donde sol reemplaza Betasoide)
     *
     * @param ferengiPos
     * @param betasoidePos
     * @param vulcanoPos
     * @param sun
     * @return
     */
    @Override
    public boolean verifySunInArea(Position ferengiPos, Position betasoidePos, Position vulcanoPos, Position sun) {
        //(A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)
        double fbv = (ferengiPos.getX() - vulcanoPos.getX()) *
                (betasoidePos.getY() - vulcanoPos.getY()) -
                (ferengiPos.getY() - vulcanoPos.getY()) *
                        (betasoidePos.getX() - vulcanoPos.getX());

        double fbs = (ferengiPos.getX() - sun.getX()) *
                (betasoidePos.getY() - sun.getY()) -
                (ferengiPos.getY() - sun.getY()) *
                        (betasoidePos.getX() - sun.getX());

        double bvs = (betasoidePos.getX() - sun.getX()) *
                (vulcanoPos.getY() - sun.getY()) -
                (betasoidePos.getY() - sun.getY()) *
                        (vulcanoPos.getX() - sun.getX());

        double vfs = (vulcanoPos.getX() - sun.getX()) *
                (ferengiPos.getY() - sun.getY()) -
                (vulcanoPos.getY() - sun.getY()) *
                        (ferengiPos.getX() - sun.getX());

        if (fbv >= 0 && fbs >= 0 && bvs >= 0 && vfs >= 0) {
            return true;
        }

        if (fbv < 0 && fbs < 0 && bvs < 0 && vfs < 0) {
            return true;
        }

        return false;
    }

    /**
     * Valida si el día solicitado se encuentra en el sistema, de ser así entrega la información del día y el
     * tipo de clima, en el caso en que no esté en el sistema se arrojará un mensaje de error.
     *
     * @param day
     * @return
     */
    @Override
    public WeatherResponse getDayWeather(Integer day) {
        Weather weather = this.weatherRepository.findById(day).orElseThrow(()-> new BadRequestException("Day not found"));
        return new WeatherResponse(weather.getDay(), weather.getPeriodType());
    }

    /**
     * Realiza los cálculos para obtener los periodos de sequía, lluvia con el/los días máximos de lluvia y los periodos
     * óptimos de clima.
     *
     * @return
     */
    @Override
    public AnalyticsResponse getAnalytics() {
        if (this.weatherRepository.findAll().size()==0) {
            throw new BadRequestException("Data not found, run init weather simulations");
        }
        List<Weather> rainyWeather = this.weatherRepository.findAllByPeriodType(PeriodType.RAINY);
        rainyWeather.sort(Comparator.comparing(Weather::getArea));
        double biggerArea = rainyWeather.get(rainyWeather.size()-1).getArea();
        System.out.println(String.format("B:[%s]",biggerArea));
        List<Weather> droughtWeather = this.weatherRepository.findAllByPeriodType(PeriodType.DROUGHT);
        List<Weather> optimalWeather = this.weatherRepository.findAllByPeriodType(PeriodType.OPTIMAL);
        return new AnalyticsResponse(
                droughtWeather.size(),
                rainyWeather.size(),
                rainyWeather.stream()
                        .filter(weather -> biggerArea == weather.getArea())
                        .map(weather -> weather.getDay())
                        .collect(Collectors.toList()),
                optimalWeather.size());
    }

    private double lengthSide(Position p1, Position p2) {
        double xDiff = p1.getX() - p2.getX();
        double yDiff = p1.getY() - p2.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

}
