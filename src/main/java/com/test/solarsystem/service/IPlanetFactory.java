package com.test.solarsystem.service;

import com.test.solarsystem.constant.PlanetName;
import com.test.solarsystem.model.Planet;

public interface IPlanetFactory {

    Planet createPlanet(PlanetName name);
}
