package com.test.solarsystem.payload;

import java.util.List;

public class AnalyticsResponse {

    private Integer droughtDaysQuantity;
    private Integer rainyDaysQuantity;
    private List<Integer> rainyDays;
    private Integer optimalDaysQuantity;

    public AnalyticsResponse(Integer droughtDaysQuantity, Integer rainyDaysQuantity, List<Integer> rainyDays, Integer optimalDaysQuantity) {
        this.droughtDaysQuantity = droughtDaysQuantity;
        this.rainyDaysQuantity = rainyDaysQuantity;
        this.rainyDays = rainyDays;
        this.optimalDaysQuantity = optimalDaysQuantity;
    }

    public Integer getDroughtDaysQuantity() {
        return droughtDaysQuantity;
    }

    public void setDroughtDaysQuantity(Integer droughtDaysQuantity) {
        this.droughtDaysQuantity = droughtDaysQuantity;
    }

    public Integer getRainyDaysQuantity() {
        return rainyDaysQuantity;
    }

    public void setRainyDaysQuantity(Integer rainyDaysQuantity) {
        this.rainyDaysQuantity = rainyDaysQuantity;
    }

    public List<Integer> getRainyDays() {
        return rainyDays;
    }

    public void setRainyDays(List<Integer> rainyDays) {
        this.rainyDays = rainyDays;
    }

    public Integer getOptimalDaysQuantity() {
        return optimalDaysQuantity;
    }

    public void setOptimalDaysQuantity(Integer optimalDaysQuantity) {
        this.optimalDaysQuantity = optimalDaysQuantity;
    }
}
