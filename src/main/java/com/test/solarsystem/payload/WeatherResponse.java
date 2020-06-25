package com.test.solarsystem.payload;

import com.test.solarsystem.constant.PeriodType;

public class WeatherResponse {

    private Integer day;

    private PeriodType periodType;

    public WeatherResponse(Integer day, PeriodType periodType) {
        this.day = day;
        this.periodType = periodType;
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
}
