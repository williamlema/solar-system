package com.test.solarsystem.model;

public class Planet {

    private int angularSpeed;
    private boolean clockwise;
    private int sunDistance;
    private Position position;

    public Planet(int angularSpeed, boolean clockwise, int sunDistance, Position position) {
        this.angularSpeed = angularSpeed;
        this.clockwise = clockwise;
        this.sunDistance = sunDistance;
        this.position = position;
    }

    public int getAngularSpeed() {
        return angularSpeed;
    }

    public void setAngularSpeed(int angularSpeed) {
        this.angularSpeed = angularSpeed;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    public void setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
    }

    public int getSunDistance() {
        return sunDistance;
    }

    public void setSunDistance(int sunDistance) {
        this.sunDistance = sunDistance;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
