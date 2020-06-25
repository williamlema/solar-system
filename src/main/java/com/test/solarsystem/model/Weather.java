package com.test.solarsystem.model;

import com.test.solarsystem.constant.PeriodType;

import javax.persistence.*;

@Entity
@Table
public class Weather {

    @Id
    private Integer day;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    private Double area;

    private Double perimeter;

    private Boolean sunInsidePlanetsArea;

    private Boolean solarAlignment;

    public Weather() {
    }

    public Weather(Integer day, PeriodType periodType, Double area, Double perimeter, Boolean sunInsidePlanetsArea, Boolean solarAlignment) {
        this.day = day;
        this.periodType = periodType;
        this.area = area;
        this.perimeter = perimeter;
        this.sunInsidePlanetsArea = sunInsidePlanetsArea;
        this.solarAlignment = solarAlignment;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Boolean getSunInsidePlanetsArea() {
        return sunInsidePlanetsArea;
    }

    public void setSunInsidePlanetsArea(Boolean sunInsidePlanetsArea) {
        this.sunInsidePlanetsArea = sunInsidePlanetsArea;
    }

    public Boolean getSolarAlignment() {
        return solarAlignment;
    }

    public void setSolarAlignment(Boolean solarAlignment) {
        this.solarAlignment = solarAlignment;
    }
}
